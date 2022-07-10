package com.rai.irregularverbs.viewmodels

import androidx.lifecycle.ViewModel
import com.rai.irregularverbs.data.IrregularVerbs
import com.rai.irregularverbs.data.IrregularVerbsDao
import kotlinx.coroutines.flow.Flow


class ListVerbViewModel(private val irregularVerbsDao: IrregularVerbsDao) : ViewModel() {

    fun fullPart(part: Int): Flow<List<IrregularVerbs>> = irregularVerbsDao.getPart(part)

    fun fullAll(): Flow<List<IrregularVerbs>> = irregularVerbsDao.getAll()
}
