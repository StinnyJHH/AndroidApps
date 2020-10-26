package course.labs.lab5_datalab

import org.junit.Assert.*
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.Before
import org.junit.Rule
//import junit.framework.Assert;
import org.junit.Assert.assertEquals
import org.junit.Assert;
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import android.content.SharedPreferences
import android.widget.EditText
import android.widget.TextView
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import androidx.test.InstrumentationRegistry.getTargetContext
import course.labs.lab5_datalab.External
import course.labs.lab5_datalab.R
import org.junit.After
import java.io.File


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExternalTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(External::class.java)

    private val PREFS_NAME = "mypref"
    private val KEY_PREF = "nameKey"
    private var sharedPreferences: SharedPreferences? = null

    @Before
    fun before() {
        val context = getInstrumentation().getTargetContext()
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }



    @Test
    fun checkNameUID() {
//        onView(withId(R.id.button2)).perform(click())
        lateinit var myExternalFile: File
        val context = getInstrumentation().getTargetContext()
        val filename = "SampleFile.txt"
        val filepath = "MyFileStorage"
        onView(withId(R.id.myInputText)).perform(clearText(), typeText("Test Name"));
        myExternalFile = File(context.getExternalFilesDir(filepath), filename)
        onView(withId(R.id.saveExternalStorage)).perform(click())
        val targetContext = getInstrumentation().targetContext
        val str = myExternalFile.readText()
        assertEquals(str,  "Test Name")

    }

    @Test
    fun checkRetrieve() {
//        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.getExternalStorage)).perform(click())
//        Thread.sleep(5000)
        val textView: TextView = activityTestRule.activity.findViewById(R.id.myInputText)
        assertEquals(textView.text.toString(), "Test Name")

    }


}
