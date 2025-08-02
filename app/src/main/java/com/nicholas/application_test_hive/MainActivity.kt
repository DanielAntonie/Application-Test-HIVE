package com.nicholas.application_test_hive

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Vibrator
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        adapter = HabitAdapter { habit ->
            performHapticFeedback()
            val updatedHabit = habit.copy(lastCompletedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()))
            viewModel.markHabitDone(habit.id, updatedHabit)
        }
        binding.habitRecyclerView.adapter = adapter
        binding.habitRecyclerView.layoutManager = LinearLayoutManager(this)

        // Add haptic feedback for RecyclerView scrolling
        binding.habitRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy != 0) {
                    performHapticFeedback()
                }
            }
        })

        viewModel.habits.observe(this) { habits ->
            adapter.updateHabits(habits)
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        binding.btnAddHabit.setOnClickListener {
            performHapticFeedback()
            startActivity(Intent(this, AddHabitActivity::class.java))
        }

        binding.btnSync.setOnClickListener {
            performHapticFeedback()
            viewModel.fetchHabits()
            Toast.makeText(this, "Syncing habits...", Toast.LENGTH_SHORT).show()
        }

        // Initial fetch
        viewModel.fetchHabits()
    }

    private fun performHapticFeedback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(50)
        }
    }
}