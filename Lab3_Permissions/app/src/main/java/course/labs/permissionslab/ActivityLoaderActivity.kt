package course.labs.permissionslab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class ActivityLoaderActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loader_activity)

        val startPhoneStatusButton = findViewById<View>(R.id.start_phone_status_button) as Button
    // TODO - Add onClickListener to the startPhoneStatusButton to call startPhoneStatusActivity()
    startPhoneStatusButton.setOnClickListener{
        startPhoneStatusActivity()
    }
}

private fun startPhoneStatusActivity() {
    Log.i(TAG, "Entered startPhoneStatusActivity()")

    val intent = Intent(this, PhoneStatusActivity::class.java)
    startActivity(intent)
    // TODO - Start the PhoneStatusActivity
}

    companion object {

        private val TAG = "Lab-Permissions"
    }
}
