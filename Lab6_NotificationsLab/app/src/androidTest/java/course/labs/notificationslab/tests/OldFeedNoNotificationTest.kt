package course.labs.notificationslab.tests

import android.test.ActivityInstrumentationTestCase2
import android.view.View
import com.robotium.solo.Solo
import course.labs.notificationslab.MainActivity
import course.labs.notificationslab.R
import course.labs.notificationslab.TestFrontEndActivity

class OldFeedNoNotificationTest : ActivityInstrumentationTestCase2<TestFrontEndActivity>(TestFrontEndActivity::class.java) {
    private var solo: Solo? = null
    @Throws(Exception::class)
    public override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    public override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    fun testRun() {
        val shortDelay = 2000
        val longDelay = 10000

        // Wait for activity:
        // 'course.labs.notificationslab.TestFrontEndActivity'
        solo!!.waitForActivity(
                TestFrontEndActivity::class.java, shortDelay)

        // Click on Make Tweets Old
        solo!!.clickOnView(solo!!
                .getView(R.id.age_tweets_button))

        // Click on Start Main Activty
        solo!!.clickOnView(solo!!
                .getView(R.id.start_main_button))

        // Wait for activity: 'course.labs.notificationslab.MainActivity'
        assertTrue("course.labs.notificationslab.MainActivity is not found!",
                solo!!.waitForActivity(
                        MainActivity::class.java, shortDelay))

        // Assert that: Toast message is shown
        val msg = activity.getString(R.string.download_in_progress_string)
        assertTrue("'$msg	' is not shown!",
                solo!!.searchText(msg))
        solo!!.waitForView(android.R.id.list)
        val listView = solo!!.getView(android.R.id.list)
        solo!!.waitForCondition({ listView.isEnabled }, longDelay)
        val failMsg = activity.getString(R.string.download_failed_string)
        val successMsg = activity.getString(R.string.download_succes_string)
        assertTrue("Toast message did not appear", solo!!.searchText("($failMsg|$successMsg)"))

        // Click on ladygaga
        solo!!.clickOnView(solo!!.getView(android.R.id.text1))

        // Assert that: 'feed_view' is shown
        assertTrue("feed_view' is not shown!", solo!!.waitForView<View?>(solo!!
                .getView(R.id.feed_view)))
    }
}