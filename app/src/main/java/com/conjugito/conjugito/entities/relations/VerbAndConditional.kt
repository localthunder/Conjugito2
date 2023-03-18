package com.conjugito.conjugito.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.conjugito.conjugito.entities.Conditional
import com.conjugito.conjugito.entities.Verb

data class VerbAndConditional(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val conditional: Conditional
) : VerbAndTense {
    override val tense: Tense
        get() = conditional
    override val yo: String
        get() = conditional.yo
    override val tu: String
        get() = conditional.tu
    override val el: String
        get() = conditional.el
    override val nosotros: String
        get() = conditional.nosotros
    override val vosotros: String
        get() = conditional.vosotros
    override val ellos: String
        get() = conditional.ellos
}
