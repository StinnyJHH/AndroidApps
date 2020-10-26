package course.labs.activitylab.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import course.labs.activitylab.ActivityOne

import junit.framework.Assert;


class Test4_DoubleRotateActivityTwoTest : ActivityInstrumentationTestCase2<ActivityOne>(ActivityOne::class.java) {

    private var solo: Solo? = null

    @Throws(Exception::class)
    override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Executes the DoubleRotateActivityTwoTest
    fun testRun() {
        // ==================== Section One =====================
        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        val timeout = 20000
        Assert.assertTrue("DoubleRotateActivityTwoTest failed:" +
                "Section One:" +
                "ActivityOne did not load correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityOne::class.java, timeout))


        // ==================== Section Two =====================
        // Click on Start Activity Two
        solo!!.waitForView(course.labs.activitylab.R.id.bLaunchActivityTwo)
        solo!!.clickOnView(solo!!
                .getView(course.labs.activitylab.R.id.bLaunchActivityTwo))

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        Assert.assertTrue("DoubleRotateActivityTwoTest failed:" +
                "Section Two:" +
                "ActivityTwo did not load correctly",
                solo!!.waitForActivity(course.labs.activitylab.ActivityTwo::class.java, timeout))


        solo!!.waitForView(course.labs.activitylab.R.id.bClose)

        // ==================== Section Three =====================
        // Rotate the screen
        solo!!.setActivityOrientation(Solo.LANDSCAPE)

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        Assert.assertTrue("DoubleRotateActivityTwoTest failed:" +
                "Section Three:" +
                "ActivityTwo did not correctly load after first LANDSCAPE rotation.",
                solo!!.waitForActivity(course.labs.activitylab.ActivityTwo::class.java, timeout))

        solo!!.waitForView(course.labs.activitylab.R.id.bClose)

        // ==================== Section Four =====================
        // Rotate the screen
        solo!!.setActivityOrientation(Solo.PORTRAIT)

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        Assert.assertTrue("DoubleRotateActivityTwoTest failed:" +
                "Section Four:" +
                "ActivityTwo did not correctly load after second PORTRAIT rotation.",
                solo!!.waitForActivity(course.labs.activitylab.ActivityTwo::class.java, timeout))

        solo!!.waitForView(course.labs.activitylab.R.id.bClose)

        // Check for proper counts
        Assert.assertTrue("DoubleRotateActivityTwoTest failed:" +
                "Section Four:" +
                "onCreate() count was off for ActivityTwo after second PORTRAIT rotation.",
                solo!!.waitForText("onCreate\\(\\) calls: 3"))

        Assert.assertTrue("DoubleRotateActivityTwoTest failed:" +
                "Section Four:" +
                "onStart() count was off for ActivityTwo after second PORTRAIT rotation.",
                solo!!.waitForText("onStart\\(\\) calls: 3"))

        Assert.assertTrue("DoubleRotateActivityTwoTest failed:" +
                "Section Four:" +
                "onResume() count was off for ActivityTwo after second PORTRAIT rotation.",
                solo!!.waitForText("onResume\\(\\) calls: 3"))

        Assert.assertTrue("DoubleRotateActivityTwoTest failed:" +
                "Section Four:" +
                "onRestart() count was off for ActivityTwo after second PORTRAIT rotation.",
                solo!!.waitForText("onRestart\\(\\) calls: 0"))
    }

}
