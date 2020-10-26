package course.labs.todomanager.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import course.labs.todomanager.ToDoManagerActivity
import junit.framework.Assert

class Test3_CancelTest : ActivityInstrumentationTestCase2<ToDoManagerActivity>(ToDoManagerActivity::class.java) {
    private var solo: Solo? = null

    @Throws(Exception::class)
    public override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    public override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Executes the CancelTest
    fun testRun() {

        val delay = 2000

        // Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
        Assert.assertTrue(
                "CancelTest failed:" + "Section One:"
                        + "ToDoManagerActivity did not load correctly.",
                solo!!.waitForActivity(course.labs.todomanager.ToDoManagerActivity::class.java))

        // Click on action bar item
        solo!!.clickOnActionBarItem(0x1)

        solo!!.sleep(delay)

        // Get footer view
        Assert.assertTrue("FooterView didn't appear",
                solo!!.waitForView(course.labs.todomanager.R.id.footerView))

        // Click on Add New ToDo Item
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.footerView))

        // Wait for activity: 'course.labs.todomanager.AddToDoActivity'
        Assert.assertTrue(
                "CancelTest failed:" + "Section One:"
                        + "AddToDoActivity did not load correctly.",
                solo!!.waitForActivity(course.labs.todomanager.AddToDoActivity::class.java))

        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        // Enter the text: 't3'
        solo!!.clearEditText(solo!!
                .getView(course.labs.todomanager.R.id.title) as android.widget.EditText)

        solo!!.enterText(solo!!
                .getView(course.labs.todomanager.R.id.title) as android.widget.EditText, "t3")

        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        // Click on Done:
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.statusDone))

        // Click on High
        solo!!.clickOnView(solo!!
                .getView(course.labs.todomanager.R.id.highPriority))

        // Click on Cancel
        solo!!.clickOnView(solo!!
                .getView(course.labs.todomanager.R.id.cancelButton))

        // Wait for activity: 'course.labs.todomanager.AddToDoActivity'
        Assert.assertTrue(
                "Cancel test failed:" + "Section One:"
                        + "AddToDoActivity did not correctly load.",
                solo!!.waitForActivity(course.labs.todomanager.AddToDoActivity::class.java))

        // Click on Add New ToDo Item
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.footerView))

        // Wait for activity: 'course.labs.todomanager.AddToDoActivity'
        Assert.assertTrue(
                "CancelTest failed:" + "Section One:"
                        + "AddToDoActivity did not load correctly.",
                solo!!.waitForActivity(course.labs.todomanager.AddToDoActivity::class.java))

        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        // Enter the text: 't4'
        solo!!.clearEditText(solo!!
                .getView(course.labs.todomanager.R.id.title) as android.widget.EditText)

        solo!!.enterText(solo!!
                .getView(course.labs.todomanager.R.id.title) as android.widget.EditText, "t4")

        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        // Click on Done:
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.statusDone))

        // Click on Low
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.lowPriority))

        // Click on Submit
        solo!!.clickOnView(solo!!
                .getView(course.labs.todomanager.R.id.submitButton))

        // ================ Section Two ===================

        // Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
        Assert.assertTrue(
                "CancelTest failed:" + "Section Two:"
                        + "ToDoManagerActivity did not load correctly.",
                solo!!.waitForActivity(course.labs.todomanager.ToDoManagerActivity::class.java))

        Assert.assertFalse("CancelTest failed:" + "Section Two:"
                + "Did not correctly cancel the creation of a ToDo Task.",
                solo!!.searchText("t3"))

        Assert.assertTrue("CancelTest failed:" + "Section Two:"
                + "Did not correctly set title of ToDo Task following cancel.",
                solo!!.searchText("t4"))

        Assert.assertTrue(
                "CancelTest failed:"
                        + "Section Two:"
                        + "Did not correctly set priority of ToDo Task following cancel.",
                solo!!.searchText("[lL][oO][wW]"))

    }
}
