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
class WithoutRotationTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(LifecycleMainActivity::class.java)

    @Test
    fun checkCounter() {
        val nClicks = (1..4).random()
        for(i in 1..nClicks){
        onView(withId(R.id.PrButton)).perform(click())
        }
        onView(withText("Portrait-$nClicks")).check(matches(isDisplayed()))
        Thread.sleep(5000)



    }
    @Test
    fun  checkReset(){
        val nClicks = (1..4).random()
        for(i in 1..nClicks){
            onView(withId(R.id.PrButton)).perform(click())
        }
        onView(withText("Portrait-$nClicks")).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.ResButton)).perform(click())
        onView(withText("Portrait-0")).check(matches(isDisplayed()))
    }
    @Test
    fun checkPageLayout(){
        onView(withId(R.id.DisplayText)).check(matches(withText("Portrait-0")))
    }
}