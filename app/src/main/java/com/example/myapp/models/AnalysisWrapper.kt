package com.example.myapp.models

import androidx.room.ColumnInfo

data class AnalysisWrapper (
        @ColumnInfo(name = "id_analysis")
        val analysisId: Long,
        @ColumnInfo(name = "name_analysis")
        val analysisName: String,
        @ColumnInfo(name = "comment")
        val analysisComment: String? = null,
        @ColumnInfo(name = "file_path")
        val analysisFilePath: String? = null,
        @ColumnInfo(name = "name_organ")
        val organName: String,
        @ColumnInfo(name = "name_organ_system")
        val OrganSystemName: String
)