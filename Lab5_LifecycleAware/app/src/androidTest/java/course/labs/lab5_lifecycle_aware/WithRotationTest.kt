package course.labs.lab5_lifecycle_aware

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class WithRotationTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(LifecycleMainActivity::class.java)

    @Test
    fun checkRotation_1() {
        onView(withId(R.id.PrButton)).perform(click())
        onView(withText("Portrait-1")).check(matches(isDisplayed()))
        Thread.sleep(2000)
        activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        Thread.sleep(2000)
        for(i in 1..3){
            onView(withId(R.id.PrButton)).perform(click())
        }
        activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
        onView(withText("Portrait-1")).check(matches(isDisplayed()))
        onView(withText("Landscape-3")).check(matches(isDisplayed()))
        //onView(withText("Reverse Portrait-0")).check(matches(isDisplayed()))
        Thread.sleep(1000)


    }
    @Test
    fun checkRotation_2() {
        var nClicks_1 = (1..4).random()
        for(i in 1..nClicks_1) {
            onView(withId(R.id.PrButton)).perform(click())
        }
        onView(withText("Portrait-$nClicks_1")).check(matches(isDisplayed()))
        Thread.sleep(5000)
        activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        Thread.sleep(2000)
        var nClicks_2 = (1..4).random()
        for(i in 1..nClicks_2){
            onView(withId(R.id.PrButton)).perform(click())
        }
        onView(withText("Portrait-$nClicks_1")).check(matches(isDisplayed()))
        onView(withText("Landscape-$nClicks_2")).check(matches(isDisplayed()))
        onView(withId(R.id.ResButton)).perform(click())
        onView(withText("Landscape-0")).check(matches(isDisplayed()))
        activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        onView(withText("Portrait-$nClicks_1")).check(matches(isDisplayed()))
        onView(withText("Landscape-0")).check(matches(isDisplayed()))
        onView(withText("Portrait-0")).check(matches(isDisplayed()))
        Thread.sleep(2000)
        activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
        nClicks_2 = (1..4).random()
        for(i in 1..nClicks_2){
            onView(withId(R.id.PrButton)).perform(click())
        }
        onView(withText("Portrait-$nClicks_1")).check(matches(isDisplayed()))
        onView(withText("Landscape-0")).check(matches(isDisplayed()))
        onView(withText("Portrait-0")).check(matches(isDisplayed()))
        onView(withText("Reverse Landscape-$nClicks_2")).check(matches(isDisplayed()))
        Thread.sleep(2000)

    }
}