package course.labs.fragmentslab.test

import course.labs.fragmentslab.MainActivity
import com.robotium.solo.*
import android.test.ActivityInstrumentationTestCase2
import android.view.View
import junit.framework.Assert

class TabletTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {
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

        val delay = 2000

        // Wait for activity: 'course.labs.fragmentslab.MainActivity'
        solo!!.waitForActivity(MainActivity::class.java, delay)

        // Wait for view: 'android.R.id.text1'
        Assert.assertTrue("text1 not found", solo!!.waitForView(android.R.id.text1))

        // Assert that: feed_view is shown
        Assert.assertTrue("'feed_view' was not found!", solo!!.waitForView<View>(solo!!
                .getView(course.labs.fragmentslab.R.id.feed_view)))

        // Click on ladygaga
        solo!!.clickOnView(solo!!.getView(android.R.id.text1))

        // Assert that: feed_view is shown
        Assert.assertTrue("'Select a feed to view!' is not shown!",
                solo!!.waitForView<View>(solo!!
                        .getView(course.labs.fragmentslab.R.id.feed_view)))

        // Assert that: 'the audience cheering!' is shown
        Assert.assertTrue("'the audience cheering!' is not shown!",
                solo!!.searchText("the audience cheering!"))

        // Click on msrebeccablack
        solo!!.clickOnView(solo!!.getView(android.R.id.text1, 1))

        // Assert that: feed_view is shown
        Assert.assertTrue("'Select a feed to view!' is not shown!",
                solo!!.waitForView<View>(solo!!
                        .getView(course.labs.fragmentslab.R.id.feed_view)))

        // Assert that: 'save me from school' is shown
        Assert.assertTrue("'save me from school' is not shown!",
                solo!!.searchText("save me from school"))

        // Click on taylorswift13
        solo!!.clickOnView(solo!!.getView(android.R.id.text1, 2))

        // Assert that: feed_view is shown
        Assert.assertTrue("'Select a feed to view!' is not shown!",
                solo!!.waitForView<View>(solo!!
                        .getView(course.labs.fragmentslab.R.id.feed_view)))

        // Assert that: 'I love you guys so much' is shown
        Assert.assertTrue("'I love you guys so much' is not shown!",
                solo!!.searchText("I love you guys so much"))

    }
}