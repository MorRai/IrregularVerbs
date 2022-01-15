package com.rai.irregularverbs.viewmodels

import androidx.lifecycle.*
import com.rai.irregularverbs.data.IrregularVerbs
import com.rai.irregularverbs.data.IrregularVerbsDao
import kotlinx.coroutines.launch

class ExamViewModel(private val irregularVerbsDao: IrregularVerbsDao): ViewModel() {
    var part: Int = 0
    var editText: String = ""


    private val _checkVisibility = MutableLiveData<Boolean>()
    val checkVisibility: LiveData<Boolean>
        get() = _checkVisibility


    private val _previousVerb = MutableLiveData<IrregularVerbs?>()
    val previousVerb: LiveData<IrregularVerbs?>
        get() = _previousVerb



    private val _randomVerb = MutableLiveData<IrregularVerbs?>()
    val randomVerb: LiveData<IrregularVerbs?>
        get() = _randomVerb


    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int>
        get() = _progress

    private val _availability = MutableLiveData<Int>()
    val availability: LiveData<Int>
        get() = _availability




     private fun getRandomVerb(){
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

    fun getAvailability(part: Int): LiveData<Int> {
        val result = MutableLiveData<Int>()
        viewModelScope.launch {
            result.value = irregularVerbsDao.getAvailability(part)
        }
        return result
    }

    fun getV2orV3(verb: IrregularVerbs): Int{
        return when {
            verb.numCorrectV3>= 3 -> {
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

    fun nextVerb(verb: IrregularVerbs,textCheck:String,editText:String, getV2orV3: Int) {
        refresh(verb,textCheck,editText, getV2orV3)
        getRandomVerb()
        getComplete()
    }

    private fun refresh(irregularVerbs: IrregularVerbs,textCheck:String,editText:String, getV2orV3: Int){
        if(editText==textCheck){
            _checkVisibility.value = false
            if (getV2orV3 == 2) {
                val newIrregularVerbs = irregularVerbs.copy(numCorrectV2 = irregularVerbs.numCorrectV2 + 1)
                updateItem(newIrregularVerbs)
            }else{
                val newIrregularVerbs = irregularVerbs.copy(numCorrectV3 = irregularVerbs.numCorrectV3 + 1)
                updateItem(newIrregularVerbs)
            }

        }else{
            _checkVisibility.value = true
            _previousVerb.value = irregularVerbs
        }
    }

    fun updateEditText(editText:String){
        this.editText = editText
    }

    fun clearIrregular(){
        _checkVisibility.value = false
        _previousVerb.value = null
        getRandomVerb()
        getComplete()
    }



}


class ExamViewModelFactory(
    private val irregularVerbsDao: IrregularVerbsDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExamViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExamViewModel(irregularVerbsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}