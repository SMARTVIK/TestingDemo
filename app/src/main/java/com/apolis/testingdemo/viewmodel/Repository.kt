package com.apolis.testingdemo.viewmodel

import android.content.Context
import android.content.SharedPreferences

open class Repository(val context : Context) : MainRepository {

    companion object {

        private const val NAME = "com.apolis.testingdemo"
        private const val KEY_COUNT = "count"
        private const val KEY_TITLE = "title"
    }

    var sharedPrefs : SharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    override fun getCount() : Int {
        return sharedPrefs.getInt(KEY_COUNT, 0)
    }

    override fun setCount(count: Int) {
        sharedPrefs.edit().putInt(KEY_COUNT, count).apply()
    }

    override fun getTitle() : String? {
        return sharedPrefs.getString(KEY_TITLE, "Title")
    }

    override fun setTitle(title: String) {
        sharedPrefs.edit().putString(KEY_TITLE, title).apply()
    }

    override fun reset() {
        sharedPrefs.edit().clear().apply() //resetting everything
    }

    override fun getTitleAndCount(): Pair<String, Int> {
        return Pair(getTitle()!!, getCount())
    }


}