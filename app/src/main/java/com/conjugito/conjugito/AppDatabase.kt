package com.conjugito.conjugito

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.conjugito.conjugito.entities.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [
        Verb::class,
        Present::class,
        Preterite::class,
        Future::class,
        Conditional::class,
        Imperfect::class,
        PresentProgressive::class,
        PreteriteProgressive::class,
        ImperfectProgressive::class,
        ConditionalProgressive::class,
        FutureProgressive::class,
        PresentPerfect::class,
        PreteritePerfect::class,
        Pluperfect::class,
        ConditionalPerfect::class,
        FuturePerfect::class,
        PresentSubjunctive::class,
        ImperfectSubjunctiveRa::class,
        ImperfectSubjunctiveSe::class,
        FutureSubjunctive::class,
        PresentPerfectSubjunctive::class,
        PluperfectSubjunctive::class,
        FuturePerfectSubjunctive::class,
        Imperative::class,
        NegativeImperative::class,
        UserPracticeSettings::class
               ],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 3, to = 4)
    ]
)

abstract class AppDatabase: RoomDatabase() {

    abstract val settingsDao : SettingsDao
    abstract val verbDao : VerbDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_db"
                )
                    .createFromAsset("app_db.db")
                    .addMigrations(migration2to3)
                    .build().also {
                    INSTANCE = it
                }
            }
        }
        private val migration2to3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS UserPracticeSettings (uniqueId INTEGER NOT NULL PRIMARY KEY, showIrregularVerbs INTEGER NOT NULL, showReflexiveVerbs INTEGER NOT NULL, showUncommonVerbs INTEGER NOT NULL, showPresentTense INTEGER NOT NULL, showPreteriteTense INTEGER NOT NULL, showFutureTense INTEGER NOT NULL, showConditionalTense INTEGER NOT NULL, showImperfectTense INTEGER NOT NULL, showPresentProgressive INTEGER NOT NULL, showPreteriteProgressive INTEGER NOT NULL, showImperfectProgressive INTEGER NOT NULL, showConditionalProgressive INTEGER NOT NULL, showFutureProgressive INTEGER NOT NULL, showPresentPerfect INTEGER NOT NULL, showPreteritePerfect INTEGER NOT NULL, showPluperfect INTEGER NOT NULL, showConditionalPerfect INTEGER NOT NULL, showFuturePerfect INTEGER NOT NULL, showPresentSubjunctive INTEGER NOT NULL, showImperfectSubjunctiveRa INTEGER NOT NULL, showImperfectSubjunctiveSe INTEGER NOT NULL, showFutureSubjunctive INTEGER NOT NULL, showPresentPerfectSubjunctive INTEGER NOT NULL, showPluperfectSubjunctive INTEGER NOT NULL, showFuturePerfectSubjunctive INTEGER NOT NULL, showImperative INTEGER NOT NULL, showNegativeImperative INTEGER NOT NULL)"
                )
            }
        }
    }
}
