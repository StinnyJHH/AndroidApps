package course.labs.activitylab.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import course.labs.activitylab.ActivityOne

import junit.framework.Assert;


class Test6_ReopenActivityTwoTest : ActivityInstrumentationTestCase2<ActivityOne>(ActivityOne::class.java) {

    private var solo: Solo? = null

    @Throws(Exception::class)
    public override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Executes the ReopenActivityTwoTest
    fun testRun() {

        // ==================== Section One =====================
        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        val timeout = 20000
        Assert.assertTrue("ReopenActivityTwoTest failed:" +
                "Section One:" +
                "ActivityOne did not load correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityOne::class.java, timeout))

        solo!!.waitForView(course.labs.activitylab.R.id.bLaunchActivityTwo)

        // ==================== Section Two =====================
        // Click on Start Activity Two
        solo!!.clickOnView(solo!!.getView(course.labs.activitylab.R.id.bLaunchActivityTwo))

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        Assert.assertTrue("ReopenActivityTwoTest failed:" +
                "Section Two:" +
                "ActivityTwo did not load correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityTwo::class.java, timeout))

        solo!!.waitForView(course.labs.activitylab.R.id.bClose)

        val sleep = 1000
        solo!!.sleep(sleep)

        // ==================== Section Three =====================
        // Click on Close Activity
        solo!!.clickOnView(solo!!.getView(course.labs.activitylab.R.id.bClose))

        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        Assert.assertTrue("ReopenActivityTwoTest failed:" +
                "Section Three:" +
                "ActivityTwo did not close correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityOne::class.java, timeout))

        solo!!.waitForView(course.labs.activitylab.R.id.bLaunchActivityTwo)

        solo!!.sleep(sleep)

        // ==================== Section Four =====================
        // Click on Start Activity Two
        solo!!.clickOnView(solo!!.getView(course.labs.activitylab.R.id.bLaunchActivityTwo))

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        Assert.assertTrue("ReopenActivityTwoTest failed:" +
                "Section Four:" +
                "ActivityTwo did not reopen correctly after being closed",
                solo!!.waitForActivity(course.labs.activitylab.ActivityTwo::class.java, timeout))

        solo!!.waitForView(course.labs.activitylab.R.id.bClose)

        // Check for proper counts
        Assert.assertTrue("ReopenActivityTwoTest failed:" +
                "Section Four:" +
                "onCreate() count was off for ActivityTwo after being reopened for a second time.",
                solo!!.waitForText("onCreate\\(\\) calls: 1"))

        Assert.assertTrue("ReopenActivityTwoTest failed:" +
                "Section Four:" +
                "onStart() count was off for ActivityTwo after being reopened for a second time.",
                solo!!.waitForText("onStart\\(\\) calls: 1"))

        Assert.assertTrue("ReopenActivityTwoTest failed:" +
                "Section Four:" +
                "onResume() count was off for ActivityTwo after being reopened for a second time.",
                solo!!.waitForText("onResume\\(\\) calls: 1"))

        Assert.assertTrue("ReopenActivityTwoTest failed:" +
                "Section Four:" +
                "onRestart() count was off for ActivityTwo after being reopened for a second time.",
                solo!!.waitForText("onRestart\\(\\) calls: 0"))


    }

}
