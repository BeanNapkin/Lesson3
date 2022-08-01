package com.geekbrains.tests

import android.content.Context
import android.view.inputmethod.EditorInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import com.geekbrains.tests.view.details.DetailsActivity
import com.geekbrains.tests.view.search.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Rule
    @JvmField
    val mainActivityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testSearchEditText(){
        val id = R.id.searchEditText
        val hint = "Enter keyword e.g. android"
        val assertion: ViewAssertion = matches(withHint(hint))

        onView(withId(id)).check(matches(isDisplayed()))
        onView(withId(id)).check(matches(isClickable()))
        onView(withId(id)).check(assertion)

        onView(withId(id)).check(matches(hasImeAction(EditorInfo.IME_ACTION_SEARCH)))
    }

    @Test
    fun testToDetailsButton(){
        val id = R.id.toDetailsActivityButton

        onView(withId(id)).check(matches(isDisplayed()))
        onView(withId(id)).check(matches(isClickable()))

        val buttonText = "to details"
        val assertion: ViewAssertion = matches(withText(buttonText))
        onView(withId(id)).check(assertion)

        onView(withId(id)).perform(click())
        intended(hasComponent(DetailsActivity::class.java.getName()))
    }
}