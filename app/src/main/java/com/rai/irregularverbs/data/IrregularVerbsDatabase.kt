package com.rai.irregularverbs.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(entities = [IrregularVerbs::class], version = 1, exportSchema = false)
abstract class IrregularVerbsDatabase: RoomDatabase() {
    abstract fun irregularVerbsDao(): IrregularVerbsDao



    private class IrregularVerbsDatabaseCallback(
        private val scope: CoroutineScope,
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    database.irregularVerbsDao()
                        .insert(IrregularVerbsGenerator.getVerbs())
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: IrregularVerbsDatabase? = null

        fun getDatabase(context: Context): IrregularVerbsDatabase {
            val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                 val instance = Room.databaseBuilder(context, IrregularVerbsDatabase::class.java, "verb_database")
                    .addCallback(IrregularVerbsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }






    }

}