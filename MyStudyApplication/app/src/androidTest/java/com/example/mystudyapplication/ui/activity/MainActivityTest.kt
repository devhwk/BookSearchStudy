package com.example.mystudyapplication.ui.activity

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import com.example.mystudyapplication.R
import com.example.mystudyapplication.ui.adapter.BookSearchAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    @SmallTest
    fun test_activity_state() {
        val activityState = activityScenarioRule.scenario.state.name
        assertThat(activityState).isEqualTo("RESUMED")
    }

    @Test
    @LargeTest
    fun from_SearchFragment_to_FavoriteFargment_Ui_Operaion() {
        onView(withId(R.id.tv_result_empty))
            .check(matches(withText("No Result")))
        onView(withId(R.id.et_search))
            .perform(typeText("android"))
        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.rv_search_result))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_search_result))
            .perform(actionOnItemAtPosition<BookSearchAdapter.ViewHolder>(0, click()))
        onView(isRoot()).perform(waitFor(1000))
        onView(withId(R.id.fab_favorite))
            .perform(click())
        Espresso.pressBack()
        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.rv_search_result))
            .check(matches(isDisplayed()))

        onView(withId(R.id.fragment_favorite))
            .perform(click())
        onView(withId(R.id.rv_favorite_books))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_books)).perform(
            actionOnItemAtPosition<BookSearchAdapter.ViewHolder>(0, swipeLeft())
        )
    }

    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String = "wait for $delay milliseconds"

            override fun getConstraints(): Matcher<View> = isRoot()

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}