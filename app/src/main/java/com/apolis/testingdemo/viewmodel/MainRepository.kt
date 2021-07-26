package com.apolis.testingdemo.viewmodel

interface MainRepository {
    fun getCount() : Int
    fun setCount(count : Int)
    fun getTitle() : String?
    fun setTitle(title : String)

}