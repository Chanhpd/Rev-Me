package com.example.fitme.view.assessment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitme.R
import com.example.fitme.model.SharedViewModelAssessment
import com.example.fitme.view.assessment.fragmentQuestion.Page1Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitme.view.assessment.fragmentQuestion.Page17Fragment


class AssessmentActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessment)


        addFragment()
    }
    fun addFragment(){

        val sub1 = Page1Fragment()


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flAssessment, sub1)
            commit()
        }
    }
}