package course.labs.notificationslab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FeedFragment : Fragment() {

    private var mTextView: TextView? = null
    private var feedFragmentData: FeedFragmentData? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.feed, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Read in all Twitter feeds
        if (null == feedFragmentData) {
            feedFragmentData = FeedFragmentData(activity)
        }
    }

    // Display Twitter feed for selected feed
    fun updateFeedDisplay(position: Int) {

        Log.i(TAG, "Entered updateFeedDisplay()")

        mTextView = view!!.findViewById<View>(R.id.feed_view) as TextView
        mTextView!!.text = feedFragmentData!!.getFeed(position)

    }

    companion object {
        private const val TAG = "Lab-Notifications"
    }

}