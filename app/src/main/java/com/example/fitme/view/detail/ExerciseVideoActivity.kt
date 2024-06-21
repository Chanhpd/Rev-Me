package com.example.fitme.view.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitme.R
import com.example.fitme.api.ApiService
import com.example.fitme.model.Exercise
import com.example.fitme.model.PreferenceManager
import com.example.fitme.model.WorkoutResponse
import com.example.fitme.model.WorkoutStatus
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import retrofit2.Callback


class ExerciseVideoActivity : AppCompatActivity() {

    lateinit var youTubePlayerView : YouTubePlayerView
    lateinit var tv_ex_num : TextView
    lateinit var tv_title : TextView
    lateinit var progressBar_time : ProgressBar
    lateinit var tv_time_current : TextView
    lateinit var tv_duration : TextView
    lateinit var img_pause : ImageView
    lateinit var btn_previous : FrameLayout
    lateinit var btnComplete : TextView
    lateinit var tv_desc_ex_video : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_video)
        initView()

        val exercise = intent.getSerializableExtra("exercise") as Exercise

        tv_ex_num.text = "Exercise " + exercise.id
        tv_title.text = exercise.name
        tv_desc_ex_video.text = exercise.description
//        var video_url ="https://www.youtube.com/watch?v=LhL5SNZfnQs"

        val idVideo = getVideoId(exercise.video_url)


        btn_previous.setOnClickListener {
            finish()
        }

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                youTubePlayer.loadVideo(idVideo, 0f)


            }

            override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
                // format duration 00:00
                val durationMinute = duration.toInt() / 60
                val durationSecond = duration.toInt() % 60

                tv_duration.text = "$durationMinute:$durationSecond"
                progressBar_time.max = duration.toInt()
                super.onVideoDuration(youTubePlayer, duration)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                // format current time 00:00
                val currentMinute = second.toInt() / 60
                val currentSecond = second.toInt() % 60

                tv_time_current.text = "$currentMinute:$currentSecond"
                progressBar_time.progress = second.toInt()

                super.onCurrentSecond(youTubePlayer, second)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {

                img_pause.setOnClickListener {
                    if (state == PlayerConstants.PlayerState.PLAYING) {
                        youTubePlayer.pause()
                        img_pause.setImageResource(R.drawable.ic_baseline_slow_motion_video_24)
                    } else {
                        youTubePlayer.play()
                        img_pause.setImageResource(R.drawable.ic_baseline_pause_circle_24)
                    }
                }

                super.onStateChange(youTubePlayer, state)
            }

        })

        val key = "token " + PreferenceManager.getKey(applicationContext)
        val idWorkout = exercise.workout_id

        Log.d("key", key)
        Log.d("idWorkout", idWorkout.toString())
        btnComplete.setOnClickListener {
            updateStatus(key, idWorkout)
        }
    }

    fun initView() {
        youTubePlayerView = findViewById(R.id.youtube_player_view)
        tv_ex_num = findViewById(R.id.tv_ex_num)
        tv_title = findViewById(R.id.tv_title)
        progressBar_time = findViewById(R.id.progressBar_time)
        tv_time_current = findViewById(R.id.tv_time_current)
        tv_duration = findViewById(R.id.tv_duration)
        img_pause = findViewById(R.id.img_pause)
        btn_previous = findViewById(R.id.btn_previous)
        btnComplete = findViewById(R.id.btn_complete)
        tv_desc_ex_video = findViewById(R.id.tv_desc_ex_video)
    }

    fun getVideoId(linkVideo : String) : String {
        // https://www.youtube.com/watch?v=IODxDxX7oi4&pp=ygUHUHVzaC11cA%3D%3D
        val videoId = linkVideo.split("v=")[1].split("&")[0]
        return videoId
    }

    fun updateStatus(key : String, idWorkout : Int) {
        // update status of exercise
        var workoutStatus = WorkoutStatus(true)
        ApiService.apiService.updateStatus(key, idWorkout, workoutStatus).enqueue(object : Callback<WorkoutResponse> {
            override fun onResponse(call: retrofit2.Call<WorkoutResponse>, response: retrofit2.Response<WorkoutResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Update status success", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(applicationContext, WorkoutActivity::class.java))

                } else {
                    Toast.makeText(applicationContext, "Update status failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<WorkoutResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Update status failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}