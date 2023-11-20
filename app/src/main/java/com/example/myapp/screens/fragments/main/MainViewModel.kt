package com.example.myapp.screens.fragments.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.models.Organ
import com.example.myapp.models.OrganSystem
import com.example.myapp.repository.AnalysisRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AnalysisRepositoryImpl
): ViewModel() {

    val listOrganSystemOrgans = MutableLiveData<List<Pair<String, List<Organ>>>>()

    init {
        updateMapOSO()
    }

    private fun updateMapOSO() {
        viewModelScope.launch {
            val result = mutableListOf<Pair<String, List<Organ>>>()

            val listOS = getOrganSystems()
            listOS.forEach {  OS ->
                val organs = getOrgansByOrganSystemId(OS.id)
                result.add(Pair(OS.name, organs))
            }
            listOrganSystemOrgans.postValue(result)
        }
    }

    private suspend fun getOrganSystems(): List<OrganSystem> {
        return repository.getOrganSystems()
    }

    private suspend fun getOrgansByOrganSystemId(idOrganSystem: Long): List<Organ> {
        return repository.getOrgansByOrganSystemId(idOrganSystem)
    }
}