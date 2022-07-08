package com.rai.irregularverbs

import android.app.Application
import com.rai.irregularverbs.data.IrregularVerbsDatabase
import com.rai.irregularverbs.koin.verbDatabaseModule
import com.rai.irregularverbs.koin.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IrregularVerbsApplication : Application() {
    //val database: IrregularVerbsDatabase by lazy { IrregularVerbsDatabase.getDatabase(this ) }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IrregularVerbsApplication)
            modules(
                viewModelsModule,
                verbDatabaseModule
            )
        }
    }
}