package com.conjugito.conjugito.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserPracticeSettings(
    @PrimaryKey(autoGenerate = false) val uniqueId: Int = 123,
    var showIrregularVerbs : Boolean = true,
    var showReflexiveVerbs: Boolean = true,
    var showUncommonVerbs: Boolean = false,
    var showPresentTense: Boolean = true,
    var showPreteriteTense: Boolean = true,
    var showFutureTense: Boolean = true,
    var showConditionalTense: Boolean = true,
    var showImperfectTense: Boolean = true,
    var showPresentProgressive: Boolean = true,
    var showPreteriteProgressive: Boolean = true,
    var showImperfectProgressive: Boolean = true,
    var showConditionalProgressive: Boolean = true,
    var showFutureProgressive: Boolean = true,
    var showPresentPerfect: Boolean = true,
    var showPreteritePerfect: Boolean = true,
    var showPluperfect: Boolean = true,
    var showConditionalPerfect: Boolean = true,
    var showFuturePerfect: Boolean = true,
    var showPresentSubjunctive: Boolean = true,
    var showImperfectSubjunctiveRa: Boolean = true,
    var showImperfectSubjunctiveSe: Boolean = true,
    var showFutureSubjunctive: Boolean = true,
    var showPresentPerfectSubjunctive: Boolean = true,
    var showPluperfectSubjunctive: Boolean = true,
    var showFuturePerfectSubjunctive: Boolean = true,
    var showImperative: Boolean = true,
    var showNegativeImperative: Boolean = true
)
