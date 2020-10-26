package course.labs.lab5_datalab

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
import course.labs.lab5_datalab.MainActivity
import course.labs.lab5_datalab.R
import org.junit.After


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class SharedPrefTest {
        @Rule
        @JvmField
        var activityTestRule = ActivityTestRule(MainActivity::class.java)

        private val PREFS_NAME = "mypref"
        private val KEY_PREF = "nameKey"
        private var sharedPreferences: SharedPreferences? = null

        @Before
        fun before() {
            val context = getInstrumentation().getTargetContext()
            sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }


//        @After
//        fun after() {
//            sharedPreferences!!.edit().putString(KEY_PREF, null).apply()
//        }

        @Test
        fun acheckNameUID() {
            onView(withId(R.id.etName)).perform(clearText(), typeText("Test Name"));
            onView(withId(R.id.etUid)).perform(clearText(), typeText("1234568"));
            onView(withId(R.id.btnSave)).perform(click())
            Thread.sleep(2000)
            val targetContext = getInstrumentation().targetContext

            val context = getInstrumentation().getTargetContext()
            sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val string2 = sharedPreferences!!.getString("nameKey", "")
            assertEquals("Test Name", string2)
            val string3 = sharedPreferences!!.getString("uidkey", "")
            assertEquals("1234568", string3)
        }

        @Test
        fun bcheckClear() {
            onView(withId(R.id.btnClear)).perform(click())
            Thread.sleep(2000)
            val textView: TextView = activityTestRule.activity.findViewById(R.id.etName)
            assertEquals(textView.text.toString(), "")
            val textView2: TextView = activityTestRule.activity.findViewById(R.id.etUid)
            assertEquals(textView2.text.toString(), "")
        }

        @Test
        fun ccheckEditText() {
            onView(withId(R.id.btnRetr)).perform(click())
            Thread.sleep(2000)
            val textView: TextView = activityTestRule.activity.findViewById(R.id.etName)
            assertEquals(textView.text.toString(), "Test Name")
            val textView2: TextView = activityTestRule.activity.findViewById(R.id.etUid)
            assertEquals(textView2.text.toString(), "1234568")

        }





}
