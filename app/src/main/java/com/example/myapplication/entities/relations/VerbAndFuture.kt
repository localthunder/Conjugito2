package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.Future
import com.example.myapplication.entities.Preterite
import com.example.myapplication.entities.Verb

data class VerbAndFuture(
    @Embedded override val verb : Verb,
    @Relation(
        parentColumn = "infinitive",
        entityColumn = "infinitive"
    )
    val future: Future
) : VerbAndTense {
    override val tense: Tense
        get() = future
    override val yo: String
        get() = future.yo
    override val tu: String
        get() = future.tu
    override val el: String
        get() = future.el
    override val nosotros: String
        get() = future.nosotros
    override val vosotros: String
        get() = future.vosotros
    override val ellos: String
        get() = future.ellos
}
