package com.conjugito.conjugito.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.conjugito.conjugito.entities.ImperfectSubjunctiveRa
import com.conjugito.conjugito.entities.Verb

data class VerbAndImperfectSubjunctiveRa(
    @Embedded
    override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val imperfectSubjunctiveRa: ImperfectSubjunctiveRa
) : VerbAndTense {
    override val tense: Tense
        get() = imperfectSubjunctiveRa
    override val yo: String
        get() = imperfectSubjunctiveRa.yo
    override val tu: String
        get() = imperfectSubjunctiveRa.tu
    override val el: String
        get() = imperfectSubjunctiveRa.el
    override val nosotros: String
        get() = imperfectSubjunctiveRa.nosotros
    override val vosotros: String
        get() = imperfectSubjunctiveRa.vosotros
    override val ellos: String
        get() = imperfectSubjunctiveRa.ellos
}
