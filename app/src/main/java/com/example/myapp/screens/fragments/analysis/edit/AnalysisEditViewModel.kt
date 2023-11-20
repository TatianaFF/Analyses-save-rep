package com.example.myapp.screens.fragments.analysis.edit

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
class AnalysisEditViewModel@Inject constructor(
    private val repository: AnalysisRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val idAnalysis = savedStateHandle.getLiveData<Long>(KEY_ID_ANALYSIS)
    val analysis: LiveData<Analysis> = idAnalysis.switchMap { id ->
        liveData(viewModelScope.coroutineContext) {
            emit(getAnalysisById(id))
        }
    }

    private suspend fun getAnalysisById(id: Long): Analysis = repository.getAnalysisById(id)

    companion object {
        private const val KEY_ID_ANALYSIS = "KEY_ID_ANALYSIS"

        fun getBundle(idAnalysis: Long) = bundleOf(KEY_ID_ANALYSIS to idAnalysis)
    }
}