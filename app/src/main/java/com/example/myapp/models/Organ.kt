package com.example.myapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "organ",
    indices = [Index("id_organ")],
    foreignKeys = [
        ForeignKey(
            entity = OrganSystem::class,
            parentColumns = ["id_organ_system"],
            childColumns = ["organ_system"]
        )
    ]
)
data class Organ (
    @PrimaryKey
    @ColumnInfo(name = "id_organ")
    val id: Long,
    @ColumnInfo(name = "name_organ")
    val name: String,
    @ColumnInfo(name = "organ_system")
    val organSystem: Long
)