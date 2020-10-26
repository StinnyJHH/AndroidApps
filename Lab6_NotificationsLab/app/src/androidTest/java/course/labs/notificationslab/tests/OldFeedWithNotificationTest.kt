package course.labs.notificationslab.tests

import android.test.ActivityInstrumentationTestCase2
import com.robotium.solo.Solo
import course.labs.notificationslab.MainActivity
import course.labs.notificationslab.R
import course.labs.notificationslab.TestFrontEndActivity

class OldFeedWithNotificationTest : ActivityInstrumentationTestCase2<TestFrontEndActivity>(TestFrontEndActivity::class.java) {
    private var solo: Solo? = null
    @Throws(Exception::class)
    public override fun setUp() {
        solo = Solo(instrumentation)
        activity
    }

    @Throws(Exception::class)
    public override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    fun testRun() {
        val shortDelay = 2000

        // Clear the log
        solo!!.clearLog()

        // Wait for activity:
        // 'course.labs.notificationslab.TestFrontEndActivity'
        solo!!.waitForActivity(
                TestFrontEndActivity::class.java,
                shortDelay)

        // Click on Make Tweets Old
        solo!!.clickOnView(solo!!
                .getView(R.id.age_tweets_button))

        // Click on Start Main Activity
        solo!!.clickOnView(solo!!
                .getView(R.id.start_main_button))

        // Wait for activity: 'course.labs.notificationslab.MainActivity'
        assertTrue(
                "course.labs.notificationslab.MainActivity is not found!",
                solo!!.waitForActivity(MainActivity::class.java))

        // Press menu back key
        solo!!.goBackToActivity("TestFrontEndActivity")


/*
		// Wait for activity:
		// 'course.labs.notificationslab.TestFrontEndActivity'
//		assertTrue(
//				"course.labs.notificationslab.TestFrontEndActivity is not found!",
//				solo.waitForActivity(course.labs.notificationslab.TestFrontEndActivity.class));
*/
        // Wait for activity: 'course.labs.notificationslab.TestFrontEndActivity'
        assertTrue(
                "course.labs.notificationslab.TestFrontEndActivity is not found!",
                solo!!.waitForActivity(TestFrontEndActivity::class.java))


        // Robotium can't check notification area directly
        val msg = activity.getString(R.string.notification_sent_string)
        assertTrue("Notification was not sent", solo!!.waitForText(msg))
    }
}