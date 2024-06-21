package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fitme.R



class Page7Fragment : Fragment() {
    lateinit var btnPrevious: FrameLayout
    lateinit var btn_nextFragment: LinearLayout

    lateinit var radioGroup_ass7: RadioGroup



//    lateinit var seekArc: ArcSeekBar

    var FCVC = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_page7, container, false)
        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)
        btnPrevious = view.findViewById(R.id.btn_pre_ass)

        radioGroup_ass7 = view.findViewById(R.id.radioGroup_ass7)

        radioGroup_ass7.setOnCheckedChangeListener { radioGroup, i ->

            when (i) {
                R.id.radio_ass5_excellent -> FCVC = 3
                R.id.radio_ass5_great -> FCVC = 2
                R.id.radio_ass14_normal -> FCVC = 1

            }
        }

//        seekArc = view.findViewById(R.id.seekArc)
//        seekArc.onProgressChangedListener = ProgressListener { p0 ->
//            when (p0) {
//                0 -> {
//                    qualitySleep = 1
//                }
//                1 -> {
//                    qualitySleep = 2
//                }
//                2 -> {
//                    qualitySleep = 3
//                }
//                3 -> {
//                    qualitySleep = 4
//                }
//                4 -> {
//                    qualitySleep = 5
//                }
//                5 -> qualitySleep = 6
//                6 -> qualitySleep = 7
//                7 -> qualitySleep = 8
//                8 -> qualitySleep = 9
//                9 -> qualitySleep = 10
//
//
//            }
//            txtLevel.text = qualitySleep.toString()
//
//        }


        btn_nextFragment.setOnClickListener {

            if (FCVC == 0) {
                Toast.makeText(
                    requireActivity(),
                    "Please choose your quality of sleep!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val bundle = arguments // Sử dụng Bundle từ Fragment 1
                bundle?.putInt("ass_7", FCVC)

                val fragment = Page8Fragment()
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

    fun goPreviousFragment() {

        requireActivity().supportFragmentManager.popBackStackImmediate()

    }
}