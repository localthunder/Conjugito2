package com.example.myapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.entities.relations.Tense

@Entity
data class FutureSubjunctive(
    @PrimaryKey(autoGenerate = false)
    override val infinitive: String,
    override val yo: String,
    override val tu: String,
    override val el: String,
    override val nosotros: String,
    override val vosotros: String,
    override val ellos: String
) : Tense(infinitive,yo,tu,el,nosotros,vosotros,ellos)
