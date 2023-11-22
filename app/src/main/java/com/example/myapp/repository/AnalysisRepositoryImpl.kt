package com.example.myapp.repository

import androidx.lifecycle.LiveData
import com.example.myapp.AppDao
import com.example.myapp.models.Analysis
import com.example.myapp.models.AnalysisWrapper
import com.example.myapp.models.Organ
import com.example.myapp.models.OrganSystem
import javax.inject.Inject

class AnalysisRepositoryImpl@Inject constructor(private val dao: AppDao): AnalysisRepository {
    override suspend fun insertAnalysis(analysis: Analysis) {
        dao.insertAnalysis(analysis)
    }

    override suspend fun getAnalyses(): List<Analysis> {
        return dao.getAnalyses()
    }

    override fun getAnalysesByIdOrgan(idOrgan: Long): LiveData<List<Analysis>> {
        return dao.getAnalysesByIdOrgan(idOrgan)
    }

    override suspend fun deleteAnalysis(id: Long) {
        dao.deleteAnalysis(id)
    }

    override suspend fun getOrganSystems(): List<OrganSystem> {
        return dao.getOrganSystems()
    }

    override suspend fun getOrgansByOrganSystemId(idOS: Long): List<Organ> {
        return dao.getOrgansByOrganSystemId(idOS)
    }

    override suspend fun updateAnalysis(analysis: Analysis) {
        dao.updateAnalysis(analysis)
    }

    override suspend fun getAnalysisById(id: Long): Analysis {
        return dao.getAnalysisById(id)
    }

    override fun getOrganById(id: Long): LiveData<Organ> {
        return dao.getOrganById(id)
    }
}