package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.Imperative
import com.example.myapplication.entities.Verb

data class VerbAndImperative(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val imperative: Imperative
) : VerbAndTense {
    override val tense: Tense
        get() = imperative
    override val yo: String
        get() = imperative.yo
    override val tu: String
        get() = imperative.tu
    override val el: String
        get() = imperative.el
    override val nosotros: String
        get() = imperative.nosotros
    override val vosotros: String
        get() = imperative.vosotros
    override val ellos: String
        get() = imperative.ellos
}
