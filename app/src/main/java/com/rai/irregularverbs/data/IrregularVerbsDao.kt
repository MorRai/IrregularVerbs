package com.rai.irregularverbs.data


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface IrregularVerbsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(verb: List<IrregularVerbs>)

    @Update
    suspend fun update(verb: IrregularVerbs)

    @Delete
    suspend fun delete(verb: IrregularVerbs)

    @Query("SELECT * from verbs WHERE part = :part")
    fun getPart(part: Int): Flow<List<IrregularVerbs>>

    @Query("SELECT * from verbs")
    fun getAll(): Flow<List<IrregularVerbs>>

    @Query("SELECT * FROM verbs WHERE part = :part and (numCorrectV2<3 or numCorrectV3<3) ORDER BY random() LIMIT 1")
    suspend fun getRandom(part: Int): IrregularVerbs

    @Query("SELECT Count(id) FROM verbs WHERE part = :part and (numCorrectV2<3 or numCorrectV3<3)")
    fun getAvailability(part: Int): Flow<Int>


}