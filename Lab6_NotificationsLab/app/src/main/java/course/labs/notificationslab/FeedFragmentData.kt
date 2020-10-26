package course.labs.notificationslab

import android.content.Context
import android.util.Log
import android.util.SparseArray
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

// Utility class that provides stored Twitter feed data

class FeedFragmentData(private val mContext: Context) {

    private val mFeeds = SparseArray<String>()


    init {
        loadFeeds()
    }

    // Load all stored Twitter feeds into the mFeeds SparseArray.

    private fun loadFeeds() {

        for (id in IDS) {

            val inputStream = mContext.resources.openRawResource(
                    id)
            val reader =  BufferedReader(InputStreamReader(
                    inputStream))

            val buffer = StringBuffer("")


            // Read raw data from resource file

            try {

                var line = reader.readLine()
                while (line != null) {
                    buffer.append(line)
                    line = reader.readLine()
                }

            } catch (e: IOException) {
                Log.i(TAG, "IOException")
            }

            // Convert raw data into a String

            var feed: JSONArray? = null
            try {
                feed = JSONArray(buffer.toString())
            } catch (e: JSONException) {
                Log.i(TAG, "JSONException")
            }

            mFeeds.put(id, procFeed(feed!!))

        }
    }


    // Convert JSON formatted data to a String

    private fun procFeed(feed: JSONArray): String {

        var name = ""
        var tweet = ""

        // string buffer for twitter feeds
        val textFeed = StringBuffer("")

        for (j in 0 until feed.length()) {
            try {

                tweet = feed.getJSONObject(j).getString("text")
                val user = feed.getJSONObject(j)
                        .get("user") as JSONObject
                name = user.getString("name")

            } catch (e: JSONException) {

                Log.i(TAG, "JSONException while processing feed")
            }

            textFeed.append("$name - $tweet\n\n")
        }

        return textFeed.toString()
    }

    // Return the Twitter feed data for the specified position as a single String

    internal fun getFeed(position: Int): String {

        return mFeeds.get(IDS[position])

    }

    companion object {
        private const val TAG = "FeedFragmentData"
        private val IDS = intArrayOf(R.raw.ladygaga, R.raw.rebeccablack, R.raw.taylorswift)
    }
}
