package course.labs.permissionslab

import android.Manifest
import android.Manifest.permission.READ_PHONE_NUMBERS
import android.Manifest.permission.READ_PHONE_STATE
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class PhoneStatusActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_status_activity)

        val getPhoneNumButton = findViewById<View>(R.id.get_phone_number_button) as Button
        // TODO - Add onClickListener to the getPhoneNumButton to call loadPhoneNumber()
        getPhoneNumButton.setOnClickListener{
            loadPhoneNumber()
        }

        val goToDangerousActivityButton = findViewById<View>(R.id.go_to_dangerous_activity_button) as Button
        // TODO - Add onClickListener to the goToDangerousActivityButton to call startGoToDangerousActivity()
        goToDangerousActivityButton.setOnClickListener{
            startGoToDangerousActivity()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun loadPhoneNumber() {
        if (ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
            val tMgr = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val mPhoneNumber = tMgr.line1Number

            val box = findViewById<View>(R.id.text) as TextView
            box.text = "Phone Number: $mPhoneNumber"
            Log.i(TAG, "Phone Number loaded")
        }
        else{
            requestPermissions(arrayOf(READ_PHONE_STATE), MY_PERMISSIONS_REQUEST_READ_PHONE_STATE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_PHONE_STATE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    loadPhoneNumber()

                } else {

                    Log.i(TAG, "Phone Number was not loaded --- Permission was not granted")

                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request
    }

    private fun startGoToDangerousActivity() {

        Log.i(TAG, "Entered startGoToDangerousActivity()")

        // TODO - Start the GoToDangerousActivity
        startActivity(Intent(this, GoToDangerousActivity::class.java))

    }

    companion object {

        private val TAG = "Lab-Permissions"
        val MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1
    }

}
