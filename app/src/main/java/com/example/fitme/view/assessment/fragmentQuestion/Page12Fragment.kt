package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.adapter.Page12AssessmentAdapter


class Page12Fragment : Fragment() {

    lateinit var btn_nextFragment: LinearLayout

    lateinit var btnPrevious : FrameLayout


    lateinit var btn_no: LinearLayout
    lateinit var btn_yes: LinearLayout


    var family_history_with_overweight= ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page12, container, false)

        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)
        btn_yes = view.findViewById(R.id.btn_yes)
        btn_no = view.findViewById(R.id.btn_no)

        btn_yes.setOnClickListener {
            family_history_with_overweight = "yes"
            goNextPage()
        }

        btn_no.setOnClickListener {
            family_history_with_overweight = "no"
            goNextPage()
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
    fun goNextPage() {

        val bundle = arguments // Sử dụng Bundle từ Fragment 1
        bundle?.putString("ass_12", family_history_with_overweight)

        val fragment = Page13Fragment()
        fragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flAssessment, fragment)
            addToBackStack(null)
            commit()
        }

    }

}