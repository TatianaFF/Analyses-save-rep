package com.example.myapp.screens.fragments.list_analyses

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.myapp.models.Analysis
import com.example.myapp.repository.AnalysisRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAnalysesViewModel @Inject constructor(
    private val repository: AnalysisRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val idOrgan = savedStateHandle.getLiveData<Long>(KEY_ID_ORGAN).value ?: throw IllegalArgumentException("Не удалось получить id органа")
    val analyses: LiveData<List<Analysis>>
        get() = getAnalysesByIdOrgan(idOrgan)
//            liveData(viewModelScope.coroutineContext) {
//            Log.e("VM", "get")
//            emit(getAnalysesByIdOrgan(idOrgan))
//        }
//            idOrgan.switchMap {  id ->
//            Log.e("VM", "get")
//            liveData(viewModelScope.coroutineContext) {
//                emit(getAnalysesByIdOrgan(id))
//            }
//        }

//    init {
//
//    }

    fun deleteAnalysis(idAnalysis: Long) = viewModelScope.launch { repository.deleteAnalysis(idAnalysis) }

    private fun getAnalysesByIdOrgan(idOrgan: Long): LiveData<List<Analysis>> {
        return repository.getAnalysesByIdOrgan(idOrgan)
    }

    companion object {
        private const val KEY_ID_ORGAN = "ID_ORGAN"
    }
}