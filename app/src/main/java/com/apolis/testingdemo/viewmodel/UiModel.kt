package com.apolis.testingdemo.viewmodel

sealed class UiModel {
    data class TitleUpdate(val title : String) : UiModel()
    data class CountUpdate(val count: Int) : UiModel()
}
