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

    fun initialize() {
        liveData.postValue(UiModel.CountUpdate(repository.getCount()))
        liveData.postValue(UiModel.TitleUpdate(repository.getTitle()!!))
    }

    fun setTitle(title : String) {
        repository.setTitle(title)
        liveData.value = UiModel.TitleUpdate(title)
    }
}