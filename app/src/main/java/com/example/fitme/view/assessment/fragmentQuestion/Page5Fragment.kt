package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fitme.R


class Page5Fragment : Fragment() {

//    lateinit var edtOccupation: EditText
    lateinit var btnPrevious: FrameLayout
    lateinit var btn_nextFragment: LinearLayout
    lateinit var radioGroup_ass5: RadioGroup
    var calc = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_page5, container, false)

        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)
        btnPrevious = view.findViewById(R.id.btn_pre_ass)

        radioGroup_ass5 = view.findViewById(R.id.radioGroup_ass5)


        radioGroup_ass5.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)

            calc = selectedRadioButton.text.toString()
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

        if (calc == "") {
            Toast.makeText(requireActivity(), "Please fill in your occupation!", Toast.LENGTH_SHORT)
                .show()
        } else {
            val bundle = arguments // Sử dụng Bundle từ Fragment 1
            bundle?.putString("ass_5", calc)

            val fragment = Page6Fragment()
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