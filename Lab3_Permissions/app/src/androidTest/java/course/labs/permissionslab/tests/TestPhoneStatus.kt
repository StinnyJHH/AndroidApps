package course.labs.permissionslab.tests

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObject2
import android.support.test.uiautomator.Until
import android.test.ActivityInstrumentationTestCase2

import com.robotium.solo.Solo

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import java.util.regex.Pattern

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Assert.fail

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 21)
class TestPhoneStatus {
    private var mDevice: UiDevice? = null
    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        mDevice!!.pressHome()

        // Wait for launcher
        val launcherPackage = mDevice!!.launcherPackageName
        assertThat(launcherPackage, `is`(notNullValue()))
        mDevice!!.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT.toLong())

        // Launch the app
        val context = InstrumentationRegistry.getContext()
        val intent = context.packageManager
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)

        if (null == intent) fail()

        // Clear out any previous instances
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        // Wait for the app to appear
        mDevice!!.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT.toLong())
        //click on Phone Status Activity
        mDevice!!.findObject(By.text("PhoneStatus Activity")).click()

        val phoneStatusActivity = mDevice!!.wait(Until.findObject(By.text("Get Phone Number")), 2000)
        assertNotNull("Phone Status Activity Didn't start", phoneStatusActivity)
        phoneStatusActivity.click()
    }

    @Test
    fun testPermissionRequested() {
        val allowButtonPattern = Pattern.compile("ALLOW", Pattern.CASE_INSENSITIVE)
        val permissionDialog = mDevice!!.wait(Until.findObject(By.text(allowButtonPattern)), 2000)
        permissionDialog?.click()
        val phonePattern = Pattern.compile("Phone Number: \\+?\\d+")
        val phoneNumber = mDevice!!.wait(Until.findObject(By.text(phonePattern)), 5000)
        assertNotNull(phoneNumber)
    }

    companion object {

        private val LAUNCH_TIMEOUT = 5000
        private val BASIC_SAMPLE_PACKAGE = "course.labs.permissionslab"
    }
}
