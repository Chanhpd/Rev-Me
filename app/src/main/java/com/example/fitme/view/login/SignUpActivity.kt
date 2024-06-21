package com.example.fitme.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.fitme.R
import com.example.fitme.api.ApiService
import com.example.fitme.model.LoginResponse
import com.example.fitme.model.SignUpRequest
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var btnToSignIn: TextView
    lateinit var edtUsername: TextInputEditText
    lateinit var edtEmail: TextInputEditText
    lateinit var edtPassword: TextInputEditText
    lateinit var edtConfirmPassword: TextInputEditText
    lateinit var btnSignUp: TextView
    lateinit var tvError : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView()

        btnToSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        tvError.visibility = View.GONE

        btnSignUp.setOnClickListener {
            val username = edtUsername.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val confirmPassword = edtConfirmPassword.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(
                    this,
                    "Password and Confirm Password does not match!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                signUpUser(username, email, password, confirmPassword)
            }


        }

    }

    private fun initView() {
        btnToSignIn = findViewById(R.id.btnToSignUp)
        edtUsername = findViewById(R.id.edt_username)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        edtConfirmPassword = findViewById(R.id.edt_confirm_password)
        btnSignUp = findViewById(R.id.btn_sign_up)
        tvError = findViewById(R.id.tv_error)
    }

    fun signUpUser(username: String, email: String, password: String, confirmPassword: String) {

        val signUpRequest = SignUpRequest(username, email, password, confirmPassword)

        ApiService.apiService.registration(signUpRequest).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.body() == null) {
                    Toast.makeText(this@SignUpActivity, "Sign Up Success!", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                } else {
                    try {
                        val errorResponse = response.errorBody()?.string()
                        val json = JSONObject(errorResponse)
                        val errors = StringBuilder()

                        if (json.has("non_field_errors")) {
                            val nonFieldErrors = json.getJSONArray("non_field_errors")
                            for (i in 0 until nonFieldErrors.length()) {
                                errors.append(nonFieldErrors.getString(i)).append("\n")
                            }

                        }
                        if (json.has("username")) {
                            val usernameErrors = json.getJSONArray("username")
                            for (i in 0 until usernameErrors.length()) {
                                errors.append(usernameErrors.getString(i)).append("\n")
                            }

                        }
                        if (json.has("email")) {
                            val emailErrors = json.getJSONArray("email")
                            for (i in 0 until emailErrors.length()) {
                                errors.append(emailErrors.getString(i)).append("\n")
                            }
                        }
                        if (json.has("password1")) {
                            val password1Errors = json.getJSONArray("password1")
                            for (i in 0 until password1Errors.length()) {
                                errors.append(password1Errors.getString(i)).append("\n")
                            }
                        }
                        if (json.has("password2")) {
                            val password2Errors = json.getJSONArray("password2")
                            for (i in 0 until password2Errors.length()) {
                                errors.append(password2Errors.getString(i)).append("\n")
                            }
                        }
                        tvError.visibility = View.VISIBLE
                        tvError.text = "ERROR: $errors"
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Error registering user!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "Sign Up Failed! $call", Toast.LENGTH_SHORT)
                    .show()
            }

        })


    }
}