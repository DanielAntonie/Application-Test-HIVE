package com.nicholas.application_test_hive.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nicholas.application_test_hive.models.Habit
import com.nicholas.application_test_hive.repository.HabitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HabitViewModel : ViewModel() {
    private val repository = HabitRepository()
    private val _habits = MutableLiveData<List<Habit>>()
    val habits: LiveData<List<Habit>> = _habits
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchHabits() {
        repository.getHabits().enqueue(object : Callback<List<Habit>> {
            override fun onResponse(call: Call<List<Habit>>, response: Response<List<Habit>>) {
                if (response.isSuccessful) {
                    _habits.postValue(response.body())
                } else {
                    _error.postValue("Failed to fetch habits: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Habit>>, t: Throwable) {
                _error.postValue("Error: ${t.message}")
            }
        })
    }

    fun addHabit(habit: Habit) {
        repository.addHabit(habit).enqueue(object : Callback<Habit> {
            override fun onResponse(call: Call<Habit>, response: Response<Habit>) {
                if (response.isSuccessful) {
                    fetchHabits() // Refresh the list
                } else {
                    _error.postValue("Failed to add habit: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Habit>, t: Throwable) {
                _error.postValue("Error: ${t.message}")
            }
        })
    }

    fun markHabitDone(id: Int, habit: Habit) {
        repository.markHabitDone(id, habit).enqueue(object : Callback<Habit> {
            override fun onResponse(call: Call<Habit>, response: Response<Habit>) {
                if (response.isSuccessful) {
                    fetchHabits() // Refresh the list
                } else {
                    _error.postValue("Failed to update habit: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Habit>, t: Throwable) {
                _error.postValue("Error: ${t.message}")
            }
        })
    }
}