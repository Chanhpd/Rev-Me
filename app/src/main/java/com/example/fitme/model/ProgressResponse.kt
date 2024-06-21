package com.example.fitme.model

data class ProgressResponse(
    val id: Int,
    val plan: PlanResponse ,
    val completed_workouts: Int,
    val completed_meals: Int,
    val total_calories_burned: Double,
    val total_calories_intake: Double,
    val total_water_intake: Double,
    val notes: String,
    val update_at: String

) {
}