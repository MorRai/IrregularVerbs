package com.rai.irregularverbs

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.rai.irregularverbs.data.IrregularVerbsDatabase

class IrregularVerbsApplication : Application() {
    val database: IrregularVerbsDatabase by lazy { IrregularVerbsDatabase.getDatabase(this ) }

}