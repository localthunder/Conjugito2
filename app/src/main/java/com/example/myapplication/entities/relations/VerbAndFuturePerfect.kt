package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.FuturePerfect
import com.example.myapplication.entities.PreteritePerfect
import com.example.myapplication.entities.Verb

data class VerbAndFuturePerfect(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val futurePerfect: FuturePerfect
) : VerbAndTense {
    override val tense: Tense
        get() = futurePerfect
    override val yo: String
        get() = futurePerfect.yo
    override val tu: String
        get() = futurePerfect.tu
    override val el: String
        get() = futurePerfect.el
    override val nosotros: String
        get() = futurePerfect.nosotros
    override val vosotros: String
        get() = futurePerfect.vosotros
    override val ellos: String
        get() = futurePerfect.ellos
}
