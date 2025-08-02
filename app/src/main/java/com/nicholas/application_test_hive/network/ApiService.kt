package com.nicholas.application_test_hive.network

import com.nicholas.application_test_hive.models.Habit
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("habits")
    fun getHabits(): Call<List<Habit>>

    @POST("habits")
    fun addHabit(@Body habit: Habit): Call<Habit>

    @PUT("habits/{id}")
    fun markHabitDone(@Path("id") id: Int, @Body habit: Habit): Call<Habit>
}