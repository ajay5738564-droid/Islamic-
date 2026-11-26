package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE type = :type ORDER BY timestamp DESC")
    fun getBookmarksByType(type: String): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmark(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)")
    fun isBookmarked(id: String): Flow<Boolean>
}

@Dao
interface TasbeehDao {
    @Query("SELECT * FROM tasbeeh_history ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<TasbeehHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: TasbeehHistoryEntity)

    @Query("DELETE FROM tasbeeh_history")
    suspend fun clearAllHistory()
}

@Dao
interface OfflineAyahDao {
    @Query("SELECT * FROM offline_ayahs WHERE surahNumber = :surahNumber ORDER BY numberInSurah ASC")
    suspend fun getAyahsForSurah(surahNumber: Int): List<OfflineAyahEntity>

    @Query("SELECT * FROM offline_ayahs WHERE juz = :juzNumber ORDER BY numberGlobal ASC")
    suspend fun getAyahsForJuz(juzNumber: Int): List<OfflineAyahEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAyahs(ayahs: List<OfflineAyahEntity>)
}
