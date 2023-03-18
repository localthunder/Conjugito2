package com.conjugito.conjugito.entities.relations

import com.conjugito.conjugito.entities.Verb

interface VerbAndTense {
    val verb : Verb
    val tense : Tense
    val yo: String
    val tu: String
    val el: String
    val nosotros: String
    val vosotros: String
    val ellos: String
}

