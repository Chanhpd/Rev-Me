package com.example.fitme.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.adapter.ExerciseAdapter
import com.example.fitme.model.Exercise
import com.example.fitme.model.Workout
import com.squareup.picasso.Picasso

class WorkoutDetailActivity : AppCompatActivity() {

    lateinit var textView_title_workout: TextView
    lateinit var imageView_workout: ImageView
    lateinit var btn_previous: FrameLayout

    lateinit var recyclerView: RecyclerView
    lateinit var constraintLayout_open_exercise : ConstraintLayout
    lateinit var linearLayout_btn_ex : LinearLayout
    lateinit var btn_start_ex : TextView
    lateinit var tv_calo_ex : TextView
    lateinit var tv_duration_ex : TextView
    lateinit var tv_desc_ex : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_detail)

        initView()

        val exercise = intent.getSerializableExtra("exercise") as Exercise
        textView_title_workout.text = exercise.name
        Picasso.get().load(exercise.image_url).error(R.drawable.meal).into(imageView_workout)
        


        Picasso.get().load(exercise.image_url).error(R.drawable.man_running).into(imageView_workout)
        constraintLayout_open_exercise.visibility = ConstraintLayout.GONE

        btn_start_ex.setOnClickListener {

//            constraintLayout_open_exercise.visibility = ConstraintLayout.VISIBLE
//            linearLayout_btn_ex.visibility = LinearLayout.GONE

            val intent = Intent(this, ExerciseVideoActivity::class.java)
            intent.putExtra("exercise", exercise)
            startActivity(intent)
        }

        tv_calo_ex.text = exercise.calories.toString() + " calo"
        tv_duration_ex.text = exercise.duration_minutes.toString() + " min"
        tv_desc_ex.text = exercise.description

        btn_previous.setOnClickListener {

            finish()
        }

    }

    private fun initView() {
        textView_title_workout = findViewById(R.id.textView_title_workout)
        imageView_workout = findViewById(R.id.imageView_workout)
        btn_previous = findViewById(R.id.btn_previous)
        recyclerView = findViewById(R.id.recycleView_exercise)
        constraintLayout_open_exercise = findViewById(R.id.constraintLayout_open_exercise)
        linearLayout_btn_ex = findViewById(R.id.linearLayout_btn_ex)
        btn_start_ex = findViewById(R.id.btn_start_ex)
        tv_calo_ex = findViewById(R.id.tv_calo_ex)
        tv_duration_ex = findViewById(R.id.tv_duration_ex)
        tv_desc_ex = findViewById(R.id.tv_desc_ex)
    }




}