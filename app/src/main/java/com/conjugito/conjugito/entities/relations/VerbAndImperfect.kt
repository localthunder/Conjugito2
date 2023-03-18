package com.conjugito.conjugito.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.conjugito.conjugito.entities.Imperfect
import com.conjugito.conjugito.entities.Verb

data class VerbAndImperfect(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val imperfect: Imperfect
) : VerbAndTense {
    override val tense: Tense
        get() = imperfect
    override val yo: String
        get() = imperfect.yo
    override val tu: String
        get() = imperfect.tu
    override val el: String
        get() = imperfect.el
    override val nosotros: String
        get() = imperfect.nosotros
    override val vosotros: String
        get() = imperfect.vosotros
    override val ellos: String
        get() = imperfect.ellos
}