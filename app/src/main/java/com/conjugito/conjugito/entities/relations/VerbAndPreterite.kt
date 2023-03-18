package com.conjugito.conjugito.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.conjugito.conjugito.entities.Preterite
import com.conjugito.conjugito.entities.Verb

data class VerbAndPreterite(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val preterite: Preterite
) : VerbAndTense {
    override val tense: Tense
        get() = preterite
    override val yo: String
        get() = preterite.yo
    override val tu: String
        get() = preterite.tu
    override val el: String
        get() = preterite.el
    override val nosotros: String
        get() = preterite.nosotros
    override val vosotros: String
        get() = preterite.vosotros
    override val ellos: String
        get() = preterite.ellos
}
