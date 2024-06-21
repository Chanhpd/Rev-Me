package com.example.fitme.model

data class MealResponse(
    val id: Int,
    val meal: Meal,
    val plan: Plan,
    val status: Boolean,
    val note: String,
    val created_at: String
) {
}