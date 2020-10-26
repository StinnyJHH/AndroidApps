package course.labs.activitylab.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import course.labs.activitylab.ActivityOne

import junit.framework.Assert;


class Test5_CloseActivityTwoTest : ActivityInstrumentationTestCase2<ActivityOne>(ActivityOne::class.java) {

    private var solo: Solo? = null

    @Throws(Exception::class)
    override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Executes the CloseActivityTwoTest
    fun testRun() {

        // ==================== Section One =====================
        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        val timeout = 20000
        Assert.assertTrue("CloseActivityTwoTest failed:" +
                "Section One:" +
                "ActivityOne did not load correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityOne::class.java, timeout))

        solo!!.waitForView(course.labs.activitylab.R.id.bLaunchActivityTwo)

        // ==================== Section Two =====================
        // Click on Start Activity Two
        solo!!.clickOnView(solo!!.getView(course.labs.activitylab.R.id.bLaunchActivityTwo))

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        Assert.assertTrue("CloseActivityTwoTest failed:" +
                "Section Two:" +
                "ActivityTwo did not load correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityTwo::class.java, timeout))


        // ==================== Section Three =====================
        // Click on Close Activity

        solo!!.waitForView(course.labs.activitylab.R.id.bClose)

        val sleep = 1000
        solo!!.sleep(sleep)

        solo!!.clickOnView(solo!!.getView(course.labs.activitylab.R.id.bClose))


        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        Assert.assertTrue("CloseActivityTwoTest failed:" +
                "Section Three:" +
                "ActivityTwo did not close correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityOne::class.java, timeout))

        solo!!.waitForView(course.labs.activitylab.R.id.bLaunchActivityTwo)

        // Check for proper counts
        Assert.assertTrue("CloseActivityTwoTest failed:" +
                "Section Three:" +
                "onCreate() count was off for ActivityOne after ActivityTwo closed.",
                solo!!.waitForText("onCreate\\(\\) calls: 1"))

        Assert.assertTrue("CloseActivityTwoTest failed:" +
                "Section Three:" +
                "onStart() count was off for ActivityOne after ActivityTwo closed.",
                solo!!.waitForText("onStart\\(\\) calls: 2"))

        Assert.assertTrue("CloseActivityTwoTest failed:" +
                "Section Three:" +
                "onResume() count was off for ActivityOne after ActivityTwo closed.",
                solo!!.waitForText("onResume\\(\\) calls: 2"))

        Assert.assertTrue("CloseActivityTwoTest failed:" +
                "Section Three:" +
                "onRestart() count was off for ActivityOne after ActivityTwo closed.",
                solo!!.waitForText("onRestart\\(\\) calls: 1"))

    }

}
