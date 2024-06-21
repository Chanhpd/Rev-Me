package com.example.fitme.view.assessment.fragmentQuestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitme.R
import com.shawnlin.numberpicker.NumberPicker

class Page1Fragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayout

//    lateinit var radioGroup: RadioGroup
    lateinit var number_picker: NumberPicker

    var height = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page1, container, false)

        linearLayoutManager = view.findViewById(R.id.btn_nextFragment)
//        radioGroup= view.findViewById(R.id.radioGroup_ass1)
        number_picker = view.findViewById(R.id.number_picker)



        number_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            height = newVal.toFloat()/100
        }


        linearLayoutManager.setOnClickListener {

            if(height == 0f){
                Toast.makeText(requireActivity(), "Please choose your height!", Toast.LENGTH_SHORT).show()
            }
            else{
                val bundle = Bundle()
                bundle.putFloat("ass_1", height)

                val fragment2 = Page2Fragment()
                fragment2.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flAssessment, fragment2)
                    addToBackStack(null) // Add to back stack if you want to navigate back
                    commit()
                }
            }
        }
//        radioGroup.setOnCheckedChangeListener { group, checkedId ->
//            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)
//            selected = selectedRadioButton.text.toString()
//
//        }


        return view
    }


}