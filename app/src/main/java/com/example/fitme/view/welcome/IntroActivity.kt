package com.example.fitme.view.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme.R
import com.example.fitme.view.login.SignInActivity
import com.example.fitme.adapter.IntroViewPagerAdapter
import com.example.fitme.model.IntroSlider

class IntroActivity : AppCompatActivity() {
    lateinit var viewPagerIntro: ViewPager2
    lateinit var list: ArrayList<IntroSlider>
    lateinit var btnNext : FrameLayout
    lateinit var btnPre : FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        initView()
        addViewPager()
        btnNext.setOnClickListener {
            if (viewPagerIntro.currentItem + 1 < list.size) {
                viewPagerIntro.currentItem = viewPagerIntro.currentItem + 1
            }
            if (viewPagerIntro.currentItem == list.size - 1) {
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }
        btnPre.setOnClickListener {
            if (viewPagerIntro.currentItem - 1 >= 0) {
                viewPagerIntro.currentItem = viewPagerIntro.currentItem - 1
            }
        }
    }

    private fun addViewPager() {
        list = ArrayList()
        list.add(IntroSlider("Personalized Fitness Plans", "Choose your own fitness journey with AI. \uD83C\uDFCB️\u200D♀️", R.drawable.sport_girl))
        list.add(IntroSlider("Extensive Workout Libraries", "Explore ~100K exercises made for you! \uD83D\uDCAA", R.drawable.fitnesss))
        list.add(IntroSlider("Health Metrics &  Fitness Analytics", "Monitor your health profile with ease. \uD83D\uDCC8", R.drawable.heart_black))
        list.add(IntroSlider("Nutrition & Diet Guidance", "Lose weight and get fit with sandow! \uD83E\uDD52", R.drawable.banner_fruit))
        list.add(IntroSlider("Virtual AI Coach Mentoring", "Say goodbye to manual coaching! \uD83D\uDC4B", R.drawable.robot))
        viewPagerIntro.adapter = IntroViewPagerAdapter(this, list)

    }

    private fun initView() {
        viewPagerIntro = findViewById(R.id.viewPagerIntro)
        btnNext = findViewById(R.id.btn_next_banner)
        btnPre = findViewById(R.id.btn_pre_banner)
    }
}