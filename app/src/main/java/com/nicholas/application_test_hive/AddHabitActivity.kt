package com.nicholas.application_test_hive

import android.content.Context
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
import com.nicholas.application_test_hive.databinding.ActivityAddHabitBinding
import com.nicholas.application_test_hive.models.Habit
import com.nicholas.application_test_hive.viewmodel.HabitViewModel

class AddHabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddHabitBinding
    private lateinit var viewModel: HabitViewModel
    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Vibrator
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        ViewCompat.setOnApplyWindowInsetsListener(binding.scrollAddHabit) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        binding.btnSaveHabit.setOnClickListener {
            performHapticFeedback()
            val name = binding.editHabitName.text.toString().trim()
            val description = binding.editHabitDescription.text.toString().trim()

            if (name.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val habit = Habit(
                id = 0, // API will assign ID
                name = name,
                description = description,
                lastCompletedDate = null
            )

            viewModel.addHabit(habit)
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.habits.observe(this) {
            // On successful add, navigate back
            finish()
        }
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