package course.labs.lab5_lifecycle_aware

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.view.View
import android.widget.TextView
import android.content.Context
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.history_list.view.*

class LifecycleMainActivity : AppCompatActivity() {
    companion object{
        private val TAG = "LifecycleMainActivity"
    }
    private lateinit var mAdapter: HistoryListAdapter
    private fun getScreenOrientation(context: Context): String {
        val orientationList = listOf("Portrait","Landscape","Reverse Portrait","Reverse Landscape")
        val orientation = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.rotation
        return orientationList[orientation]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ToDo: Implement your own logic to display appropriate text ,increase and reset the counter and populate the history list
        // Add the Activity observer to preserve the counts till now and add  it to the list when the activity is created again
        val currOrientation = getScreenOrientation(this)
        val displayText = findViewById<View>(R.id.DisplayText)
        if(currOrientation == "Portrait"){
            displayText.Text.text = "Portrait" + mAdapter.getItem(0)
        }else if(currOrientation == "Landscape"){

        }else if(currOrientation == "Reverse Portrait"){

        }else if(currOrientation == "Reverse Landscape"){

        }

        val nextButton = findViewById<View>(R.id.PrButton)
        nextButton.setOnClickListener{
            Log.i(TAG, "Entered nextButton")

        }
    }
}
