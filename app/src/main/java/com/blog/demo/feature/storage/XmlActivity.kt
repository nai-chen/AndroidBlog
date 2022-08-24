package com.blog.demo.feature.storage

import android.app.Activity
import android.os.Bundle
import android.util.Xml
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.blog.demo.People
import com.blog.demo.R
import org.w3c.dom.Element
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import org.xmlpull.v1.XmlSerializer
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.SAXParserFactory

class XmlActivity : Activity(), View.OnClickListener {

    companion object {
        private val CHARSET         = "utf-8"
        private val TAG_ROOT        = "peoples"
        private val TAG_PEOPLE      = "people"
        private val TAG_ID          = "id"
        private val TAG_NAME        = "name"
        private val TAG_ADDR        = "addr"
        private val TAG_AGE         = "age"
    }

    private lateinit var mListView: ListView
    private lateinit var mAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_storage_xml)
        findViewById<Button>(R.id.btn_write_xml).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read_xml_dom).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read_xml_sax).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read_xml_pull).setOnClickListener(this)

        mListView = findViewById(R.id.list_view)
        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        mListView.adapter = mAdapter
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_write_xml) {
            val peopleList: MutableList<People> = ArrayList()
            peopleList.add(People(1, "Mike", "ShangHai", 20))
            peopleList.add(People(2, "Tom", "BeiJing", 23))
            peopleList.add(People(3, "Lily", "ShenZhen", 21))
            peopleList.add(People(4, "Jack", "GuangZhou", 22))
            try {
                writeXml(openFileOutput("people.xml", MODE_PRIVATE), peopleList)
            } catch (e: IOException) {
            }
        } else if (v.id == R.id.btn_read_xml_dom) {
            mAdapter.clear()
            try {
                val peopleList = readXmlByDom(openFileInput("people.xml"))
                for (people in peopleList) {
                    mAdapter.add(people.description())
                }
            } catch (e: Exception) {
            }
            mAdapter.notifyDataSetChanged()
        } else if (v.id == R.id.btn_read_xml_sax) {
            mAdapter.clear()
            try {
                val peopleList = readXmlBySax(openFileInput("people.xml"))
                for (people in peopleList) {
                    mAdapter.add(people!!.description())
                }
            } catch (e: Exception) {
            }
            mAdapter.notifyDataSetChanged()
        } else if (v.id == R.id.btn_read_xml_pull) {
            mAdapter.clear()
            try {
                val peopleList = readXmlByPull(openFileInput("people.xml"))
                for (people in peopleList) {
                    mAdapter.add(people.description())
                }
            } catch (e: Exception) {
            }
            mAdapter.notifyDataSetChanged()
        }
    }

    @Throws(IOException::class)
    private fun writeXml(output: OutputStream, peopleList: List<People>) {
        val xmlSerializer = Xml.newSerializer()
        xmlSerializer.setOutput(output, CHARSET)
        xmlSerializer.startDocument(CHARSET, true)
        xmlSerializer.startTag(null, TAG_ROOT)
        for (people in peopleList) {
            xmlSerializer.startTag(null, TAG_PEOPLE)
            xmlSerializer.attribute(null, TAG_ID, people.id.toString())
            writeTextTag(xmlSerializer, TAG_NAME, people.name)
            writeTextTag(xmlSerializer, TAG_ADDR, people.addr)
            writeTextTag(xmlSerializer, TAG_AGE, people.age.toString())
            xmlSerializer.endTag(null, TAG_PEOPLE)
        }
        xmlSerializer.endTag(null, TAG_ROOT)
        xmlSerializer.endDocument()
    }

    @Throws(IOException::class)
    private fun writeTextTag(xmlSerializer: XmlSerializer, tag: String, text: String?) {
        xmlSerializer.startTag(null, tag)
        xmlSerializer.text(text)
        xmlSerializer.endTag(null, tag)
    }

    @Throws(Exception::class)
    private fun readXmlByDom(input: InputStream): List<People> {
        val peopleList: MutableList<People> = ArrayList()
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val document = builder.parse(input)
        val root = document.documentElement
        val nodeList = root.getElementsByTagName(TAG_PEOPLE)
        for (index in 0 until nodeList.length) {
            val peopleElement = nodeList.item(index) as Element
            val id = peopleElement.getAttribute(TAG_ID)
            val people = People(id.toInt(),
                readText(peopleElement, TAG_NAME),
                readText(peopleElement, TAG_ADDR),
                readText(peopleElement, TAG_AGE).toInt())
            peopleList.add(people)
        }
        return peopleList
    }

    private fun readText(parent: Element, tag: String): String {
        return parent.getElementsByTagName(tag).item(0).textContent
    }

    @Throws(Exception::class)
    private fun readXmlBySax(input: InputStream): List<People?> {
        val factory = SAXParserFactory.newInstance()
        val handler = PeopleHandler()
        val parser = factory.newSAXParser()
        val xmlReader = parser.xmlReader
        xmlReader.contentHandler = handler
        xmlReader.parse(InputSource(input))
        return handler.getPeopleList()
    }

    private class PeopleHandler : DefaultHandler() {
        private val peopleList: MutableList<People> = ArrayList()
        private var people: People? = null
        private var text: String? = null

        @Throws(SAXException::class)
        override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
            if (TAG_PEOPLE == localName) {
                people = People()
                people?.id = attributes.getValue(TAG_ID).toInt()
            }
        }

        @Throws(SAXException::class)
        override fun endElement(uri: String, localName: String, qName: String) {
            if (TAG_PEOPLE == localName) {
                if (people != null) {
                    peopleList.add(people!!)
                }
            } else if (TAG_NAME == localName) {
                people?.name = text
            } else if (TAG_ADDR == localName) {
                people?.addr = text
            } else if (TAG_AGE == localName) {
                people?.age = text?.toInt() ?: 0
            }
        }

        @Throws(SAXException::class)
        override fun characters(ch: CharArray, start: Int, length: Int) {
            super.characters(ch, start, length)
            text = String(ch, start, length)
        }

        fun getPeopleList(): List<People> {
            return peopleList
        }
    }

    @Throws(Exception::class)
    private fun readXmlByPull(input: InputStream): List<People> {
        val peopleList: MutableList<People> = ArrayList()
        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(InputStreamReader(input))

        var eventType = parser.eventType
        var people: People? = null
        var text: String? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if (TAG_PEOPLE == parser.name) {
                    people = People()
                    people.id = parser.getAttributeValue(null, TAG_ID).toInt()
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if (TAG_PEOPLE == parser.name) {
                    if (people != null) {
                        peopleList.add(people!!)
                    }
                } else if (TAG_NAME == parser.name) {
                    people?.name = text
                } else if (TAG_ADDR == parser.name) {
                    people?.addr = text
                } else if (TAG_AGE == parser.name) {
                    people?.age = text?.toInt() ?: 0
                }
            } else if (eventType == XmlPullParser.TEXT) {
                text = parser.text
            }
            eventType = parser.next()
        }
        return peopleList
    }
}