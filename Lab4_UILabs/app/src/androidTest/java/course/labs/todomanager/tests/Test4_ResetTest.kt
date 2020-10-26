package course.labs.todomanager.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import course.labs.todomanager.ToDoManagerActivity
import junit.framework.Assert

class Test4_ResetTest : ActivityInstrumentationTestCase2<ToDoManagerActivity>(ToDoManagerActivity::class.java) {
    private var solo: Solo? = null

    @Throws(Exception::class)
    public override fun setUp() {
        solo = Solo(instrumentation, activity)
    }

    @Throws(Exception::class)
    public override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Executes the ResetTest
    fun testRun() {

        val delay = 2000

        // ============= Section One ==============
        // Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
        Assert.assertTrue(
                "ResetTest failed:" + "Section One:"
                        + "ToDoManagerActivity did not correctly load.",
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
                "ResetTest failed:" + "Section One:"
                        + "AddToDoActivity did not correctly load.",
                solo!!.waitForActivity(course.labs.todomanager.AddToDoActivity::class.java))

        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        // Enter the text: 't2'
        solo!!.clearEditText(solo!!
                .getView(course.labs.todomanager.R.id.title) as android.widget.EditText)

        solo!!.enterText(solo!!
                .getView(course.labs.todomanager.R.id.title) as android.widget.EditText, "t2")

        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        // Click on Done:
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.statusDone))

        // Click on High
        solo!!.clickOnView(solo!!
                .getView(course.labs.todomanager.R.id.highPriority))

        // Click on Reset
        solo!!.clickOnView(solo!!.getView(course.labs.todomanager.R.id.resetButton))

        solo!!.sleep(delay)

        // Click on Submit
        solo!!.clickOnView(solo!!
                .getView(course.labs.todomanager.R.id.submitButton))

        // ============= Section Two =================
        // Checks that reset button reset the text

        // Wait for activity: 'course.labs.todomanager.AddToDoActivity'
        Assert.assertTrue(
                "ResetTest failed:" + "Section Two:"
                        + "AddToDoActivity did not correctly load.",
                solo!!.waitForActivity(course.labs.todomanager.AddToDoActivity::class.java))

        Assert.assertFalse("ResetTest failed:" + "Section Two:"
                + "Title of ToDo Task was not correctly reset.",
                solo!!.searchText("t2"))

        // Makes sure that the check box is not checked
        Assert.assertFalse("ResetTest failed:" + "SectionTwo:"
                + "Done status of ToDo Task was not correctly reset",
                solo!!.isCheckBoxChecked(0))

        // Makes sure that the priority was reset to Medium
        Assert.assertTrue("ResetTest failed:" + "Section Two:"
                + "Priority of ToDo Task was not correctly reset",
                solo!!.searchText("[mM][eE][dD]"))

        // Clicks on the Done box
        solo!!.clickOnCheckBox(0)

        // Makes sure that was able to correctly change completion status from
        // ToDoManagerActivity
        Assert.assertTrue(
                "ResetTest failed:"
                        + "Section Two:"
                        + "Was not able to modify Done status of ToDo Task from ToDoManagerActivity",
                solo!!.isCheckBoxChecked(0))

    }
}