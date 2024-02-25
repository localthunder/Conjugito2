package com.conjugito.conjugito.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.conjugito.conjugito.entities.relations.Tense

@Entity
data class Pluperfect(
    @PrimaryKey(autoGenerate = false)
    override val infinitive: String,
    override val yo: String,
    override val tu: String,
    override val el: String,
    override val nosotros: String,
    override val vosotros: String,
    override val ellos: String
) : Tense(infinitive,yo,tu,el,nosotros,vosotros,ellos)
