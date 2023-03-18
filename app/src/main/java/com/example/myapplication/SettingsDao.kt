package com.example.myapplication

import androidx.room.*
import com.example.myapplication.entities.UserPracticeSettings

@Dao
interface SettingsDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserPracticeSettings(userPracticeSettings: UserPracticeSettings)

    @Query("SELECT * FROM UserPracticeSettings")
    suspend fun getUserPracticeSettings(): List<UserPracticeSettings>

    @Update
    suspend fun updateUserPracticeSettings(userPracticeSettings: UserPracticeSettings)
}