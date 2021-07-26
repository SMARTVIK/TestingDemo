package com.apolis.testingdemo.viewmodel

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.apolis.testingdemo.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    var mainActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkTextViewDisplayed() {

        onView(withId(R.id.textViewCount)).check(matches(isDisplayed()))

    }

    @Test
    fun tappingOnTitleOpensAlertDialog() {

        onView(withId(R.id.title)).perform(click())

        onView(withId(R.id.alertTitle)).check(matches(isDisplayed()))

        onView(withId(android.R.id.button2)).perform(click())

    }

    @Test
    fun editingTitleUpdatesTitle() {

        onView(withId(R.id.title)).perform(click())

        val title = "Android is great!!"

        onView(instanceOf(EditText::class.java))
            .perform(clearText())
            .perform(typeText(title))

        onView(withText("OK")).perform(click())

        onView(allOf(withId(R.id.title), withText(title))).check(matches(isDisplayed()))

    }


}