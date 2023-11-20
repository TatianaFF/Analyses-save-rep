package com.example.myapp.screens.fragments.analysis.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.models.Analysis
import com.example.myapp.repository.AnalysisRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisBaseViewModel@Inject constructor(
    private val repository: AnalysisRepositoryImpl
) : ViewModel() {
    fun updateAnalysis(analysis: Analysis) = viewModelScope.launch { repository.updateAnalysis(analysis) }
}