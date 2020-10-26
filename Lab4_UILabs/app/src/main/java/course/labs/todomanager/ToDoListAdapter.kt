package course.labs.todomanager

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*


class ToDoListAdapter(private val mContext: Context) : BaseAdapter() {

    private val mItems = ArrayList<ToDoItem>()

    // Add a ToDoItem to the adapter
    // Notify observers that the data set has changed

    fun add(item: ToDoItem) {

        mItems.add(item)
        notifyDataSetChanged()

    }

    // Clears the list adapter of all items.

    fun clear() {

        mItems.clear()
        notifyDataSetChanged()

    }

    // Returns the number of ToDoItems

    override fun getCount(): Int {

        return mItems.size

    }

    // Retrieve the number of ToDoItems

    override fun getItem(pos: Int): Any {

        return mItems[pos]

    }

    // Get the ID for the ToDoItem
    // In this case it's just the position

    override fun getItemId(pos: Int): Long {

        return pos.toLong()

    }

    // TODO - Create a View for the ToDoItem at specified position
    // Remember to check whether convertView holds an already allocated View
    // before created a new View.
    // Consider using the ViewHolder pattern to make scrolling more efficient
    // See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        // TODO - Get the current ToDoItem
        val curr = getItem(position) as ToDoItem
        val inflater : View


        val viewHolder: ViewHolder

        if (null == convertView) {

            viewHolder = ViewHolder()
            Log.i(TAG, "if statement of getView in ToDoListAdapter")
            // TODO - Inflate the View for this ToDoItem

            inflater = LayoutInflater.from(mContext).inflate(R.layout.todo_item, parent, false)
            viewHolder.mItemLayout = inflater.findViewById(R.id.RelativeLayout1)
            viewHolder.mTitleView = inflater.findViewById(R.id.titleView)
            viewHolder.mStatusView = inflater.findViewById(R.id.statusCheckBox)
            viewHolder.mPriorityView = inflater.findViewById(R.id.priorityView)
            viewHolder.mDateView = inflater.findViewById(R.id.dateView)
            viewHolder.position = position
            viewHolder.mItemLayout!!.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            viewHolder.mStatusView!!.setOnCheckedChangeListener(null)
        }

        // TODO - Fill in specific ToDoItem data
        // Remember that the data that goes in this View
        // corresponds to the user interface elements defined
        // in the layout file
        Log.i(TAG, "outside if statement in getView in ToDoListAdapter")
        viewHolder.mTitleView?.text = curr.title
        viewHolder.mStatusView!!.isChecked = curr.status != ToDoItem.Status.NOTDONE
        viewHolder.mPriorityView?.text = curr.priority.toString()
        viewHolder.mDateView?.setText(ToDoItem.FORMAT.format(curr.date))
        notifyDataSetChanged()
        return viewHolder.mItemLayout

    }

    internal class ViewHolder {
        var position: Int = 0
        var mItemLayout: RelativeLayout? = null
        var mTitleView: TextView? = null
        var mStatusView: CheckBox? = null
        var mPriorityView: TextView? = null
        var mDateView: TextView? = null
    }

    companion object {

        private val TAG = "Lab-UserInterface"
    }
}
