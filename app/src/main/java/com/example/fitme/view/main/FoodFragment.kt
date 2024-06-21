package com.example.fitme.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fitme.R
import com.shawnlin.numberpicker.NumberPicker
import java.util.*


class FoodFragment : Fragment() {

    lateinit var numberPicker: NumberPicker
    lateinit var textViewMonth : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_food, container, false)

        initView(view)
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)

        numberPicker.value = day
        // max of month
        numberPicker.maxValue = 31

        // don't allow user use number picker
        numberPicker.wrapSelectorWheel = false

        // format month
        textViewMonth.text =  String.format("%02d", month + 1)


        return view
    }

    fun initView(view: View){
        numberPicker = view.findViewById(R.id.numberPicker_food)
        textViewMonth = view.findViewById(R.id.textView_month)
    }

}