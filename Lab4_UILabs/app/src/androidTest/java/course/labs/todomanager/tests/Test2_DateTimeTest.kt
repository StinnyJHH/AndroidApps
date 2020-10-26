package course.labs.todomanager.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import course.labs.todomanager.ToDoManagerActivity
import junit.framework.Assert

class Test2_DateTimeTest : ActivityInstrumentationTestCase2<ToDoManagerActivity>(ToDoManagerActivity::class.java) {

    private var solo: Solo? = null

    @Throws(Exception::class)
    override fun setUp() {
        solo = Solo(instrumentation)
        activity
    }

    @Throws(Exception::class)
    override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Executes DateTimeTest
    fun testRun() {

        val delay = 2000

        // ============== Section One ================

        // Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
        Assert.assertTrue(
                "DateTimeTest failed:" + "Section One:"
                        + "ToDoManagerActivity did not correctly load.",
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
                "DateTimeTest failed:" + "Section One:"
                        + "AddToDoActivity did not correctly load.",
                solo!!.waitForActivity(course.labs.todomanager.AddToDoActivity::class.java))

        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        // Enter the text: 't1'
        solo!!.clearEditText(solo!!
                .getView(course.labs.todomanager.R.id.title) as android.widget.EditText)

        solo!!.enterText(solo!!
                .getView(course.labs.todomanager.R.id.title) as android.widget.EditText, "t1")

        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        // Click on Done:
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.statusDone))

        // Click on Low
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.lowPriority))

        // Click on Choose Date
        solo!!.clickOnView(solo!!
                .getView(course.labs.todomanager.R.id.date_picker_button))

        // Wait for dialog
        solo!!.waitForDialogToOpen(10000)

        // Really set the date
        solo!!.setDatePicker(0, 2014, 1, 28)

        // Click on Done
        solo!!.clickOnView(solo!!.getView(android.R.id.button1))

        solo!!.sleep(delay)

        // Click on Choose Time
        solo!!.clickOnView(solo!!
                .getView(course.labs.todomanager.R.id.time_picker_button))

        // Wait for dialog
        solo!!.waitForDialogToOpen(10000)


        // Really set the time
        solo!!.setTimePicker(0, 9, 19)

        // Click on Done
        solo!!.clickOnView(solo!!.getView(android.R.id.button1))

        solo!!.sleep(delay)

        // Click on Submit
        solo!!.clickOnView(solo!!
                .getView(course.labs.todomanager.R.id.submitButton))

        // Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
        Assert.assertTrue(
                "DateTimeTest failed:" + "Section One:"
                        + "ToDoManagerActivity did not load correctly",
                solo!!.waitForActivity(course.labs.todomanager.ToDoManagerActivity::class.java))

        // ============== Section Two =============

        // Makes sure the title was changed correctly
        Assert.assertTrue("DateTimeTest failed:" + "Section Two:"
                + "Did not modify title correctly", solo!!.searchText("t1"))

        // Checks to see if the status was changed correctly
        Assert.assertTrue("DateTimeTest failed:" + "Section Two:"
                + "Did not change status correctly", solo!!.isCheckBoxChecked(0))

        // Checks to make sure the priority was correctly set
        Assert.assertTrue("DateTimeTest failed:" + "Section Two:"
                + "Did not correctly set priority",
                solo!!.searchText("[lL][oO][wW]"))

        // Checks to make sure the Date was correctly set
        Assert.assertTrue("DateTimeTest failed:" + "Section Two:"
                + "Did not correctly set the date",
                solo!!.searchText("2014-02-28"))

        // Checks to make sure the Time was correctly set
        Assert.assertTrue("DateTimeTest failed:" + "Section Two:"
                + "Did not correctly set the time", solo!!.searchText("09:19:00"))

    }

}