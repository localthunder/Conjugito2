package com.example.myapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FuturePerfectSubjunctive(
    @PrimaryKey(autoGenerate = false)
    val infinitive: String,
    val yo: String,
    val tu: String,
    val el: String,
    val nosotros: String,
    val vosotros: String,
    val ellos: String
)
