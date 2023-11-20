package com.example.myapp.repository

import androidx.lifecycle.LiveData
import com.example.myapp.models.Analysis
import com.example.myapp.models.AnalysisWrapper
import com.example.myapp.models.Organ
import com.example.myapp.models.OrganSystem

interface AnalysisRepository {
    suspend fun insertAnalysis(analysis: Analysis)
    suspend fun getAnalyses(): List<Analysis>
    fun getAnalysesByIdOrgan(idOrgan: Long): LiveData<List<Analysis>>
    suspend fun deleteAnalysis(id: Long)
    suspend fun updateAnalysis(analysis: Analysis)
    suspend fun getAnalysisById(id: Long): Analysis

    suspend fun getOrganSystems(): List<OrganSystem>

    suspend fun getOrgansByOrganSystemId(idOS: Long): List<Organ>
}