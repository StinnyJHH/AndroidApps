package course.labs.activitylab.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import course.labs.activitylab.ActivityOne

import junit.framework.Assert;


class Test2_DoubleRotateActivityOneTest : ActivityInstrumentationTestCase2<ActivityOne>(ActivityOne::class.java) {

    private var solo: Solo? = null

    @Throws(Exception::class)
    public override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    fun testRun() {
        // ==================== Section One =====================
        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        val timeout = 20000
        Assert.assertTrue("DoubleRotateActivityOneTest failed: " +
                "Section One:" +
                "ActivityOne did not correctly load",
                solo!!.waitForActivity(course.labs.activitylab.ActivityOne::class.java, timeout))


        val sleep = 1000
        solo!!.sleep(sleep)

        // ==================== Section Two =====================
        // Rotate the screen
        solo!!.setActivityOrientation(Solo.LANDSCAPE)

        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        Assert.assertTrue("DoubleRotateActivityOneTest failed:" +
                "Section Two:" +
                "ActivityOne did not correctly load after first LANDSCAPE rotation.",
                solo!!.waitForActivity(course.labs.activitylab.ActivityOne::class.java, timeout))


        solo!!.sleep(sleep)

        // ==================== Section Three =====================
        // Rotate the screen
        solo!!.setActivityOrientation(Solo.PORTRAIT)

        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        Assert.assertTrue("DoubleRotateActivityOneTest failed:" +
                "Section Three:" +
                "ActivityOne did not correctly load after second PORTRAIT rotation.",
                solo!!.waitForActivity(course.labs.activitylab.ActivityOne::class.java, timeout))

        solo!!.sleep(sleep)

        // Check for proper counts
        Assert.assertTrue("DoubleRotateActivityOneTest failed:" +
                "Section Three:" +
                "onCreate() count was off for ActivityOne after second PORTRAIT rotation.",
                solo!!.searchText("onCreate\\(\\) calls: 3"))
        Assert.assertTrue("DoubleRotateActivityOneTest failed:" +
                "Section Three:" +
                "onStart() count was off for ActivityOne after second PORTRAIT rotation.",
                solo!!.searchText("onStart\\(\\) calls: 3"))
        Assert.assertTrue("DoubleRotateActivityOneTest failed:" +
                "Section Three:" +
                "onResume() count was off for ActivityOne after second PORTRAIT rotation.",
                solo!!.searchText("onResume\\(\\) calls: 3"))
        Assert.assertTrue("DoubleRotateActivityOneTest failed:" +
                "Section Three:" +
                "onRestart() count was off for ActivityOne after second PORTRAIT rotation.",
                solo!!.searchText("onRestart\\(\\) calls: 0"))
    }

}
