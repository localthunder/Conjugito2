package com.conjugito.conjugito.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PreteriteProgressive(
    @PrimaryKey(autoGenerate = false)
    val infinitive: String,
    val yo: String,
    val tu: String,
    val el: String,
    val nosotros: String,
    val vosotros: String,
    val ellos: String
)
