package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.ConditionalPerfect
import com.example.myapplication.entities.Verb

data class VerbAndConditionalPerfect(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val conditionalPerfect: ConditionalPerfect
) : VerbAndTense {
    override val tense: Tense
        get() = conditionalPerfect
    override val yo: String
        get() = conditionalPerfect.yo
    override val tu: String
        get() = conditionalPerfect.tu
    override val el: String
        get() = conditionalPerfect.el
    override val nosotros: String
        get() = conditionalPerfect.nosotros
    override val vosotros: String
        get() = conditionalPerfect.vosotros
    override val ellos: String
        get() = conditionalPerfect.ellos
}
