package com.example.fitme.view.assessment.fragmentQuestion

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.fitme.R
import com.example.fitme.api.ApiService
import com.example.fitme.model.*
import com.example.fitme.view.main.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate


class Page17Fragment : Fragment() {

    lateinit var textView_ob: TextView
    lateinit var textView_advice: TextView
    lateinit var btn_nextFragment: LinearLayout
    lateinit var tvGoalType: TextView
    lateinit var edtTargetWeight: EditText
    lateinit var edtDurationWeeks: EditText

    lateinit var tvStart: TextView
    lateinit var tvEnd: TextView

    var stardard = 0.0

    var key = ""
    var allow = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page17, container, false)


        textView_ob = view.findViewById(R.id.textView_ob)
        textView_advice = view.findViewById(R.id.textView_advice)
        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)
        tvGoalType = view.findViewById(R.id.tv_goal_type)
        edtDurationWeeks = view.findViewById(R.id.edtDurationWeeks)
        edtTargetWeight = view.findViewById(R.id.edtTargetWeight)
        tvStart = view.findViewById(R.id.tv_start)
        tvEnd = view.findViewById(R.id.tv_end)

        key = "token ${PreferenceManager.getKey(requireContext())}"

        saveUser(requireContext())



        val height = arguments?.getFloat("ass_1")
        val gender = arguments?.getString("ass_2")
        val weight = arguments?.getInt("ass_3")
        val age = arguments?.getInt("ass_4")
        val calc = arguments?.getString("ass_5")
        val favc = arguments?.getString("ass_6")
        val FCVC = arguments?.getInt("ass_7")
        val NCP = arguments?.getInt("ass_8")
        val SCC = arguments?.getString("ass_9")
        val SMOKE = arguments?.getString("ass_10")
        val CH2O = arguments?.getInt("ass_11")
        val family_history_with_overweight = arguments?.getString("ass_12")
        val FAF = arguments?.getInt("ass_13")
        val TUE = arguments?.getInt("ass_14")
        val CAEC = arguments?.getString("ass_15")
        val MTRANS = arguments?.getString("ass_16")


        val assessment = Assessment(
            1,
            height,
            weight,
            gender,
            age,
            calc,
            favc,
            FCVC,
            NCP,
            SCC,
            SMOKE,
            CH2O,
            family_history_with_overweight,
            FAF,
            TUE,
            CAEC,
            MTRANS,
        )
//        val assessment = Assessment(
//            1,
//            1.71f,
//            80,
//            "Male",
//            21,
//            "Always",
//            "no",
//            2,
//            3,
//            "no",
//            "no",
//            2,
//            "yes",
//            0,
//            1,
//            "Sometimes",
//            "Bike"
//        )

        submitAssessment(assessment)

        return view
    }


    private fun submitAssessment(assessment: Assessment) {
        ApiService.apiService.createAssessment(key, assessment).enqueue(object : Callback<Goal> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<Goal>, response: Response<Goal>) {
                if (response.isSuccessful) {
                    val goal = response.body()

                    textView_ob.text = goal!!.obesity_lv
                    textView_advice.text = goal.advice
                    tvGoalType.text = goal.goal_type

                    stardard = goal.target_weight.toDouble() / goal.duration_weeks

                    edtTargetWeight.setText(goal.target_weight.toString())
                    edtDurationWeeks.setText(goal.duration_weeks.toString())

                    val startDate = LocalDate.now().toString()
                    // format startDate into "2022-01-01"
                    val endDate = LocalDate.now().plusWeeks(goal.duration_weeks.toLong()).toString()

                    tvStart.text = startDate
                    tvEnd.text = endDate
                    val id = PreferenceManager.getUserId(requireContext())
                    Log.d("CCC", id.toString())
                    val plan = Plan(
                        id,
                        goal.goal_type,
                        goal.target_weight.toDouble(),
                        goal.duration_weeks,
                        startDate,
                        endDate
                    )

                    btn_nextFragment.setOnClickListener {
                        generatePlan(plan)
                        Log.d("AAA", plan.toString())
                        startActivity(Intent(activity, HomeActivity::class.java))
                    }
                    Log.d("AAA", plan.toString())

                    val validateInputs = {
                        val targetWeight = edtTargetWeight.text.toString().toDoubleOrNull()
                        val durationWeeks = edtDurationWeeks.text.toString().toIntOrNull()

                        if (durationWeeks != null && targetWeight != null) {
                            if (targetWeight > 1.0 && durationWeeks > 4.0) {

                                if ((targetWeight / durationWeeks) < stardard) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Valid value",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    val startDate = LocalDate.now().toString()
                                    // format startDate into "2022-01-01"

                                    val endDate =
                                        LocalDate.now().plusWeeks(durationWeeks.toLong()).toString()

                                    tvStart.text = startDate
                                    tvEnd.text = endDate

                                    val id = PreferenceManager.getUserId(requireContext())
                                    val plan = Plan(
                                        id,
                                        goal.goal_type,
                                        targetWeight,
                                        durationWeeks,
                                        startDate,
                                        endDate
                                    )
                                    Log.d("AAA", plan.toString())

                                    btn_nextFragment.setOnClickListener {
                                        generatePlan(plan)
                                        startActivity(Intent(activity, HomeActivity::class.java))
                                    }

                                } else {

                                    val dialog = AlertDialog.Builder(requireContext())
                                    dialog.setTitle("Warning")
                                    dialog.setMessage("Value not suitable!!!")
                                    dialog.setPositiveButton("OK") { dialog, which ->
                                        dialog.dismiss()
                                    }
                                    dialog.show()
                                }
                            }
                        }
                    }

                    edtTargetWeight.addTextChangedListener {
                        validateInputs()
                    }

                    edtDurationWeeks.addTextChangedListener {
                        validateInputs()
                    }

                    Toast.makeText(context, "Predict successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed $response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Goal>, t: Throwable) {
                Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun generatePlan(plan: Plan) {
        ApiService.apiService.generatePlan(key, plan).enqueue(object : Callback<PlanStatus> {
            override fun onResponse(call: Call<PlanStatus>, response: Response<PlanStatus>) {
                if (response.isSuccessful) {
                    val planStatus = response.body()
                    PreferenceManager.saveGoalId(requireContext(), planStatus!!.goal_id)
                    Log.d("BBB", planStatus.toString())

                    Toast.makeText(context, "Plan generated successfully!", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Toast.makeText(context, "Failed $response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PlanStatus>, t: Throwable) {
                Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun saveUser(context: Context) {
        var id = -1
        ApiService.apiService.getUser(key!!).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        id = user.pk
                        PreferenceManager.saveUserId(context, id)

                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        }
        )

    }

}