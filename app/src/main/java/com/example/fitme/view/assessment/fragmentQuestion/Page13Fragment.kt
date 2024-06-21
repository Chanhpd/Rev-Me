package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.fitme.R
import com.shawnlin.numberpicker.NumberPicker


class Page13Fragment : Fragment() {

    lateinit var btn_nextFragment: LinearLayout


    lateinit var btnPrevious: FrameLayout
    lateinit var radioGroup_ass13: RadioGroup
    var FAF = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page13, container, false)
        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)
        radioGroup_ass13 = view.findViewById(R.id.radioGroup_ass13)




        btnPrevious = view.findViewById(R.id.btn_pre_ass)

        radioGroup_ass13.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                R.id.radio_ass13_1 -> FAF = 3
                R.id.radio_ass13_2 ->  FAF = 2
                R.id.radio_ass13_3 ->  FAF = 1
                R.id.radio_ass13_4 ->  FAF = 0
            }
        }

        btnPrevious.setOnClickListener {

            goPreviousFragment()
        }
        btn_nextFragment.setOnClickListener {

            if (FAF == -1) {
                Toast.makeText(context, "Please choose the systolicBP!!", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = arguments // Sử dụng Bundle từ Fragment 1
                bundle?.putInt("ass_13", FAF)

                val fragment = Page14Fragment()
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

    fun goPreviousFragment() {

        requireActivity().supportFragmentManager.popBackStackImmediate()

    }

}