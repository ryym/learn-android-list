package com.example.learnlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

// https://stackoverflow.com/questions/46151681/how-can-we-implement-base-adapter-with-kotlin-in-android
class MyListAdapter(context: Context, private val items: ArrayList<ListItem>, private val resource: Int) : BaseAdapter() {
    private val inflator = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflator.inflate(resource, null)
        val item = getItem(position) as ListItem

        view.findViewById<TextView>(R.id.title).text = item.title
        view.findViewById<TextView>(R.id.tag).text = item.tag
        view.findViewById<TextView>(R.id.desc).text = item.desc

        return view
    }

    override fun getItem(position: Int): Any {
        return items.get(position)
    }

    override fun getItemId(position: Int): Long {
        return items.get(position).id
    }


    override fun getCount(): Int {
        return items.size
    }

}

data class ListItem(val id: Long, val title: String, val tag: String, val desc: String = "description of $title")


