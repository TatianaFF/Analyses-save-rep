package com.example.myapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organ_system")
data class OrganSystem (
    @PrimaryKey
    @ColumnInfo(name = "id_organ_system")
    val id: Long,
    @ColumnInfo(name = "name_organ_system")
    val name: String
)