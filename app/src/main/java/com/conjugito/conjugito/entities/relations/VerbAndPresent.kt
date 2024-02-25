package com.conjugito.conjugito.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.conjugito.conjugito.entities.Present
import com.conjugito.conjugito.entities.Verb

data class VerbAndPresent(
    @Embedded override val verb : Verb,
    @Relation (
        parentColumn = "infinitive",
        entityColumn = "infinitive"
            )
    val present: Present
) : VerbAndTense {
    override val tense: Tense
        get() = present
    override val yo: String
        get() = present.yo
    override val tu: String
        get() = present.tu
    override val el: String
        get() = present.el
    override val nosotros: String
        get() = present.nosotros
    override val vosotros: String
        get() = present.vosotros
    override val ellos: String
        get() = present.ellos
}




