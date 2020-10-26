package course.labs.activitylab.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import course.labs.activitylab.ActivityOne

import junit.framework.Assert;


class Test3_StartActivityTwoTest : ActivityInstrumentationTestCase2<ActivityOne>(ActivityOne::class.java) {

    private var solo: Solo? = null

    @Throws(Exception::class)
    override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Executes the StartActivityTwoTest
    fun testRun() {

        // ==================== Section One =====================
        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        val timeout = 20000
        Assert.assertTrue("StartActivityTwoTest failed:" +
                "Section One:" +
                "ActivityOne did not load correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityOne::class.java, timeout))


        // ==================== Section Two =====================
        // Click on Start Activity Two

        solo!!.waitForView(course.labs.activitylab.R.id.bLaunchActivityTwo)
        solo!!.clickOnView(solo!!
                .getView(course.labs.activitylab.R.id.bLaunchActivityTwo))

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        Assert.assertTrue("StartActivityTwoTest failed:" +
                "Section Two:" +
                "ActivityTwo did not load correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityTwo::class.java, timeout))


        // Check for proper counts after ActivityTwo has been opened
        Assert.assertTrue("StartActivityTwoTest failed:" +
                "Section Two:" +
                "onCreate() count was off for ActivityTwo.",
                solo!!.waitForText("onCreate\\(\\) calls: 1"))

        Assert.assertTrue("StartActivityTwoTest failed:" +
                "Section Two:" +
                "onStart() count was off for ActivityTwo.",
                solo!!.waitForText("onStart\\(\\) calls: 1"))

        Assert.assertTrue("StartActivityTwoTest failed:" +
                "Section Two:" +
                "onResume() count was off for ActivityTwo.",
                solo!!.waitForText("onResume\\(\\) calls: 1"))

        Assert.assertTrue("StartActivityTwoTest failed:" +
                "Section Two:" +
                "onRestart() count was off for ActivityTwo.",
                solo!!.waitForText("onRestart\\(\\) calls: 0"))
    }

}
