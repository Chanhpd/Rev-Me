package com.example.fitme.model

data class Goal(
    val obesity_lv: String,
    val advice  : String,
    val goal_type : String,
    val target_weight : Int,
    val duration_weeks : Int
) {
}