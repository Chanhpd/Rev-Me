package com.example.fitme.model

data class Meal(
    val id: Int,
    val name: String,
    val description: String,
    val image_url: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val created_at: String,

    var status: Boolean,
    var meal_id: Int

    ) : java.io.Serializable {
}