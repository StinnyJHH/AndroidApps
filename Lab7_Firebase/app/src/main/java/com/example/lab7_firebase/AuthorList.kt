package com.example.lab7_firebase

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class AuthorList(private val context: Activity, private var authors: List<Author>) : ArrayAdapter<Author>(context,
    R.layout.layout_author_list, authors) {

    @SuppressLint("InflateParams", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_author_list, null, true)

        val textViewName = listViewItem.findViewById<View>(R.id.textViewName) as TextView
        val textViewCountry = listViewItem.findViewById<View>(R.id.textViewCountry) as TextView

        val author = authors[position]
        textViewName.text = author.authorName
        textViewCountry.text = author.authorCountry

        return listViewItem
    }
}