package com.apolis.testingdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun incrementCount() {
        val count = repository.getCount() + 1
        repository.setCount(count) //6
        liveData.value = UiModel.CountUpdate(count)//count = 1
    }

    lateinit var repository : MainRepository
    var liveData : MutableLiveData<UiModel> = MutableLiveData()

    fun populate() {
        val (title, count) = repository.getTitleAndCount()
        liveData.postValue(UiModel.CountUpdate(count))
        liveData.value = UiModel.TitleUpdate(title)
    }

    fun setTitle(title : String) {
        repository.setTitle(title)
        liveData.value = UiModel.TitleUpdate(title)
    }

    fun reset() {
        repository.reset()
        populate()
    }
}