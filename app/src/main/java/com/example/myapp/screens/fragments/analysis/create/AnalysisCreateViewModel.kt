package com.example.myapp.screens.fragments.analysis.create

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.myapp.models.Analysis
import com.example.myapp.repository.AnalysisRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisCreateViewModel@Inject constructor(
    private val repository: AnalysisRepositoryImpl
) : ViewModel() {
}