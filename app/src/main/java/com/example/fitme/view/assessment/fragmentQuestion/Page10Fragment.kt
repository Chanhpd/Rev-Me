package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fitme.R
import com.shawnlin.numberpicker.NumberPicker


class Page10Fragment : Fragment() {

    lateinit var btn_nextFragment: LinearLayout

    lateinit var btnPrevious: FrameLayout

    lateinit var btn_no: LinearLayout
    lateinit var btn_yes: LinearLayout

    var SMOKE = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page10, container, false)


        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)

        btnPrevious = view.findViewById(R.id.btn_pre_ass)

        btn_yes = view.findViewById(R.id.btn_yes)
        btn_no = view.findViewById(R.id.btn_no)

        btn_yes.setOnClickListener {
            SMOKE = "yes"
            goNextPage()
        }

        btn_no.setOnClickListener {
            SMOKE = "no"
            goNextPage()
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
        bundle?.putString("ass_10", SMOKE)

        val fragment = Page11Fragment()
        fragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flAssessment, fragment)
            addToBackStack(null)
            commit()
        }

    }

}