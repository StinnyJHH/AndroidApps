package course.labs.intentslab.mybrowser

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MyBrowserActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_browser_activity)

        // Save the String passed with the intent
        var url = intent.dataString

        if (null == url)
            url = "No Data Provided"

        // Get a reference to the TextView and set the text it to the String
        val textView = findViewById<TextView>(R.id.url)
        textView.text = url
    }

}
