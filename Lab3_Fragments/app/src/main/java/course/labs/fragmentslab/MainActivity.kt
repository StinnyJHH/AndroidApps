package course.labs.fragmentslab

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : FragmentActivity(), FriendsFragment.SelectionListener {

    private lateinit var mFriendsFragment: FriendsFragment
    private var mFeedFragment: FeedFragment? = null

    // If there is no fragment_container ID, then the application is in
    // two-pane mode

    private val isInTwoPaneMode: Boolean
        get() = findViewById<View>(R.id.fragment_container) == null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // If the layout is single-pane, create the FriendsFragment
        // and add it to the Activity

        val fragmentManager = supportFragmentManager
        val trans = fragmentManager.beginTransaction()
        mFriendsFragment = FriendsFragment()
        if (!isInTwoPaneMode) {

            //TODO 1 - add the FriendsFragment
            // Otherwise, save a reference to the FeedFragment for later use
            trans.add(R.id.fragment_container, mFriendsFragment).addToBackStack(TAG)
        }else{
            mFeedFragment = fragmentManager.findFragmentById(R.id.feed_frag) as FeedFragment?
            trans.add(R.id.friends_frag, mFriendsFragment)
        }

        trans.commit()
        fragmentManager.executePendingTransactions()

    }

    // Display selected Twitter feed

    override fun onItemSelected(position: Int) {

        Log.i(TAG, "Entered onItemSelected($position)")

        // If in single-pane mode, replace single visible Fragment
        val fragmentManager = supportFragmentManager
        val trans = fragmentManager.beginTransaction()
        if (!isInTwoPaneMode) {

            // If there is no FeedFragment instance, then create one

            if (mFeedFragment == null)
                mFeedFragment = FeedFragment()

            //TODO 2 - replace the fragment_container with the FeedFragment
            trans.replace(R.id.fragment_container, mFeedFragment).addToBackStack(TAG)
        }else{
            if(mFeedFragment == null)
                mFeedFragment = FeedFragment()
            trans.replace(R.id.feed_frag, mFeedFragment)
        }

        trans.commit()
        fragmentManager.executePendingTransactions()

        // Update Twitter feed display on FriendFragment
        mFeedFragment?.updateFeedDisplay(position)

    }

    companion object {
        private const val TAG = "Lab-Fragments"
    }

}
