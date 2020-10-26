/* See https://kotlinlang.org/docs/reference/ for reference material on
 the Kotlin language */

package course.labs.activitylab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class ActivityOne : Activity() {


    // Lifecycle counters

    // TODO:
    // Create counter variables for onCreate(), onRestart(), onStart() and
    // onResume()
    // You will need to increment these variables' values when their
    // corresponding lifecycle methods get called


    // TODO: Create variables for each of the TextViews

    private lateinit var create: TextView
    private lateinit var start: TextView
    private lateinit var resume: TextView
    private lateinit var restart: TextView
    private var onCreateCtr = 0
    private var onStartCtr = 0
    private var onResumeCtr = 0
    private var onRestartCtr = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        // TODO: Assign the appropriate TextViews to the TextView variables
        // Hint: Access the TextView by calling Activity's findViewById()
        // textView1 = findViewById(R.id.textView1);
        create = findViewById(R.id.create)
        start = findViewById(R.id.start)
        resume = findViewById(R.id.resume)
        restart = findViewById(R.id.restart)



        val launchActivityTwoButton = findViewById<Button>(R.id.bLaunchActivityTwo)
        launchActivityTwoButton.setOnClickListener {
            // TODO:
            // Launch Activity Two
            // Hint: use Context's startActivity() method

            // Create an intent stating which Activity you would like to start

            // Launch the Activity using the intent
            val intent = Intent(this@ActivityOne, ActivityTwo::class.java)
            startActivity(intent)
        }

        // Has previous state been saved?
        if (savedInstanceState != null) {

            // TODO:
            // Restore value of counters from saved state
            // Only need 4 lines of code, one for every count variable
            onCreateCtr = savedInstanceState.getInt(CREATE_KEY)
            onStartCtr =  savedInstanceState.getInt(START_KEY)
            onResumeCtr = savedInstanceState.getInt(RESUME_KEY)
            onRestartCtr = savedInstanceState.getInt(RESTART_KEY)

        }

        // Emit LogCat message
        Log.i(TAG, "Entered the onCreate() method")

        // TODO:
        // Update the appropriate count variable
        // Update the user interface via the displayCounts() method
        onCreateCtr++
        displayCounts()
    }

    // Lifecycle callback overrides

    public override fun onStart() {
        super.onStart()

        // Emit LogCat message
        Log.i(TAG, "Entered the onStart() method")

        // TODO:
        // Update the appropriate count variable
        // Update the user interface
        onStartCtr++
        displayCounts()
    }

    public override fun onResume() {
        super.onResume()
        Log.i(TAG, "Entered the onResume() method")
        // Emit LogCat message
        // Follow the previous 2 examples provided



        // TODO:
        // Update the appropriate count variable
        // Update the user interface
        onResumeCtr++
        displayCounts()
    }

    public override fun onPause() {
        super.onPause()
        Log.i(TAG,"Entered the onPause() method")
        // Emit LogCat message
        // Follow the previous 2 examples provided


    }

    public override fun onStop() {
        super.onStop()
        Log.i(TAG, "Entered the onStop() method")
        // Emit LogCat message
        // Follow the previous 2 examples provided


    }

    public override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "Entered the onRestart() method")
        // Emit LogCat message
        // Follow the previous 2 examples provided


        // TODO:
        // Update the appropriate count variable
        // Update the user interface
        onRestartCtr++
        displayCounts()
    }

    public override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Entered the onDestroy() method")
        // Emit LogCat message
        // Follow the previous 2 examples provided
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        // TODO:
        // Save state information with a collection of key-value pairs
        savedInstanceState.putInt(CREATE_KEY, onCreateCtr)
        savedInstanceState.putInt(RESUME_KEY, onResumeCtr)
        savedInstanceState.putInt(RESTART_KEY, onRestartCtr)
        savedInstanceState.putInt(START_KEY, onStartCtr)
    }

    // Updates the displayed counters
    private fun displayCounts() {
        // TODO:
        // set text for text view variables
        create.text = "onCreate() calls: " + onCreateCtr
        start.text = "onStart() calls: " + onStartCtr
        resume.text = "onResume() calls: " + onResumeCtr
        restart.text = "onRestart() calls: " + onRestartCtr
    }

    companion object {

        private val RESTART_KEY = "restart"
        private val RESUME_KEY = "resume"
        private val START_KEY = "start"
        private val CREATE_KEY = "create"

        // String for LogCat documentation
        private val TAG = "Lab-ActivityOne"
    }
}
