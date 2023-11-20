package com.example.myapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "analysis",
    indices = [Index("id_analysis")],
    foreignKeys = [
        ForeignKey(
            entity = Organ::class,
            parentColumns = ["id_organ"],
            childColumns = ["organ"]
        )
    ]
)
data class Analysis (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_analysis")
    val id: Long,
    @ColumnInfo(name = "name_analysis")
    var name: String,
    var comment: String? = null,
    @ColumnInfo(name = "file_path")
    var filePath: String? = null,
    @ColumnInfo
    var organ: Long
) {
}