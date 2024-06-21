package com.example.fitme.model

import com.google.gson.annotations.SerializedName

class Assessment1(
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,

    val gender: String?,
    val age: Int?,
    val weight:Int?,
    val occupation: String?,

    @SerializedName("sleep_duration")
    val sleepDuration: Int?,
    @SerializedName("quality_of_sleep")
    val qualityOfSleep: Int?,
    @SerializedName("physical_activity_level")
    val physicalActivityLevel: Int?,
    @SerializedName("stress_level")
    val stressLevel: Int?,
    @SerializedName("heart_rate")
    val heartRate: Int?,
    @SerializedName("daily_steps")
    val dailySteps: Int?,
    @SerializedName("sleep_disorder")
    val sleepDisorder: Int?,
    @SerializedName("systolic")
    val systolicBP: Int?,
    @SerializedName("diastolic")
    val diastolicBP: Int?


    ) {
    constructor(
        userId: Int,
        gender: String?,
        age: Int?,
        weight:Int?,
        occupation: String?,
        sleepDuration: Int?,
        qualityOfSleep: Int?,
        physicalActivityLevel: Int?,
        stressLevel: Int?,
        heartRate: Int?,
        dailySteps: Int?,
        sleepDisorder: Int?,
        systolicBP: Int?,
        diastolicBP: Int?


        ) : this(
        0,
        userId,
        gender,
        age,
        weight,
        occupation,
        sleepDuration,
        qualityOfSleep,
        physicalActivityLevel,
        stressLevel,
        heartRate,
        dailySteps,
        sleepDisorder,
        systolicBP,
        diastolicBP
    )

}