package com.blog.demo.control.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.blog.demo.People
import com.blog.demo.R

class CustomAdapter(val context: Context, val data: List<People>) : BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
            viewHolder = ViewHolder()
            viewHolder.tvName = convertView.findViewById(R.id.tv_name)
            viewHolder.tvAddress = convertView.findViewById(R.id.tv_address)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        val people = data[position]
        viewHolder.tvName?.text = people.name
        viewHolder.tvAddress?.text = people.addr
        return convertView
    }

    private class ViewHolder {
        var tvName: TextView? = null
        var tvAddress: TextView? = null
    }

}