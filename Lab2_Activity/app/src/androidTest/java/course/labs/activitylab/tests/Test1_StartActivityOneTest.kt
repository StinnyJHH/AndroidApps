package course.labs.activitylab.tests

import android.test.ActivityInstrumentationTestCase2

import android.support.test.rule.ActivityTestRule

import com.robotium.solo.Solo

import course.labs.activitylab.ActivityOne

import junit.framework.Assert;


class Test1_StartActivityOneTest : ActivityInstrumentationTestCase2<ActivityOne>(ActivityOne::class.java) {

    private var solo: Solo? = null

    @Throws(Exception::class)
    public override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Execution of StartActivityOneTest
    fun testRun() {

        // ==================== Section One =====================
        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        val timeout = 20000
        Assert.assertTrue("StartActivityOneTest failed: " + "Section One:"
                + "ActivityOne did not correctly load", solo!!.waitForActivity(
                course.labs.activitylab.ActivityOne::class.java, timeout))


        // ==================== Section Two =====================
        // Check for proper counts
        Assert.assertTrue("StartActivityOneTest failed:" + "Section Two:"
                + "onCreate() count was off for ActivityOne",
                solo!!.waitForText("onCreate\\(\\) calls: 1"))
        Assert.assertTrue("StartActivityOneTest failed:" + "Section Two:"
                + "onStart() count was off for ActivityOne",
                solo!!.waitForText("onStart\\(\\) calls: 1"))
        Assert.assertTrue("StartActivityOneTest failed:" + "Section Two:"
                + "onResume() count was off for ActivityOne",
                solo!!.waitForText("onResume\\(\\) calls: 1"))
        Assert.assertTrue("StartActivityOneTest failed:" + "Section Two:"
                + "onRestart() count was off for ActivityOne",
                solo!!.waitForText("onRestart\\(\\) calls: 0"))

    }

}
