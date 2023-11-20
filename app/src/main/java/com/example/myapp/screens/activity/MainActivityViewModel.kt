package com.example.myapp.screens.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapp.models.OrganSystem
import com.example.myapp.repository.AnalysisRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: AnalysisRepositoryImpl
): ViewModel() {

}