package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fitme.R


class Page16Fragment : Fragment() {

    lateinit var radioGroup_ass16: RadioGroup
    lateinit var btnPrevious: FrameLayout
    lateinit var btn_nextFragment: LinearLayout

    var MTRANS = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page16, container, false)

        radioGroup_ass16 = view.findViewById(R.id.radioGroup_ass16)
        btnPrevious = view.findViewById(R.id.btn_pre_ass)
        btn_nextFragment = view.findViewById(R.id.btn_nextFragment)

        radioGroup_ass16.setOnCheckedChangeListener { radioGroup, i ->
            val radio: RadioButton = view.findViewById(i)
            MTRANS  = radio.text.toString()
        }
        btnPrevious.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }

        btn_nextFragment.setOnClickListener {
            if (MTRANS == "") {
                Toast.makeText(context, "Please pick one option!", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = arguments // Sử dụng Bundle từ Fragment 1
                bundle?.putString("ass_16", MTRANS)

                val fragment = Page17Fragment()
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


}