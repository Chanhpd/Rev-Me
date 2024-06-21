package com.example.fitme.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModelAssessment : ViewModel() {
    val weight = MutableLiveData<Int>()

}