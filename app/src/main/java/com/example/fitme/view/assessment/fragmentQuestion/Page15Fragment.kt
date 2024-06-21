package com.example.fitme.view.assessment.fragmentQuestion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fitme.R
import com.example.fitme.api.ApiService
import com.example.fitme.model.Assessment1
import com.example.fitme.model.Goal
import com.example.fitme.view.main.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Page15Fragment : Fragment() {

    //    lateinit var btnPrevious: FrameLayout
    lateinit var btn_nextFragment: LinearLayout

    lateinit var btnPrevious: FrameLayout
    lateinit var radioGroup_ass15: RadioGroup

    var CAEC = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_page15, container, false)

        radioGroup_ass15 = view.findViewById(R.id.radioGroup_ass15)

        radioGroup_ass15.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = view.findViewById(checkedId)
            CAEC = radio.text.toString()
        }


        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)
        btn_nextFragment.setOnClickListener {

            if (CAEC == "") {
                Toast.makeText(context, "Please pick one option!", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = arguments // Sử dụng Bundle từ Fragment 1
                bundle?.putString("ass_15", CAEC)

                val fragment = Page16Fragment()
                fragment.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flAssessment, fragment)
                    addToBackStack(null)
                    commit()
                }
            }

        }

        return view
    }

//    private fun submitAssessment(assessment1: Assessment1) {
//        ApiService.apiService.createAssessment(assessment1).enqueue(object : Callback<Goal> {
//            override fun onResponse(call: Call<Goal>, response: Response<Goal>) {
//                if (response.isSuccessful) {
//                    val goal = response.body()
//
//                    Log.d("AAA", goal.toString())
//                    Toast.makeText(context, "Predict BMI successfully!", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Goal>, t: Throwable) {
//                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
//            }
//
//
//        })
//
//    }

    fun goPreviousFragment() {

        requireActivity().supportFragmentManager.popBackStackImmediate()
    }
}