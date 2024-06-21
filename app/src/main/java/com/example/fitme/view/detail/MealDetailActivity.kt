package com.example.fitme.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.api.ApiService
import com.example.fitme.model.*
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailActivity : AppCompatActivity() {

    lateinit var textView_title_workout: TextView
    lateinit var imageView_workout: ImageView
    lateinit var btn_previous: FrameLayout


    lateinit var btn_start_ex : TextView
    lateinit var tv_calo_ex : TextView
    lateinit var tv_duration_ex : TextView
    lateinit var tv_desc_ex : TextView
    lateinit var tv_set_ex : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)

        initView()

        val meal = intent.getSerializableExtra("meal") as Meal
        textView_title_workout.text = meal.name
        tv_desc_ex.text = meal.description
        tv_duration_ex.text = meal.protein.toString() + "g"
        tv_calo_ex.text = meal.calories.toString() + " Kcal"
        tv_set_ex.text = meal.fat.toString() + "g"
        Picasso.get().load(meal.image_url).into(imageView_workout)
        btn_previous.setOnClickListener {
            finish()
        }

        btn_start_ex.setOnClickListener {
            val key ="token "+ PreferenceManager.getKey(this)

            updateStatus(key, meal.meal_id)

        }
    }

    private fun initView() {
        textView_title_workout = findViewById(R.id.textView_title_workout)
        imageView_workout = findViewById(R.id.imageView_workout)
        btn_previous = findViewById(R.id.btn_previous)


        btn_start_ex = findViewById(R.id.btn_start_ex)
        tv_calo_ex = findViewById(R.id.tv_calo_ex)
        tv_duration_ex = findViewById(R.id.tv_duration_ex)
        tv_desc_ex = findViewById(R.id.tv_desc_ex)
        tv_set_ex = findViewById(R.id.tv_set)
    }
    fun updateStatus(key : String, idWorkout : Int) {
        // update status of exercise
        var workoutStatus = WorkoutStatus(true)
        ApiService.apiService.updateMealStatus(key, idWorkout, workoutStatus).enqueue(object :  Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Update status success", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(applicationContext, MealActivity::class.java))

                } else {
                    Toast.makeText(applicationContext, "Update status failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Update status failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}