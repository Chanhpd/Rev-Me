package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fitme.R
import com.shawnlin.numberpicker.NumberPicker


class Page14Fragment : Fragment() {

    lateinit var btn_nextFragment : LinearLayout
    lateinit var radioGroup_ass14 : RadioGroup

    lateinit var btnPrevious : FrameLayout

    var TUE = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_page14, container, false)

        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)

        radioGroup_ass14 = view.findViewById(R.id.radioGroup_ass14)

        radioGroup_ass14.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.radio_ass14_1 -> {
                    TUE = 2
                }
                R.id.radio_ass14_2 -> {
                    TUE = 1
                }
                R.id.radio_ass14_3 -> {
                    TUE = 0
                }
            }
        }


        btn_nextFragment.setOnClickListener {
            if (TUE == -1) {
                Toast.makeText(context, "Please pick one option!", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = arguments // Sử dụng Bundle từ Fragment 1
                bundle?.putInt("ass_14", TUE)

                val fragment = Page15Fragment()
                fragment.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flAssessment, fragment)
                    addToBackStack(null)
                    commit()
                }
            }
        }
        btnPrevious = view.findViewById(R.id.btn_pre_ass)
        btnPrevious.setOnClickListener {
            goPreviousFragment()
        }

        return view
    }
    fun goPreviousFragment(){

        requireActivity().supportFragmentManager.popBackStackImmediate()

    }

}