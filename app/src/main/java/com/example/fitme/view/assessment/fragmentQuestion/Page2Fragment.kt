package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fitme.R


class Page2Fragment : Fragment() {

    lateinit var btnPrevious: FrameLayout

    lateinit var linearLayoutManager: LinearLayout
    lateinit var btnMale: ConstraintLayout
    lateinit var btnFemale: ConstraintLayout

    lateinit var checkBoxMale: CheckBox
    lateinit var checkBoxFemale: CheckBox

    var selectedGender = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_page2, container, false)

        linearLayoutManager = view.findViewById(R.id.btn_nextFragment)

        btnMale = view.findViewById(R.id.btn_ass2_male)
        btnFemale = view.findViewById(R.id.btn_ass2_female)
        btnPrevious = view.findViewById(R.id.btn_pre_ass)
        checkBoxMale = view.findViewById(R.id.checkBoxMale)
        checkBoxFemale = view.findViewById(R.id.checkBoxFemale)

        linearLayoutManager.setOnClickListener {
            if (selectedGender == "") {
                Toast.makeText(requireActivity(), "Select your gender!", Toast.LENGTH_SHORT).show()
            } else {

                val bundle = arguments // Sử dụng Bundle từ Fragment 1

                bundle?.putString("ass_2", selectedGender)

                val fragment = Page3Fragment()
                fragment.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flAssessment, fragment)
                    addToBackStack(null)
                    commit()
                }
            }
        }

        btnPrevious.setOnClickListener {

            goPreviousFragment()
        }

        chooseGender()
        return view
    }

    fun goPreviousFragment() {

        requireActivity().supportFragmentManager.popBackStackImmediate()

    }

    fun chooseGender() {
        btnMale.setOnClickListener {
            btnMale.setBackgroundResource(R.drawable.bg_32_app_color)
            btnFemale.setBackgroundResource(R.drawable.bg_32_border)
            checkBoxMale.isChecked = true
            checkBoxFemale.isChecked = false

            selectedGender = "Male"
        }
        btnFemale.setOnClickListener {
            btnFemale.setBackgroundResource(R.drawable.bg_32_app_color)
            btnMale.setBackgroundResource(R.drawable.bg_32_border)
            checkBoxMale.isChecked = false
            checkBoxFemale.isChecked = true

            selectedGender = "Female"
        }
    }

}