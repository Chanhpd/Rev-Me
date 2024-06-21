package com.example.fitme.model

import com.google.gson.annotations.SerializedName

class Assessment(
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("Height")
    var height: Float?,
    @SerializedName("Weight")
    val weight: Int?,
    @SerializedName("Gender")
    val gender: String?,
    @SerializedName("Age")
    val age: Int?,
    @SerializedName("CALC")
    val calc: String?,
    @SerializedName("FAVC")
    val favc: String?, // 6


    val FCVC: Int?,

    val NCP: Int?,

    val SCC: String?,

    val SMOKE: String?, // 10

    val CH2O: Int?,

    val family_history_with_overweight: String?,

    val FAF: Int?,

    val TUE: Int?,  // 14

    val CAEC: String?,
    val MTRANS: String?,

    ) {
    constructor(
        userId: Int,
        height: Float?,
        weight: Int?,
        gender: String?,
        age: Int?,
        calc: String?,
        favc: String?,

        FCVC: Int?,

        NCP: Int?,

        SCC: String?,

        SMOKE: String?,

        CH2O: Int?,

        family_history_with_overweight: String?,

        FAF: Int?,

        TUE: Int?,

        CAEC: String?,
        MTRANS: String?,

        ) : this(
        1,
        userId,
        height,
        weight,
        gender,
        age,
        calc,
        favc,
        FCVC,
        NCP,
        SCC,
        SMOKE,
        CH2O,
        family_history_with_overweight,
        FAF,
        TUE,
        CAEC,
        MTRANS,
    )

    override fun toString(): String {
        return "Assessment(id=$id, userId=$userId, height=$height, weight=$weight, gender=$gender, age=$age, calc=$calc, favc=$favc, FCVC=$FCVC, NCP=$NCP, SCC=$SCC, SMOKE=$SMOKE, CH2O=$CH2O, family_history_with_overweight=$family_history_with_overweight, FAF=$FAF, TUE=$TUE, CAEC=$CAEC, MTRANS=$MTRANS)"
    }


}