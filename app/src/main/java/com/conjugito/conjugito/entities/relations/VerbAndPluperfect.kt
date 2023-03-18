package com.conjugito.conjugito.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.conjugito.conjugito.entities.Pluperfect
import com.conjugito.conjugito.entities.Verb

data class VerbAndPluperfect(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val pluperfect: Pluperfect
) : VerbAndTense {
    override val tense: Tense
        get() = pluperfect
    override val yo: String
        get() = pluperfect.yo
    override val tu: String
        get() = pluperfect.tu
    override val el: String
        get() = pluperfect.el
    override val nosotros: String
        get() = pluperfect.nosotros
    override val vosotros: String
        get() = pluperfect.vosotros
    override val ellos: String
        get() = pluperfect.ellos
}
