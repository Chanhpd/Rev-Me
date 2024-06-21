package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fitme.R


class Page8Fragment : Fragment() {

    lateinit var btn_nextFragment: LinearLayout
    lateinit var btnPrevious: FrameLayout


    lateinit var tv_days: TextView
    lateinit var radio_group_ass8: RadioGroup


    var NCP = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page8, container, false)

        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)

        tv_days = view.findViewById(R.id.tv_days)

        radio_group_ass8 = view.findViewById(R.id.radio_group_ass8)

        radio_group_ass8.setOnCheckedChangeListener { radioGroup, i ->
            tv_days.text = view.findViewById<RadioButton>(i).text.toString() + "x"
            when (i) {
                R.id.radio_ass8_1 -> NCP = 1
                R.id.radio_ass8_2 -> NCP = 2
                R.id.radio_ass8_3 -> NCP = 3
                R.id.radio_ass8_4 -> NCP = 4

            }
        }

        btn_nextFragment.setOnClickListener {

          if(NCP == 0){
            Toast.makeText(requireActivity(), "Please fill in your occupation!", Toast.LENGTH_SHORT)
                .show()
            } else {
              val bundle = arguments // Sử dụng Bundle từ Fragment 1
              bundle?.putInt("ass_8", NCP)

              val fragment = Page9Fragment()
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


    fun goPreviousFragment() {

        requireActivity().supportFragmentManager.popBackStackImmediate()

    }

}