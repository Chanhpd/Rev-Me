package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitme.R

class Page6Fragment : Fragment() {

    lateinit var btn_nextFragment: LinearLayout
    lateinit var radioGroup: RadioGroup

    lateinit var btnPrevious: FrameLayout

    lateinit var btn_no: LinearLayout
    lateinit var btn_yes: LinearLayout

    var favc = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_page6, container, false)

        btnPrevious = view.findViewById(R.id.btn_pre_ass)
        btn_yes = view.findViewById(R.id.btn_yes)
        btn_no = view.findViewById(R.id.btn_no)

//        radioGroup = view.findViewById(R.id.radioGroup_ass6)
//
//        radioGroup.setOnCheckedChangeListener { group, checkedId ->
//
//            when (checkedId) {
//                R.id.radio_ass14_excellent -> selected = 8
//                R.id.radio_ass14_great -> selected = 7
//                R.id.radio_ass14_normal -> selected = 6
//                R.id.radio_ass14_bad -> selected = 3
//                R.id.radio_ass14_insomniac -> selected = 2
//            }
//
//        }

        btn_yes.setOnClickListener {
            favc = "yes"
            goNextPage()
        }
        btn_no.setOnClickListener {
            favc = "no"
            goNextPage()
        }

        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)

        btn_nextFragment.setOnClickListener {

        }
        btnPrevious.setOnClickListener {

            goPreviousFragment()
        }

        return view

    }

    fun goPreviousFragment() {

        requireActivity().supportFragmentManager.popBackStackImmediate()

    }

    fun goNextPage() {

        val bundle = arguments // Sử dụng Bundle từ Fragment 1
        bundle?.putString("ass_6", favc)

        val fragment = Page7Fragment()
        fragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flAssessment, fragment)
            addToBackStack(null)
            commit()
        }

    }
}