package com.example.fitme.api

import com.example.fitme.model.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import okhttp3.Response

interface ApiService {

    companion object {

        //192.168.1.118
        // 172.20.10.2
        val IP = "172.20.10.2"
//        val IP = "192.168.1.118"

        val BASE_URL = "http://$IP:8000/api/"

        val gson: Gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        val apiService: ApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


    @POST("auth/login/")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("auth/registration/")
    fun registration(@Body signUpRequest: SignUpRequest): Call<ResponseBody>

    //    @Headers("Content-Type: application/json")
    @POST("predict/")
    fun createAssessment(
        @Header("Authorization") token: String,
        @Body assessment: Assessment
    ): Call<Goal>

    @GET("auth/user/")
    fun getUser(@Header("Authorization") token: String): Call<UserResponse>

    @POST("generate_plan/")
    fun generatePlan(
        @Header("Authorization") token: String,
        @Body plan: Plan
    ): Call<PlanStatus>


    // put auth token and goal id
    @GET("home/{goal_id}/")
    fun getHome(
        @Header("Authorization") token: String,
        @Path("goal_id") goal_id: Int
    ): Call<GoalDetailsResponse>


    @GET("workout/{goal_id}&{day}/")
    fun getWorkout(
        @Header("Authorization") token: String,
        @Path("goal_id") goal_id: Int,
        @Path("day") day: String
    ): Call<List<WorkoutResponse>>

    @PUT("workout/{workout_id}/")
    fun updateStatus(
        @Header("Authorization") token: String,
        @Path("workout_id") workout_id: Int,
        @Body status: WorkoutStatus
    ): Call<WorkoutResponse>

    @GET("meal/{goal_id}&{day}/")
    fun getMeal(
        @Header("Authorization") token: String,
        @Path("goal_id") goal_id: Int,
        @Path("day") day: String
    ): Call<List<MealResponse>>

    @PUT("meal/{meal_id}/")
    fun updateMealStatus(
        @Header("Authorization") token: String,
        @Path("meal_id") meal_id: Int,
        @Body status: WorkoutStatus
    ): Call<MealResponse>
}

class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Token $authToken")
            .build()
        return chain.proceed(request)
    }
}