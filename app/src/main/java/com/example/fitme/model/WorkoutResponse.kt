package com.example.fitme.model

data class WorkoutResponse(
    val id: Int,
    val exercise: Exercise,
    val plan: PlanResponse,
    val note: String,
    val status: Boolean,
    val update_at: String,

) {
}