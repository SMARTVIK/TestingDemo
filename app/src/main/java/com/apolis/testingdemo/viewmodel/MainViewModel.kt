package com.apolis.testingdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apolis.testingdemo.model.MainRepository
import com.apolis.testingdemo.model.UiModel

class MainViewModel : ViewModel() {

    fun incrementCount() {
        val count = repository.getCount() + 1
        repository.setCount(count)
        liveData.value = UiModel.CountUpdate(count)
    }

    lateinit var repository : MainRepository

    var liveData : MutableLiveData<UiModel> = MutableLiveData()

    fun setTitle(title : String) {
        repository.setTitle(title)
        liveData.value = UiModel.TitleUpdate(title)
    }





}