package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.PresentPerfect
import com.example.myapplication.entities.PreteritePerfect
import com.example.myapplication.entities.Verb

data class VerbAndPreteritePerfect(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val preteritePerfect: PreteritePerfect
) : VerbAndTense {
    override val tense: Tense
        get() = preteritePerfect
    override val yo: String
        get() = preteritePerfect.yo
    override val tu: String
        get() = preteritePerfect.tu
    override val el: String
        get() = preteritePerfect.el
    override val nosotros: String
        get() = preteritePerfect.nosotros
    override val vosotros: String
        get() = preteritePerfect.vosotros
    override val ellos: String
        get() = preteritePerfect.ellos
}
