package course.labs.intentslab.tests

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObject2
import android.support.test.uiautomator.Until

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Assert.fail

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 21)
class TestImplicitIntent {
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
    }

    @Test
    fun TestMyBrowserAvailable() {
        mDevice!!.findObject(By.res(BASIC_SAMPLE_PACKAGE, "implicit_activation_button")).click()
        val chooserContents = mDevice!!.wait(Until.findObject(By.text("MyBrowser")), 2000)
        assertNotNull(chooserContents)

        mDevice!!.pressBack()
    }

    companion object {

        private val BASIC_SAMPLE_PACKAGE = "course.labs.intentslab"
        private val LAUNCH_TIMEOUT = 5000
    }
}