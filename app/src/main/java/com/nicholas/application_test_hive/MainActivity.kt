package com.nicholas.application_test_hive

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholas.application_test_hive.adapters.HabitAdapter
import com.nicholas.application_test_hive.databinding.ActivityMainBinding
import com.nicholas.application_test_hive.viewmodel.HabitViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HabitViewModel
    private lateinit var adapter: HabitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        adapter = HabitAdapter { habit ->
            val updatedHabit = habit.copy(lastCompletedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()))
            viewModel.markHabitDone(habit.id, updatedHabit)
        }
        binding.habitRecyclerView.adapter = adapter
        binding.habitRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.habits.observe(this) { habits ->
            adapter.updateHabits(habits)
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        binding.btnAddHabit.setOnClickListener {
            startActivity(Intent(this, AddHabitActivity::class.java))
        }

        binding.btnSync.setOnClickListener {
            viewModel.fetchHabits()
            Toast.makeText(this, "Syncing habits...", Toast.LENGTH_SHORT).show()
        }

        // Initial fetch
        viewModel.fetchHabits()
    }
}