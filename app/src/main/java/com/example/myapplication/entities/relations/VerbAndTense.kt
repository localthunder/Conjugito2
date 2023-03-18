package com.example.myapplication.entities.relations

import com.example.myapplication.entities.Verb

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

