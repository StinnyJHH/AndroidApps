package course.labs.permissionslab.tests

import android.content.ComponentName
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.test.ActivityInstrumentationTestCase2
import junit.framework.Assert


import com.robotium.solo.Solo

import course.labs.permissionslab.ActivityLoaderActivity

class PermissionEnforcementTest : ActivityInstrumentationTestCase2<ActivityLoaderActivity>(ActivityLoaderActivity::class.java) {
    private var solo: Solo? = null

    @Throws(Exception::class)
    public override fun setUp() {
        solo = Solo(instrumentation)
        activity
    }

    @Throws(Exception::class)
    public override fun tearDown() {
        solo!!.finishOpenedActivities()
    }

    // Executes PermissionEnforcementTest
    fun testRun() {

        // =============== Section One ==================
        solo!!.waitForActivity(
                course.labs.permissionslab.ActivityLoaderActivity::class.java, 2000)

        val pm = activity.packageManager
        try {
            val activityInfo = pm.getActivityInfo(ComponentName(
                    "course.labs.dangerousapp",
                    "course.labs.dangerousapp.DangerousActivity"), 0)
            Assert.assertTrue(
                    "PermissionEnforcementTest:" +
                            "Section One:" +
                            "course.labs.permissions.DANGEROUS_ACTIVITY_PERM not enforced by DangerousActivity",
                    null != activityInfo && null != activityInfo.permission
                            && activityInfo.permission == "course.labs.permissions.DANGEROUS_ACTIVITY_PERM")
        } catch (e: NameNotFoundException) {
            Assert.fail("PermissionEnforcementTest:" +
                    "Section One:" +
                    "DangerousActivity not found")
        }

    }
}
