package com.example.fitme.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.adapter.ExerciseAdapter
import com.example.fitme.adapter.WorkoutAdapter
import com.example.fitme.api.ApiService
import com.example.fitme.model.Exercise
import com.example.fitme.model.PreferenceManager
import com.example.fitme.model.Workout
import com.example.fitme.model.WorkoutResponse
import com.example.fitme.view.main.HomeActivity
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WorkoutActivity : AppCompatActivity() {

    lateinit var recycleViewWorkout: RecyclerView
    lateinit var workoutAdapter: ExerciseAdapter
    lateinit var btn_previous: FrameLayout
    lateinit var tvMonth: TextView
    lateinit var tvDay: TextView
    lateinit var btnChooseDate: ConstraintLayout

    var exerciseList = ArrayList<Exercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        initView()

//        val list = getWorkoutList()
//        workoutAdapter = ExerciseAdapter(list)


        val key = "token " + PreferenceManager.getKey(this)
        val goalId = PreferenceManager.getGoalId(this)

        // Get the current date
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        fetchWorkoutDetails(key, goalId, date)

        tvMonth.text = SimpleDateFormat("MMMM", Locale.getDefault()).format(Date())
        tvDay.text = SimpleDateFormat("dd", Locale.getDefault()).format(Date())

        btnChooseDate.setOnClickListener {
            // open date picker
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()

            datePicker.show(supportFragmentManager, "date_picker")
            datePicker.addOnPositiveButtonClickListener {
                val date = Date(it)
                val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
                fetchWorkoutDetails(key, goalId, selectedDate)
                tvMonth.text = SimpleDateFormat("MMMM", Locale.getDefault()).format(date)
                tvDay.text = SimpleDateFormat("dd", Locale.getDefault()).format(date)

//                fetchWorkoutDetails(key, goalId, selectedDate)

                // chọn ngày lớn hơn ngày hiện tại
                val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                if (selectedDate > currentDate) {
                    fetchWorkoutDetails(key, goalId, selectedDate)
                } else {
                    // sử dụng Dialog để báo lỗi ngày không hợp lệ
                    val dialog = AlertDialog.Builder(this)
                        .setTitle("Please choose a valid date")
                        .setMessage("Please choose a date greater than the current date!!!")
                        .setPositiveButton("OK") { dialog, which ->
                            dialog.dismiss()
                        }
                        .create()

                    dialog.show()

                }

            }

        }

        btn_previous.setOnClickListener {
            // return to previous activity
            startActivity(Intent(this, HomeActivity::class.java))
        }


    }

    private fun initView() {
        recycleViewWorkout = findViewById(R.id.recycleView_workout)
        btn_previous = findViewById(R.id.btn_previous)

        tvMonth = findViewById(R.id.tv_month)
        tvDay = findViewById(R.id.tv_day)
        btnChooseDate = findViewById(R.id.btn_choose_day)

    }

    fun fetchWorkoutDetails(key: String, goalId: Int, day: String) {
        exerciseList.clear()
        ApiService.apiService.getWorkout(key, goalId, day).enqueue(object :
            Callback<List<WorkoutResponse>> {
            override fun onResponse(
                call: Call<List<WorkoutResponse>>,
                response: Response<List<WorkoutResponse>>
            ) {
                if (response.isSuccessful) {

                    val workoutResponseList = response.body()
                    if (workoutResponseList != null) {
                        for (workoutResponse in workoutResponseList) {

                            var exercise = workoutResponse.exercise
                            val plan = workoutResponse.plan
                            exercise.status = workoutResponse.status
                            exercise.workout_id = workoutResponse.id
                            // Process the exercise and plan details as needed
                            exerciseList.add(exercise)


                        }
                        workoutAdapter = ExerciseAdapter(exerciseList)
                        recycleViewWorkout.layoutManager = LinearLayoutManager(applicationContext)
                        recycleViewWorkout.adapter = workoutAdapter
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Failed to fetch workout details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<WorkoutResponse>>, t: Throwable) {
                Log.e("Workout", "fetchWorkoutDetails: ${t.message}")
            }
        })
    }


}