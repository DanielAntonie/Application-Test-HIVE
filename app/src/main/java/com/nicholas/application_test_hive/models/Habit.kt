package com.nicholas.application_test_hive.models

data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val lastCompletedDate: String?
)