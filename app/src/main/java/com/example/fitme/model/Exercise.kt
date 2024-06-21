package com.example.fitme.model

import java.io.Serializable

data class Exercise(
    val id: Int,
    val name: String,
    val description: String,
    val image_url: String,
    val video_url: String,
    val reps: Int,
    val sets: Int,
    val duration_minutes: Int,
    val calories: Int,
    val created_at: String,
    var status: Boolean,
    var workout_id: Int

    ) : Serializable {

    override fun toString(): String {
        return "Exercise(id=$id, name='$name', description='$description', image_url='$image_url', video_url='$video_url', reps=$reps, sets=$sets, duration_minutes=$duration_minutes, calories=$calories, created_at='$created_at')"
    }
}