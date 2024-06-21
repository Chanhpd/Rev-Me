package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.fitme.R
import com.shawnlin.numberpicker.NumberPicker


class Page3Fragment : Fragment() {

    lateinit var btn_nextFragment: LinearLayout
    lateinit var txtWeight: TextView
    lateinit var numberPicker: NumberPicker

    lateinit var btnPrevious : FrameLayout
    var weight = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_page3, container, false)
        txtWeight = view.findViewById(R.id.txtWeight)
        numberPicker = view.findViewById(R.id.number_picker)
        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)
        btnPrevious = view.findViewById(R.id.btn_pre_ass)

        numberPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            weight = newVal
            txtWeight.text = newVal.toString()
        })



        btn_nextFragment.setOnClickListener {
            if (weight == 0) {
                Toast.makeText(requireActivity(), "Select your weight!", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = arguments // Sử dụng Bundle từ Fragment 1
                bundle?.putInt("ass_3", weight)

                val fragment = Page4Fragment()
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