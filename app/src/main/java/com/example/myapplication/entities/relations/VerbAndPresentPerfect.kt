package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.PresentPerfect
import com.example.myapplication.entities.Preterite
import com.example.myapplication.entities.Verb

data class VerbAndPresentPerfect(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val presentPerfect: PresentPerfect
) : VerbAndTense {
    override val tense: Tense
        get() = presentPerfect
    override val yo: String
        get() = presentPerfect.yo
    override val tu: String
        get() = presentPerfect.tu
    override val el: String
        get() = presentPerfect.el
    override val nosotros: String
        get() = presentPerfect.nosotros
    override val vosotros: String
        get() = presentPerfect.vosotros
    override val ellos: String
        get() = presentPerfect.ellos
}
