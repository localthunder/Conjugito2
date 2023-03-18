package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.ImperfectSubjunctiveSe
import com.example.myapplication.entities.Verb

data class VerbAndImperfectSubjunctiveSe(
    @Embedded
    override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val imperfectSubjunctiveSe: ImperfectSubjunctiveSe
) : VerbAndTense {
    override val tense: Tense
        get() = imperfectSubjunctiveSe
    override val yo: String
        get() = imperfectSubjunctiveSe.yo
    override val tu: String
        get() = imperfectSubjunctiveSe.tu
    override val el: String
        get() = imperfectSubjunctiveSe.el
    override val nosotros: String
        get() = imperfectSubjunctiveSe.nosotros
    override val vosotros: String
        get() = imperfectSubjunctiveSe.vosotros
    override val ellos: String
        get() = imperfectSubjunctiveSe.ellos
}
