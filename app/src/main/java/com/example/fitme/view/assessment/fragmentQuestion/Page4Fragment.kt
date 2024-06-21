package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.example.fitme.R
import com.shawnlin.numberpicker.NumberPicker

class Page4Fragment : Fragment() {
    lateinit var btnPrevious : FrameLayout
    lateinit var btn_nextFragment: LinearLayout
    lateinit var numberPicker: NumberPicker
     var age = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_page4, container, false)

        numberPicker = view.findViewById(R.id.number_picker)
        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)
        btnPrevious = view.findViewById(R.id.btn_pre_ass)

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            age = newVal
        }

        btn_nextFragment.setOnClickListener {
            if (age == 0) {
                Toast.makeText(requireActivity(), "Select your weight!", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = arguments // Sử dụng Bundle từ Fragment 1
                bundle?.putInt("ass_4", age)

                val fragment = Page5Fragment()
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

        return view
    }
    fun goPreviousFragment(){

        requireActivity().supportFragmentManager.popBackStackImmediate()

    }

}