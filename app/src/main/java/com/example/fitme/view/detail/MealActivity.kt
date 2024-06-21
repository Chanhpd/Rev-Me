package com.example.fitme.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.adapter.ExerciseAdapter
import com.example.fitme.adapter.MealAdapter
import com.example.fitme.api.ApiService
import com.example.fitme.model.*
import com.example.fitme.view.main.HomeActivity
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MealActivity : AppCompatActivity() {

    lateinit var recycleViewWorkout: RecyclerView
    lateinit var workoutAdapter: MealAdapter
    lateinit var btn_previous: FrameLayout
    lateinit var tvMonth: TextView
    lateinit var tvDay: TextView
    lateinit var btnChooseDate: ConstraintLayout

    var mealList = ArrayList<Meal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        initView()

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
        mealList.clear()
        ApiService.apiService.getMeal(key, goalId, day).enqueue(object :
            Callback<List<MealResponse>> {
            override fun onResponse(
                call: Call<List<MealResponse>>,
                response: Response<List<MealResponse>>
            ) {
                if (response.isSuccessful) {

                    val mealResponseList = response.body()
                    if (mealResponseList != null) {
                        for (mealResponse in mealResponseList) {

                            var meal = mealResponse.meal
                            val plan = mealResponse.plan
                            meal.status = mealResponse.status
                            meal.meal_id = mealResponse.id
                            // Process the exercise and plan details as needed
                            mealList.add(meal)


                        }
                        workoutAdapter = MealAdapter(mealList)
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

            override fun onFailure(call: Call<List<MealResponse>>, t: Throwable) {
                Log.e("Workout", "fetchWorkoutDetails: ${t.message}")
            }
        })
    }
}