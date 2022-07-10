package com.rai.irregularverbs.viewmodels

import androidx.lifecycle.*
import com.rai.irregularverbs.data.IrregularVerbs
import com.rai.irregularverbs.data.IrregularVerbsDao
import kotlinx.coroutines.launch
import com.rai.irregularverbs.constants.Charpter.Most50
import com.rai.irregularverbs.constants.Charpter.Plus50
import com.rai.irregularverbs.constants.Charpter.Pro
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExamViewModel(private val irregularVerbsDao: IrregularVerbsDao) : ViewModel() {
    var part: Int = 0
    var editText: String = ""


    private val _randomVerb = MutableStateFlow<IrregularVerbs?>(null)
    val randomVerb: StateFlow<IrregularVerbs?>
        get() = _randomVerb

    private val _previousVerb = MutableStateFlow<IrregularVerbs?>(null)
    val previousVerb: StateFlow<IrregularVerbs?>
        get() = _previousVerb


    private val _checkVisibility = MutableStateFlow(false)
    val checkVisibility: StateFlow<Boolean>
        get() = _checkVisibility

    private val _progress = MutableStateFlow(0)
    val progress: StateFlow<Int>
        get() = _progress


    private val _blockMost50 = MutableStateFlow(false)
    val blockMost50: StateFlow<Boolean>
        get() = _blockMost50


    private val _blockPlus50 = MutableStateFlow(false)
    val blockPlus50: StateFlow<Boolean>
        get() = _blockPlus50

    private val _blockPro = MutableStateFlow(false)
    val blockPro: StateFlow<Boolean>
        get() = _blockPro


    private fun getRandomVerb() {
        viewModelScope.launch {
            _randomVerb.value = irregularVerbsDao.getRandom(part)
        }
    }

    private fun updateItem(verb: IrregularVerbs) {
        viewModelScope.launch {
            irregularVerbsDao.update(verb)
        }
    }

    fun dumpPart() {
        viewModelScope.launch {
            irregularVerbsDao.dumpPart(part)
        }
    }

    private fun getComplete() {
        viewModelScope.launch {
            _progress.value = irregularVerbsDao.getComplete(part)
        }
    }

    fun getAvailability() {
        viewModelScope.launch {
            _blockMost50.value = irregularVerbsDao.getAvailability(Most50) == 0
            _blockPlus50.value = irregularVerbsDao.getAvailability(Plus50) == 0
            _blockPro.value = irregularVerbsDao.getAvailability(Pro) == 0
        }
    }

    fun getV2orV3(verb: IrregularVerbs): Int {
        return when {
            verb.numCorrectV3 >= 3 -> {
                2
            }
            verb.numCorrectV2 >= 3 -> {
                3
            }
            else -> {
                (2..3).random()
            }
        }
    }

    fun nextVerb(verb: IrregularVerbs, textCheck: String, editText: String, getV2orV3: Int) {
        refresh(verb, textCheck, editText, getV2orV3)
        getRandomVerb()
        getComplete()
    }

    private fun refresh(
        irregularVerbs: IrregularVerbs,
        textCheck: String,
        editText: String,
        getV2orV3: Int,
    ) {
        if (editText == textCheck) {
            _checkVisibility.value = false
            if (getV2orV3 == 2) {
                val newIrregularVerbs =
                    irregularVerbs.copy(numCorrectV2 = irregularVerbs.numCorrectV2 + 1)
                updateItem(newIrregularVerbs)
            } else {
                val newIrregularVerbs =
                    irregularVerbs.copy(numCorrectV3 = irregularVerbs.numCorrectV3 + 1)
                updateItem(newIrregularVerbs)
            }

        } else {
            _checkVisibility.value = true
            _previousVerb.value = irregularVerbs
            if (getV2orV3 == 2 && irregularVerbs.numCorrectV2 > 0) {
                val newIrregularVerbs =
                    irregularVerbs.copy(numCorrectV2 = irregularVerbs.numCorrectV2 - 1)
                updateItem(newIrregularVerbs)
            } else if (irregularVerbs.numCorrectV3 > 0) {
                val newIrregularVerbs =
                    irregularVerbs.copy(numCorrectV3 = irregularVerbs.numCorrectV3 - 1)
                updateItem(newIrregularVerbs)
            }
        }
    }

    fun updateEditText(editText: String) {
        this.editText = editText
    }

    fun clearIrregular() {
        _checkVisibility.value = false
        _previousVerb.value = null
        getRandomVerb()
        getComplete()
    }

}

