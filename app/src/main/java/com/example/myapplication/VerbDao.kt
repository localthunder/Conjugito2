package com.example.myapplication

import androidx.room.*
import com.example.myapplication.entities.*
import com.example.myapplication.entities.relations.*

@Dao
interface VerbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVerb (verb: Verb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPresentTense (present: Present)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreterite (preterite: Preterite)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuture (future: Future)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConditional (conditional: Conditional)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImperfect (imperfect: Imperfect)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPresentProgressive (presentProgressive: PresentProgressive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreteriteProgressive (preteriteProgressive: PreteriteProgressive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImperfectProgressive (imperfectProgressive: ImperfectProgressive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConditionalProgressive (conditionalProgressive: ConditionalProgressive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFutureProgressive (futureProgressive: FutureProgressive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPresentPerfect (presentPerfect: PresentPerfect)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreteritePerfect (preteritePerfect: PreteritePerfect)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPluperfect (pluperfect: Pluperfect)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConditionalPerfect (conditionalPerfect: ConditionalPerfect)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuturePerfect (futurePerfect: FuturePerfect)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPresentSubjunctive (presentSubjunctive: PresentSubjunctive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImperfectSubjunctiveRa (imperfectSubjunctiveRa: ImperfectSubjunctiveRa)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImperfectSubjunctiveSe (imperfectSubjunctiveSe: ImperfectSubjunctiveSe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFutureSubjunctive (futureSubjunctive: FutureSubjunctive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPresentPerfectSubjunctive (presentPerfectSubjunctive: PresentPerfectSubjunctive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPluperfectSubjunctive (pluperfectSubjunctive: PluperfectSubjunctive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuturePerfectSubjunctive (futurePerfectSubjunctive: FuturePerfectSubjunctive)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImperative (imperative: Imperative)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNegativeImperative (negativeImperative: NegativeImperative)

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndPresentWithInfinitive(infinitive: String): List<VerbAndPresent>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndPreteriteWithInfinitive(infinitive: String): List<VerbAndPreterite>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndFutureWithInfinitive(infinitive: String): List<VerbAndFuture>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndImperfectWithInfinitive(infinitive: String): List<VerbAndImperfect>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndConditionalWithInfinitive(infinitive: String): List<VerbAndConditional>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndPresentPerfectWithInfinitive(infinitive: String): List<VerbAndPresentPerfect>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndPreteritePerfectWithInfinitive(infinitive: String): List<VerbAndPreteritePerfect>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndFuturePerfectWithInfinitive(infinitive: String): List<VerbAndFuturePerfect>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndConditionalPerfectWithInfinitive(infinitive: String): List<VerbAndConditionalPerfect>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndPluperfectWithInfinitive(infinitive: String): List<VerbAndPluperfect>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndPresentSubjunctive(infinitive: String): List<VerbAndPresentSubjunctive>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndImperfectSubjunctiveRa(infinitive: String): List<VerbAndImperfectSubjunctiveRa>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndImperfectSubjunctiveSe(infinitive: String): List<VerbAndImperfectSubjunctiveSe>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndImperativeWithInfinitive(infinitive: String): List<VerbAndImperative>

    @Transaction
    @Query ("SELECT * FROM verb WHERE infinitive = :infinitive")
    suspend fun getVerbAndNegativeImperativeWithInfinitive(infinitive: String): List<VerbAndNegativeImperative>

    @Query ("SELECT * FROM verb")
    suspend fun getVerbs(): List<Verb>
}