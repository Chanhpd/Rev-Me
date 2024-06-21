package com.example.fitme.model

data class GoalResponse(
    val id: Int,
    val goal_type: String,
    val target_weight_kg: Double,
    val duration_weeks: Int,
    val start_date: String,
    val end_date: String,
    val created_at: String,
    val user: Int
) {
}