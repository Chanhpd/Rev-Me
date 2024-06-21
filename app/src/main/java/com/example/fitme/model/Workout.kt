package com.example.fitme.model

import java.io.Serializable

class Workout(
    val id: Int,
    val title: String,
    val image: String,
    val numOfExercise: String,
    val numberPerform: String
) : Serializable {
    constructor(
        title: String,
        image: String,
        numOfExercise: String,
        numberPerform: String
    ) : this(0, title, image, numOfExercise, numberPerform)
}