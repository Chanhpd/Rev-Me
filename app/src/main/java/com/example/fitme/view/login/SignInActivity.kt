package com.example.fitme.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.fitme.R
import com.example.fitme.api.ApiService
import com.example.fitme.model.LoginRequest
import com.example.fitme.model.LoginResponse
import com.example.fitme.model.PreferenceManager
import com.example.fitme.view.assessment.AssessmentActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    lateinit var btnToSignUp: TextView
    lateinit var edtUsername : TextInputEditText
    lateinit var edtPassword : TextInputEditText
    lateinit var btnSignIn : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initView()

        btnToSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

//        edtUsername.setText("duychanh")
//        edtPassword.setText("chanh123")

        btnSignIn.setOnClickListener {

            val email = edtUsername.text.toString()
            val password = edtPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                   Toast.makeText(this, "Email or Password is empty", Toast.LENGTH_SHORT).show()

            } else {
                loginUSer(email, password)
            }
        }

    }

    private fun initView() {
        btnToSignUp = findViewById(R.id.btnToSignUp)
        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        btnSignIn = findViewById(R.id.btn_sign_in)
    }

    fun loginUSer(email: String, password: String){

        val loginRequest = LoginRequest(email,password)

        ApiService.apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if(response.isSuccessful){

                    val loginResponse = response.body()
                    val key = loginResponse?.key
                    Toast.makeText(this@SignInActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
                    PreferenceManager.saveKey(this@SignInActivity, key!!)
                    startActivity(Intent(this@SignInActivity, AssessmentActivity::class.java))
                    finish()

                } else {
                    Log.e("SignInActivity", "onResponse: $response")
                    Toast.makeText(this@SignInActivity, "Login Failed!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("SignInActivity", "onFailure: ${t.message}")
            }
        })
    }
}