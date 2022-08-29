package com.blog.demo.application

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import com.blog.demo.R
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.net.URL

class RssActivity : Activity() {

    private lateinit var mListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        mListView = findViewById(R.id.list_view)
        RssTask().execute()
    }

    private inner class RssTask : AsyncTask<String?, Int?, String?>() {

        override fun doInBackground(vararg params: String?): String? {
            try {
                val url = URL("http://www.sciencenet.cn/xml/news-0.aspx?news=0")
                val connection = url.openConnection()
                val br = BufferedReader(
                    InputStreamReader(connection.getInputStream())
                )
                var line: String? = null
                val buffer = StringBuffer()
                while (br.readLine().also { line = it } != null) {
                    buffer.append(line)
                }
                return buffer.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(s: String?) {
            if (s != null) {
                val itemList = parseRss(s)
                val data: MutableList<Map<String, String?>> = ArrayList()
                for (item in itemList) {
                    val itemMap: MutableMap<String, String?> = HashMap()
                    itemMap["title"] = item!!.title
                    itemMap["desc"] = item.description
                    data.add(itemMap)
                }
                mListView.adapter = SimpleAdapter(this@RssActivity, data, R.layout.list_item_rss,
                        arrayOf("title", "desc"), intArrayOf(R.id.tv_title, R.id.tv_description))
                mListView.setOnItemClickListener{ _, _, position, _ ->
                    Toast.makeText(this@RssActivity, itemList[position]?.link, Toast.LENGTH_LONG).show()
                }
            }
        }

        private fun parseRss(content: String): List<ChannelItem?> {
            val itemList: MutableList<ChannelItem?> = ArrayList()
            try {
                val factory = XmlPullParserFactory.newInstance()
                val parser = factory.newPullParser()
                parser.setInput(BufferedReader(StringReader(content)))
                var eventType = parser.eventType
                var item: ChannelItem? = null
                var text: String? = null

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if ("item" == parser.name) {
                            item = ChannelItem()
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if ("item" == parser.name) {
                            if (item != null) {
                                itemList.add(item)
                            }
                        } else if ("title" == parser.name) {
                            item?.title = text
                        } else if ("link" == parser.name) {
                            item?.link = text
                        } else if ("description" == parser.name) {
                            item?.description = text
                        }
                    } else if (eventType == XmlPullParser.TEXT) {
                        text = parser.text
                    }
                    eventType = parser.next()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return itemList
        }
    }

    private class ChannelItem {
        var title: String? = null
        var link: String? = null
        var description: String? = null
    }
}