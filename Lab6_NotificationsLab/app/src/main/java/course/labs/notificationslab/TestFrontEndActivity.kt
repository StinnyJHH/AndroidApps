package course.labs.notificationslab

import android.app.Activity
import android.content.Intent
import android.content.res.Resources.NotFoundException
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import java.io.*

class TestFrontEndActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Starting FrontEndActivity...")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_front_end)
        sFileName = filesDir.toString() + "/" + MainActivity.TWEET_FILENAME
        val ageTweetsButton = findViewById<View?>(R.id.age_tweets_button) as Button
        ageTweetsButton.setOnClickListener { setFileAge(DAWN_OF_TIME) }
        val rejuvTweetsButton = findViewById<View?>(R.id.rejuv_tweets_button) as Button
        rejuvTweetsButton.setOnClickListener { setFileAge(System.currentTimeMillis()) }
        val startMainActivityButton = findViewById<View?>(R.id.start_main_button) as Button
        startMainActivityButton.setOnClickListener {
            startActivity(Intent(this@TestFrontEndActivity,
                    MainActivity::class.java))
        }
        createTweetFileIfMissing()
    }

    private fun createTweetFileIfMissing() {
        val file = File(sFileName)
        if (!file.exists()) {
            var out: PrintWriter? = null
            var `in`: BufferedReader? = null
            try {
                out = PrintWriter(BufferedWriter(
                        OutputStreamWriter(openFileOutput(
                                MainActivity.TWEET_FILENAME,
                                MODE_PRIVATE))))
                for (resId in MainActivity.sRawTextFeedIds) {
                    `in` = BufferedReader(InputStreamReader(
                            resources.openRawResource(resId)))
                    var line: String?
                    val buffer = StringBuffer()
                    while (`in`.readLine().also { line = it } != null) {
                        buffer.append(line)
                    }
                    out.println(buffer)
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: NotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    `in`?.close()
                    out?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun setFileAge(timestamp: Long) {
        val file = File(sFileName)
        if (file.exists()) {
            file.setLastModified(timestamp)
        }
    }

    companion object {
        private const val DAWN_OF_TIME: Long = 0
        private val TAG: String? = "TestFrontEndActivity"
        private var sFileName: String? = null
    }
}