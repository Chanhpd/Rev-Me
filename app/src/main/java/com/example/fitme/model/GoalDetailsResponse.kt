package com.example.fitme.model

data class GoalDetailsResponse (
    val user: User,
    val goal: GoalResponse,
    val plan: PlanResponse,
    val progress: ProgressResponse
        ){

}