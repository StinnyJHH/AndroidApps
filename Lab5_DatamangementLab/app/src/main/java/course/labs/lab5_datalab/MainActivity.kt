package course.labs.lab5_datalab

import androidx.appcompat.app.AppCompatActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import java.util.*

//import javax.swing.text.View

class MainActivity : AppCompatActivity() {

    protected lateinit var sharedpreferences: SharedPreferences
    protected lateinit var name: TextView
    protected lateinit var uidtv: TextView
    protected lateinit var recent: Pair<String,String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate()")
        name = findViewById(R.id.etName)
        uidtv = findViewById(R.id.etUid)
        val save = findViewById<View>(R.id.btnSave) as Button
        val retrieve = findViewById<View>(R.id.btnRetr) as Button
        val clear = findViewById<View>(R.id.btnClear) as Button
        val ext = findViewById<View>(R.id.button2) as Button
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE)
        name.text = sharedpreferences.getString(Name, "")
        uidtv.text = sharedpreferences.getString(uid, "")
        save.setOnClickListener{
            Log.i(TAG, "save button clicked")
            save()
        }
        retrieve.setOnClickListener{
            Log.i(TAG, "retrieve button clicked")
            get()
        }
        clear.setOnClickListener{
            Log.i(TAG, "clear button clicked")
            clear()
        }
        ext.setOnClickListener{
            Log.i(TAG, "external storage button clicked")
            goToAnActivity()
        }
    }

    fun save() {
        Log.i(TAG,"entered Save() function")
        sharedpreferences.edit().putString(Name, name.text.toString()).apply()
        sharedpreferences.edit().putString(uid, uidtv.text.toString()).apply()
    }

    fun clear() {
        Log.i(TAG, "entered clear() function")
        name.setText("")
        uidtv.setText("")
    }

    fun get() {
        name.setText(sharedpreferences.getString(Name, ""))
        uidtv.setText(sharedpreferences.getString(uid, ""))
    }

    fun goToAnActivity() {
        val intent = Intent(this, External::class.java)
        startActivity(intent)
    }

    companion object {
        val mypreference = "mypref"
        val Name = "nameKey"
        val uid = "uidkey"
        val TAG = "MainActivity"
    }
}
