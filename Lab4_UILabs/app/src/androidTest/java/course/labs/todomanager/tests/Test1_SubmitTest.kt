package course.labs.todomanager.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import course.labs.todomanager.ToDoManagerActivity
import junit.framework.Assert

class Test1_SubmitTest : ActivityInstrumentationTestCase2<ToDoManagerActivity>(ToDoManagerActivity::class.java) {

    private var solo: Solo? = null

    @Throws(Exception::class)
    override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Execute the SubmitTest
    fun testRun() {

        val delay = 2000

        // ============= Section One ===============
        // Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
        Assert.assertTrue(
                "SubmitTest failed:" + "Section One:"
                        + "ToDoManagerActivity did not load correctly.",
                solo!!.waitForActivity(course.labs.todomanager.ToDoManagerActivity::class.java))

        // Click on action bar item to delete all items
        solo!!.clickOnActionBarItem(0x1)

        solo!!.sleep(delay)

        // Get footer view
        Assert.assertTrue("FooterView didn't appear",
                solo!!.waitForView(course.labs.todomanager.R.id.footerView))

        // Click on Add New ToDo Item
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.footerView))

        // Wait for activity: 'course.labs.todomanager.AddToDoActivity'
        Assert.assertTrue(
                "Submit Test failed:" + "Section One:"
                        + "AddToDoActivity did not correctly load.",
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

        // ================= Section Two ================
        // Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
        Assert.assertTrue(
                "SubmitTest failed:"
                        + "Section Two:"
                        + "ToDoManagerActivity did not load correctly after pressing submit.",
                solo!!.waitForActivity(course.labs.todomanager.ToDoManagerActivity::class.java))

        Assert.assertTrue("SubmitTest failed:" + "Section Two:"
                + "Title was not correctly entered in the ToDoManager",
                solo!!.searchText("t4"))

        Assert.assertTrue("SubmitTest failed:" + "Section Two:"
                + "Priority was not correctly entered in the ToDoManager",
                solo!!.searchText("[lL][oO][wW]"))

        Assert.assertTrue("SubmitTest failed:" + "Section Two:"
                + "Did not correctly set completion status.",
                solo!!.isCheckBoxChecked(0))

    }

}
