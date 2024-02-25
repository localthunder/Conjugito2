package com.conjugito.conjugito

import androidx.room.*
import com.conjugito.conjugito.entities.UserPracticeSettings

@Dao
interface SettingsDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserPracticeSettings(userPracticeSettings: UserPracticeSettings)

    @Query("SELECT * FROM UserPracticeSettings")
    suspend fun getUserPracticeSettings(): List<UserPracticeSettings>

    @Update
    suspend fun updateUserPracticeSettings(userPracticeSettings: UserPracticeSettings)
}