package com.rai.irregularverbs.data


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface IrregularVerbsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(verb: IrregularVerbs)

    @Update
    suspend fun update(verb: IrregularVerbs)

    @Delete
    suspend fun delete(verb: IrregularVerbs)

    @Query("SELECT * from verbs WHERE part = :part")
    fun getPart(part: Int): Flow<List<IrregularVerbs>>

    @Query("SELECT * from verbs")
    fun getAll(): Flow<List<IrregularVerbs>>

    @Query("SELECT * FROM verbs WHERE part = :part ORDER BY random() LIMIT 1")
    fun getRandom(part: Int): Flow<List<IrregularVerbs>>


}