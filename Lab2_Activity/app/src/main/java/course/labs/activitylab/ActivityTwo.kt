/* See https://kotlinlang.org/docs/reference/ for reference material on
 the Kotlin language */

package course.labs.activitylab

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class ActivityTwo : Activity() {

    // Lifecycle counters

    // TODO:
    // Create counter variables for onCreate(), onRestart(), onStart() and
    // onResume()
    // You will need to increment these variables' values when their
    // corresponding lifecycle methods get called

    private lateinit var create: TextView
    private lateinit var start: TextView
    private lateinit var resume: TextView
    private lateinit var restart: TextView
    private var onCreate = 0
    private var onStart = 0
    private var onResume = 0
    private var onRestart = 0

    // TODO: Create variables for each of the TextViews

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        // TODO: Assign the appropriate TextViews to the TextView variables
        // Hint: Access the TextView by calling Activity's findViewById()
        // textView1 = (TextView) findViewById(R.id.textView1);
        create = findViewById(R.id.create)
        start = findViewById(R.id.start)
        resume = findViewById(R.id.resume)
        restart = findViewById(R.id.restart)


        val closeButton = findViewById<Button>(R.id.bClose)
        closeButton.setOnClickListener {
            // TODO:
            // This function closes Activity Two
            // Hint: use Context's finish() method
            finish()
        }

        // Has previous state been saved?
        if (savedInstanceState != null) {
            // TODO:
            // Restore value of counters from saved state
            // Only need 4 lines of code, one for every count variable
            onCreate = savedInstanceState.getInt(CREATE_KEY)
            onStart = savedInstanceState.getInt(START_KEY)
            onResume = savedInstanceState.getInt(RESUME_KEY)
            onRestart = savedInstanceState.getInt(RESTART_KEY)
        }

        // Emit LogCat message

        Log.i(TAG, "Entered the onCreate() method")

        // TODO:
        // Update the appropriate count variable
        // Update the user interface via the displayCounts() method
        ++onCreate
        displayCounts()
    }

    // Lifecycle callback methods overrides

    public override fun onStart() {
        super.onStart()

        // Emit LogCat message
        Log.i(TAG, "Entered the onStart() method")

        // TODO:
        // Update the appropriate count variable
        // Update the user interface
        ++onStart
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
        ++onResume
        displayCounts()
    }

    public override fun onPause() {
        super.onPause()
        Log.i(TAG, "Entered the onPause() method")
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
        onRestart++
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
        // Save counter state information with a collection of key-value pairs
        // 4 lines of code, one for every count variable
        savedInstanceState.putInt(CREATE_KEY, onCreate)
        savedInstanceState.putInt(RESUME_KEY, onResume)
        savedInstanceState.putInt(RESTART_KEY, onRestart)
        savedInstanceState.putInt(START_KEY, onStart)
    }

    // Updates the displayed counters
    private fun displayCounts() {
        create.text = ("onCreate() calls: " + onCreate)
        start.text = ("onStart() calls: " + onStart)
        resume.text = ("onResume() calls: " + onResume)
        restart.text = ("onRestart() calls: " + onRestart)
    }

    companion object {

        private val RESTART_KEY = "restart"
        private val RESUME_KEY = "resume"
        private val START_KEY = "start"
        private val CREATE_KEY = "create"

        // String for LogCat documentation
        private val TAG = "Lab-ActivityTwo"
    }
}
