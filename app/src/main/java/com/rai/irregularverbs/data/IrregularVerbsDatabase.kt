package com.rai.irregularverbs.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(IrregularVerbs::class), version = 1, exportSchema = false)
abstract class IrregularVerbsDatabase: RoomDatabase() {
    abstract fun irregularVerbsDao(): IrregularVerbsDao

    companion object {
        @Volatile
        private var INSTANCE: IrregularVerbsDatabase? = null
        fun getDatabase(context: Context): IrregularVerbsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    IrregularVerbsDatabase::class.java,
                    "verb_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

}