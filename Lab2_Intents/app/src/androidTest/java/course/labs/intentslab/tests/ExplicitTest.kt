package course.labs.intentslab.tests

import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import junit.framework.Assert

import android.view.View

import course.labs.intentslab.ActivityLoaderActivity

class ExplicitTest : ActivityInstrumentationTestCase2<ActivityLoaderActivity>(ActivityLoaderActivity::class.java) {
    private var solo: Solo? = null

    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()

        solo = Solo(instrumentation, activity)

    }

    @Throws(Exception::class)
    public override fun tearDown() {
        super.tearDown()
        if (solo != null)
            solo!!.finishOpenedActivities()
    }

    // Executes the ExplicitTest
    fun testRun() {
        val delay = 1000
        // =================== Section One =====================
        // Wait for activity: 'course.labs.intentslab.ActivityLoaderActivity'
        Assert.assertTrue(
                "ExplicitTest:" + "Section One:"
                        + "ActivityLoaderActivity did not load correctly",
                solo!!.waitForActivity(ActivityLoaderActivity::class.java, delay))

        solo!!.sleep(delay)

        // Click on Explicit Activation Button
        solo!!.clickOnView(solo!!
                .getView(course.labs.intentslab.R.id.explicit_activation_button))
        solo!!.sleep(delay)

        // Wait for activity: 'course.labs.intentslab.ExplicitlyLoadedActivity'
        Assert.assertTrue(
                "ExplicitTest:" + "Section One:"
                        + "ExplicitlyLoadedActivity did not load correctly",
                solo!!.waitForActivity(course.labs.intentslab.ExplicitlyLoadedActivity::class.java, delay))

        solo!!.sleep(delay)

        // Checks that the ExplicitlyLoadedActivity was launched by the correct
        // Intent
        Assert.assertEquals(
                "ExplicitTest:"
                        + "Section One:"
                        + "ExplicitlyLoadedActivity was not launched by the correct Intent",
                "Intent { cmp=course.labs.intentslab/.ExplicitlyLoadedActivity }",
                solo!!.currentActivity.intent.toString())

        // =================== Section Two ==========================


        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        solo!!.sleep(delay)

        // Clear any text in the EditText
        solo!!.clearEditText(solo!!
                .getView(course.labs.intentslab.R.id.editText) as android.widget.EditText)

        // Enter the text: 'test'
        solo!!.enterText(solo!!
                .getView(course.labs.intentslab.R.id.editText) as android.widget.EditText, "test")

        // Hide the soft keyboard
        solo!!.hideSoftKeyboard()

        solo!!.sleep(delay)

        // Click on Enter Button
        solo!!.clickOnView(solo!!.getView(course.labs.intentslab.R.id.enter_button))

        // Assert that: 'textView1' is shown
        Assert.assertTrue("ExplicitTest:" + "Section Two:"
                + "textView1 did not show correctly", solo!!.waitForView<View>(solo!!
                .getView(course.labs.intentslab.R.id.textView1)))

        // Assert that the string 'test' is found in the display
        Assert.assertTrue(
                "ExplicitTest:"
                        + "Section Two:"
                        + "Correct text was not returned from ExplicitlyLoadedActivity to ActivityLoaderActivity",
                solo!!.searchText("test"))

    }
}
