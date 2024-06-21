package com.example.fitme.model

data class Plan (
    val user: Int,
    val goal_type: String,
    val target_weight_kg: Double,
    val duration_weeks: Int,
    val start_date: String,
    val end_date: String
        ){
    override fun toString(): String {
        return "Plan(user=$user, goal_type='$goal_type', target_weight_kg=$target_weight_kg, duration_weeks=$duration_weeks, start_date='$start_date', end_date='$end_date')"
    }
}