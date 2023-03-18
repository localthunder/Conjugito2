package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.PresentSubjunctive
import com.example.myapplication.entities.Verb

data class VerbAndPresentSubjunctive(
@Embedded
override val verb : Verb,
@Relation(
    parentColumn = "infinitive",
    entityColumn = "infinitive"
)
val presentSubjunctive: PresentSubjunctive
) : VerbAndTense {
    override val tense: Tense
    get() = presentSubjunctive
    override val yo: String
    get() = presentSubjunctive.yo
    override val tu: String
    get() = presentSubjunctive.tu
    override val el: String
    get() = presentSubjunctive.el
    override val nosotros: String
    get() = presentSubjunctive.nosotros
    override val vosotros: String
    get() = presentSubjunctive.vosotros
    override val ellos: String
    get() = presentSubjunctive.ellos
}
