package com.example.fitme.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.adapter.ProgramAdapter
import com.example.fitme.api.ApiService
import com.example.fitme.model.*
import com.example.fitme.view.assessment.AssessmentActivity
import com.example.fitme.view.detail.MealActivity
import com.example.fitme.view.detail.WorkoutActivity
import com.example.fitme.view.login.SignInActivity
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    lateinit var radioGroupProgram: RadioGroup
    lateinit var recyclerViewProgram: RecyclerView
    lateinit var programAdapter: ProgramAdapter
    lateinit var profile_image: CircleImageView
    lateinit var textView_username: TextView
    lateinit var textView_calories: TextView
    lateinit var imageView_notification: ImageView
    lateinit var constraintLayout_workout: ConstraintLayout
    lateinit var textView35: TextView
    lateinit var tv_calo_intake: TextView
    lateinit var progressBar_calo: CircularProgressBar
    lateinit var progress_meal: CircularProgressBar
    lateinit var btnOpenMeal : ConstraintLayout
    var userId = 0
    var key = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        intView(view)


        key = "token " + PreferenceManager.getKey(requireContext())
        Log.d("key", key)

        val goalId = PreferenceManager.getGoalId(requireContext())
        Log.d("goalId", goalId.toString())
        fetchGoalDetails(key, goalId)

        replaceRecyclerViewProgram("Jog")

        radioGroupProgram.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)
            val selected = selectedRadioButton.text.toString()

            replaceRecyclerViewProgram(selected)


        }

        constraintLayout_workout.setOnClickListener {
            startActivity(Intent(activity, WorkoutActivity::class.java))
        }
        imageView_notification.setOnClickListener {
            startActivity(Intent(activity, AssessmentActivity::class.java))
        }

        btnOpenMeal.setOnClickListener {
            startActivity(Intent(activity, MealActivity::class.java))
        }
        return view
    }

    fun intView(view: View) {
        radioGroupProgram = view.findViewById(R.id.radioGroupProgram)
        recyclerViewProgram = view.findViewById(R.id.recycleVewProgram)
        recyclerViewProgram.layoutManager = LinearLayoutManager(activity)
        profile_image = view.findViewById(R.id.profile_image)
        textView_username = view.findViewById(R.id.textView_username)
        textView_calories = view.findViewById(R.id.textView_calories)
        imageView_notification = view.findViewById(R.id.imageView_notification)
        constraintLayout_workout = view.findViewById(R.id.constraintLayout_workout)
        progressBar_calo = view.findViewById(R.id.progressBar_calo)
        progress_meal = view.findViewById(R.id.progress_meal)
        tv_calo_intake = view.findViewById(R.id.tv_calo_intake)
        textView35 = view.findViewById(R.id.textView35)
        btnOpenMeal = view.findViewById(R.id.btnOpenMeal)
        textView35.setOnClickListener {
            startActivity(Intent(activity, SignInActivity::class.java))
        }
    }

    fun fetchGoalDetails(key: String, goalId: Int) {

        Log.d("HomeFragment", "fetchGoalDetails: $key, $goalId")

        ApiService.apiService.getHome(key, goalId).enqueue(object : Callback<GoalDetailsResponse> {
            override fun onResponse(
                call: Call<GoalDetailsResponse>,
                response: Response<GoalDetailsResponse>
            ) {

                if (response.isSuccessful) {

                    val goalDetailsResponse = response.body()
                    if (goalDetailsResponse != null) {
                        // Xử lý dữ liệu nhận được từ API
                        val user = goalDetailsResponse.user
                        val goal = goalDetailsResponse.goal
                        val plan = goalDetailsResponse.plan
                        val progress = goalDetailsResponse.progress

                        textView_username.text = user.username

                        textView_calories.text = progress.total_calories_burned.toString()

                        progressBar_calo.progress = progress.total_calories_burned.toFloat()
                        progressBar_calo.progressMax = plan.calories_intake_per_day.toFloat()

                        progress_meal.progress = progress.total_calories_intake.toFloat()
                        progress_meal.progressMax = plan.calories_intake_per_day.toFloat()

                        tv_calo_intake.text = "${progress.total_calories_intake.toInt()}/${plan.calories_intake_per_day.toInt()}"

                    }
                } else
                    Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<GoalDetailsResponse>, t: Throwable) {
                Log.e("HomeFragment", "fetchGoalDetails: ${t.message}")
            }
        })
    }

    fun replaceRecyclerViewProgram(selected: String) {

        val programs = getProgramsForSelection(selected)

        programAdapter = ProgramAdapter(programs)
        recyclerViewProgram.adapter = programAdapter
    }

    private fun getProgramsForSelection(selected: String): ArrayList<Program> {
        val programList = ArrayList<Program>()

        when (selected) {
            "Jog" -> {
                programList.add(
                    Program(
                        1,
                        "Jog",
                        "Morning Routine",
                        "Hours",
                        "https://emilypost.com/client_media/images/blogs/everyday-gym.jpg",
                        50
                    )
                )
                programList.add(
                    Program(
                        2,
                        "Jog",
                        "Running ",
                        "Hours",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVxu4g1MjZUxgb1ohSl36uh4veRXr8hIzfEw&s",
                        50
                    )
                )
            }
            "Yoga" -> {
                programList.add(
                    Program(
                        3,
                        "Yoga",
                        "Description 3",
                        "Hours",
                        "https://images.shiksha.com/mediadata/images/articles/1690284718phpoWwdGe.jpeg",
                        50
                    )
                )
                programList.add(
                    Program(
                        4,
                        "Yoga",
                        "Description 4",
                        "Hours",
                        "https://media.post.rvohealth.io/wp-content/uploads/2020/10/Female_Yoga_1200x628-facebook-1200x628.jpg",
                        50
                    )
                )
            }
            "Cycling" -> {
                programList.add(
                    Program(
                        5,
                        "Cycling",
                        "Description 5",
                        "Hours",
                        "https://example.com/image5.jpg",
                        50
                    )
                )
                programList.add(
                    Program(
                        6,
                        "Cycling",
                        "Description 6",
                        "Hours",
                        "https://example.com/image6.jpg",
                        50
                    )
                )
            }
            "Workout" -> {
                programList.add(
                    Program(
                        7,
                        "Workout",
                        "Description 7",
                        "Hours",
                        "https://example.com/image7.jpg",
                        50
                    )
                )
                programList.add(
                    Program(
                        8,
                        "Workout",
                        "Description 8",
                        "Hours",
                        "https://example.com/image8.jpg",
                        50
                    )
                )
            }
            else -> "No program available"
        }
        return programList
    }
}