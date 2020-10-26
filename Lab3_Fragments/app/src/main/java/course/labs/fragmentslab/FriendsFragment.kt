package course.labs.fragmentslab

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class FriendsFragment : ListFragment() {

    private lateinit var mCallback: SelectionListener

    // If there is a FeedFragment, then the layout is two-pane
    private val isInTwoPaneMode: Boolean
        get() = activity.supportFragmentManager.findFragmentById(R.id.feed_frag) != null

    interface SelectionListener {
        fun onItemSelected(position: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the list adapter for this ListFragment
        listAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_activated_1, FRIENDS)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Make sure that the hosting Activity has implemented
        // the SelectionListener callback interface. We need this
        // because when an item in this ListFragment is selected,
        // the hosting Activity's onItemSelected() method will be called.

        try {

            mCallback = context as SelectionListener

        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement SelectionListener")
        }

    }

    // Note: ListFragments come with a default onCreateView() method.
    // For other Fragments you'll normally implement this method.
    // 	@Override
    //  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //		Bundle savedInstanceState)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.i(TAG, "Entered onActivityCreated()")

        // When using two-pane layout, configure the ListView to highlight the
        // selected list item

        if (isInTwoPaneMode) {
            listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        }

    }

    override fun onListItemClick(l: ListView, view: View, position: Int, id: Long) {

        // Notify the hosting Activity that a selection has been made.

        mCallback!!.onItemSelected(position)

    }
    companion object {

        private val FRIENDS = arrayOf("ladygaga", "msrebeccablack", "taylorswift13")
        private const val TAG = "Lab-Fragments"
    }

}
