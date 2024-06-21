package com.example.fitme.view.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.fitme.R
import com.example.fitme.view.login.SignInActivity

class WelcomeActivity : AppCompatActivity() {

    lateinit var btn_get_started : TextView
    lateinit var textView13 : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btn_get_started = findViewById(R.id.btn_get_started)
        textView13 = findViewById(R.id.textView13)
        btn_get_started.setOnClickListener {
            startActivity(Intent(this, IntroActivity::class.java))
        }

        textView13.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}