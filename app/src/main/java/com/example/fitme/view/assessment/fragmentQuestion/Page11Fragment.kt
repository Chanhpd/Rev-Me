package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fitme.R


class Page11Fragment : Fragment() {

    lateinit var btnPrevious: FrameLayout
    lateinit var btn_nextFragment: LinearLayout

    lateinit var tv_days : TextView
    lateinit var radio_group_ass11 : RadioGroup
    var CH2O = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page11, container, false)


        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)

        btnPrevious = view.findViewById(R.id.btn_pre_ass)

        tv_days = view.findViewById(R.id.tv_days)
        radio_group_ass11 = view.findViewById(R.id.radio_group_ass11)

        radio_group_ass11.setOnCheckedChangeListener { radioGroup, i ->
            val selectedRadioButton = view.findViewById<RadioButton>(i)

            tv_days.text = selectedRadioButton.text.toString() + "x"
            CH2O = selectedRadioButton.text.toString().toInt()

        }

        btnPrevious.setOnClickListener {

            goPreviousFragment()
        }

        btn_nextFragment.setOnClickListener {
            goNextPage()
        }
        return view
    }

    fun goNextPage() {

        if(CH2O==0){
            Toast.makeText(context, "Please pick your steps each everyday!!", Toast.LENGTH_SHORT).show()
        } else {
            val bundle = arguments // Sử dụng Bundle từ Fragment 1
            bundle?.putInt("ass_11", CH2O)

            val fragment = Page12Fragment()
            fragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.flAssessment, fragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    fun goPreviousFragment() {

        requireActivity().supportFragmentManager.popBackStackImmediate()

    }

}