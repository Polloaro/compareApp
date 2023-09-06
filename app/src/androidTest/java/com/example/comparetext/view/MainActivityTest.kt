package com.example.comparetext.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.comparetext.R

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @get:Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivity_compareEmptyStrings() {
        Espresso.onView(
            ViewMatchers.withId(R.id.compareButton)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.resultTextView)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Error: Complete ambos campos.")
            )
        )
    }

    @Test
    fun mainActivity_compareEqualStrings() {
        Espresso.onView(
            ViewMatchers.withId(R.id.Text1)
        ).perform(
            ViewActions.typeText("asdasd")
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.Text2)
        ).perform(
            ViewActions.typeText("asdasd")
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.compareButton)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.resultTextView)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.result_ok)
            )
        ).check(
            ViewAssertions.matches(
                ViewMatchers.hasTextColor(R.color.result_ok)
            )
        )
    }

    @Test
    fun mainActivity_compareDifferentStrings() {
        Espresso.onView(
            ViewMatchers.withId(R.id.Text1)
        ).perform(
            ViewActions.typeText("asdasd")
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.Text2)
        ).perform(
            ViewActions.typeText("asdfgh")
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.compareButton)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.resultTextView)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.result_fail)
            )
        ).check(
            ViewAssertions.matches(
                ViewMatchers.hasTextColor(R.color.result_fail)
            )
        )
    }
}