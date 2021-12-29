package com.rai.irregularverbs.viewmodels

import androidx.lifecycle.*
import com.rai.irregularverbs.data.IrregularVerbs
import com.rai.irregularverbs.data.IrregularVerbsDao
import kotlinx.coroutines.launch

class ExamViewModel(private val irregularVerbsDao: IrregularVerbsDao): ViewModel() {
    var part: Int = 1
    var editText: String = ""


    private val _checkVisibility = MutableLiveData<Boolean>()
    val checkVisibility: LiveData<Boolean>
        get() = _checkVisibility


    private val _previousVerb = MutableLiveData<IrregularVerbs>()
    val previousVerb: LiveData<IrregularVerbs>
        get() = _previousVerb



    private val _randomVerb = MutableLiveData<IrregularVerbs>()
    val randomVerb: LiveData<IrregularVerbs>
        get() = _randomVerb


    init {
        getRandomVerb(part)
    }



    private fun getRandomVerb(part: Int){
        viewModelScope.launch {
            _randomVerb.value = irregularVerbsDao.getRandom(part)
        }
    }

    private fun updateItem(verb: IrregularVerbs) {
        viewModelScope.launch {
            irregularVerbsDao.update(verb)
        }
    }

    fun getv2orv3(verb: IrregularVerbs): Int{
        if (verb.numCorrectV3>= 3){
            return 2
        }else if(verb.numCorrectV2 >= 3){
            return 3
        }else{
         return (2..3).random()
        }
    }

    fun nextVerb(verb: IrregularVerbs,textСheck:String,editText:String) {
        refresh(verb,textСheck,editText)
        updateItem(verb)
        getRandomVerb(part)
    }

    private fun refresh(irregularVerbs: IrregularVerbs,textСheck:String,editText:String){
        if(editText.equals(textСheck)){
            _checkVisibility.value = false
        }else{
            _checkVisibility.value = true
            _previousVerb.value = irregularVerbs
        }
    }

    fun updateEditText(editText:String){
        this.editText = editText
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