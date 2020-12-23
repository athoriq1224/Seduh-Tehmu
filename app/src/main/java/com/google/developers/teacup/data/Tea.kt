package com.google.developers.teacup.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A Model class that holds information about the tea.
 * This class defines table for the Room database with primary key {@see #mCode}
 * @see Entity
 */
@Entity(tableName = "tea")
data class Tea(
    @PrimaryKey (autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val type: String,
    val origin: String,
    val steepTimeMs: Long,
    val description: String,
    val ingredients: String,
    val caffeineLevel: String,
    @ColumnInfo(name = "favorite")
    val isFavorite: Boolean = false

)
