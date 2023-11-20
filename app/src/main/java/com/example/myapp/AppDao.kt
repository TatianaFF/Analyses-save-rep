package com.example.myapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapp.models.Analysis
import com.example.myapp.models.AnalysisWrapper
import com.example.myapp.models.Organ
import com.example.myapp.models.OrganSystem

@Dao
interface AppDao {
    @Insert(entity = Analysis::class)
    fun insertAnalysis(analysis: Analysis)

    @Query("SELECT * FROM analysis")
    fun getAnalyses(): List<Analysis>

    @Query("SELECT * FROM analysis WHERE id_analysis = :id")
    fun getAnalysisById(id: Long): Analysis

    @Query("SELECT * FROM analysis WHERE organ = :idOrgan")
    fun getAnalysesByIdOrgan(idOrgan: Long): LiveData<List<Analysis>>

    @Query("DELETE FROM analysis WHERE id_analysis = :id")
    fun deleteAnalysis(id: Long)

    @Query("SELECT * FROM organ_system")
    fun getOrganSystems(): List<OrganSystem>

    @Query("SELECT * FROM organ " +
            "INNER JOIN organ_system ON organ_system = id_organ_system " +
            "WHERE organ_system = :idOS")
    fun getOrgansByOrganSystemId(idOS: Long): List<Organ>

//    @Transaction
    @Update
    fun updateAnalysis(analysis: Analysis) {
        deleteAnalysis(analysis.id)
        insertAnalysis(analysis)
    }
}