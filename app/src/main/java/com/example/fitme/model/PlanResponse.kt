package com.example.fitme.model

data class PlanResponse(
    val id: Int,
    val name_day: String,
    val description: String,
    val calories_burned_per_day: Double,
    val calories_intake_per_day: Double,
    val created_at: String,
    val user: Int,
    val goal: Int
) {

}