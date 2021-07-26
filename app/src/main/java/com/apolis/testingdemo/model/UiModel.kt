package com.apolis.testingdemo.model

sealed class UiModel {
    data class TitleUpdate(val title : String) : UiModel()
    data class CountUpdate(val count: Int) : UiModel()
}
