package com.nicholas.application_test_hive.repository

import retrofit2.Call
import com.nicholas.application_test_hive.models.Habit
import com.nicholas.application_test_hive.network.RetrofitClient

class HabitRepository {
    fun getHabits(): Call<List<Habit>> {
        return RetrofitClient.apiService.getHabits()
    }

    fun addHabit(habit: Habit): Call<Habit> {
        return RetrofitClient.apiService.addHabit(habit)
    }

    fun markHabitDone(id: Int, habit: Habit): Call<Habit> {
        return RetrofitClient.apiService.markHabitDone(id, habit)
    }
}