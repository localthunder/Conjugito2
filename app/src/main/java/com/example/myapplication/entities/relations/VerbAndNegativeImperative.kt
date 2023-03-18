package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.NegativeImperative
import com.example.myapplication.entities.Verb

data class VerbAndNegativeImperative(
@Embedded
override val verb : Verb,
@Relation(
    parentColumn = "infinitive",
    entityColumn = "infinitive"
)
val negativeImperative: NegativeImperative
) : VerbAndTense {
    override val tense: Tense
    get() = negativeImperative
    override val yo: String
    get() = negativeImperative.yo
    override val tu: String
    get() = negativeImperative.tu
    override val el: String
    get() = negativeImperative.el
    override val nosotros: String
    get() = negativeImperative.nosotros
    override val vosotros: String
    get() = negativeImperative.vosotros
    override val ellos: String
    get() = negativeImperative.ellos
}
