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

    @Query("SELECT SUM(numCorrectV2)+ SUM(numCorrectV3) From verbs WHERE part = :part")
    suspend fun getComplete(part: Int): Int

    @Query("SELECT Count(id) FROM verbs WHERE part = :part and (numCorrectV2<3 or numCorrectV3<3)")
    suspend fun getAvailability(part: Int): Int

    @Query("Update verbs Set numCorrectV2 = 0,numCorrectV3=0 WHERE part = :part")
    suspend fun dumpPart(part: Int)


}