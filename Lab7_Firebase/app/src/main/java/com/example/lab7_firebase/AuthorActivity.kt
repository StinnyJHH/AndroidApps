package com.example.lab7_firebase

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.lang.Exception
import java.util.*

class AuthorActivity : AppCompatActivity() {
    private lateinit var buttonAddTitle: Button
    private lateinit var editTextTitleName: EditText
    private lateinit var seekBarRating: SeekBar
    internal lateinit var textViewRating: TextView
    private lateinit var textViewAuthor: TextView
    internal lateinit var listViewTitles: ListView

    private lateinit var databaseTitles: DatabaseReference

    internal lateinit var titles: MutableList<Title>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)

        val intent = intent

        /*
         * this line is important
         * this time we are not getting the reference of a direct node
         * but inside the node track we are creating a new child with the author id
         * and inside that node we will store all the titles with unique ids
         * */
        databaseTitles = FirebaseDatabase.getInstance().getReference("titles").child(intent.getStringExtra(
            AUTHOR_ID
        )!!)

        buttonAddTitle = findViewById<View>(R.id.buttonAddTitle) as Button
        editTextTitleName = findViewById<View>(R.id.editTextName) as EditText
        seekBarRating = findViewById<View>(R.id.seekBarRating) as SeekBar
        textViewRating = findViewById<View>(R.id.textViewRating) as TextView
        textViewAuthor = findViewById<View>(R.id.textViewAuthor) as TextView
        listViewTitles = findViewById<View>(R.id.listViewTitles) as ListView

        titles = ArrayList()

        textViewAuthor.text = intent.getStringExtra(AUTHOR_NAME)

        seekBarRating.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                textViewRating.text = i.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // do nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // do nothing
            }
        })

        buttonAddTitle.setOnClickListener { saveTitle() }
    }

    override fun onStart() {
        super.onStart()

        databaseTitles.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                titles.clear()

                var title: Title? = null
                for (postSnapshot in dataSnapshot.children) {
                    try {
                        title = postSnapshot.getValue(Title::class.java)
                    } catch (e: Exception) {
                        Log.e(TAG, e.toString())
                    } finally {
                        titles.add(title!!)
                    }
                }
                val titleListAdapter = TitleList(this@AuthorActivity, titles)
                listViewTitles.adapter = titleListAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // do nothing
            }
        })
    }

    private fun saveTitle() {
        val titleName = editTextTitleName.text.toString().trim { it <= ' ' }
        val rating = seekBarRating.progress
        if (!TextUtils.isEmpty(titleName)) {
            val id = (databaseTitles.push()).key.toString()
            val title = Title(id, titleName, rating)
            databaseTitles.child(id).setValue(title)
            Toast.makeText(this, "Title saved", Toast.LENGTH_LONG).show()
            editTextTitleName.setText("")
        } else {
            Toast.makeText(this, "Please enter title name", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val TAG = "Lab-Firebase"
        const val AUTHOR_NAME = "com.example.tesla.myhomelibrary.authorname"
        const val AUTHOR_ID = "com.example.tesla.myhomelibrary.authorid"
    }
}
