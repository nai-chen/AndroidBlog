package com.blog.demo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.blog.demo.LogTool.loge

class MainActivity : Activity() {

    private val ACTION = "com.blog.demo.content"
    private val EXTRA_DATA = "data"

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var layer = intent.getParcelableExtra<Layer>(EXTRA_DATA)
        if (layer == null) {
            layer = Layer("Demo")
            val intent = Intent(ACTION)
            val infoList = packageManager.queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS)
            if (infoList != null) {
                for (info in infoList) {
                    layer.addItem(info.loadLabel(packageManager).toString(), info.activityInfo.name)
                }
            }
            findViewById<View>(R.id.iv_back).visibility = View.GONE
        } else {
            findViewById<View>(R.id.iv_back).setOnClickListener {
                finish()
            }
        }
        val tvTitle: TextView = findViewById(R.id.tv_title)
        tvTitle.text = layer.name

        val listView: ListView = findViewById(R.id.list_view)
        listView.adapter = MainAdapter(layer)

        val content: Layer = layer
        listView.onItemClickListener =
            OnItemClickListener { _, _, position, _ ->
                startActivity(content.getValue(position))
            }
    }

    private fun startActivity(layer: Layer?) {
        if (layer?.isEmpty() == true) {
            try {
                val c = Class.forName(layer.getClassName())
                startActivity(Intent(this, c))
            } catch (e: ClassNotFoundException) {
                loge("MainActivity", e)
            }
        } else {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_DATA, layer)
            startActivity(intent)
        }
    }

    private inner class MainAdapter constructor(var mLayer: Layer?) :
        BaseAdapter() {
        override fun getCount(): Int {
            return if (mLayer == null) 0 else mLayer!!.getCount()
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            var convertView = view
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.list_item_main, parent, false)
            }

            var tv: TextView? = convertView?.findViewById(R.id.tv_item_title)
            tv?.text = mLayer?.getValue(position)?.name
            return convertView!!
        }
    }
}