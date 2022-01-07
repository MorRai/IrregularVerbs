package com.rai.irregularverbs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rai.irregularverbs.data.IrregularVerbs
import com.rai.irregularverbs.data.IrregularVerbsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ListVerbViewModel(private val irregularVerbsDao: IrregularVerbsDao): ViewModel() {

    fun fullPart(part: Int): Flow<List<IrregularVerbs>> = irregularVerbsDao.getPart(part)
    fun fullAll(): Flow<List<IrregularVerbs>> = irregularVerbsDao.getAll()



}

class ListVerbViewModelFactory(
    private val irregularVerbsDao: IrregularVerbsDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListVerbViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListVerbViewModel(irregularVerbsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}