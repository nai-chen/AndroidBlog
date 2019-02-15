package com.blog.demo.application.storage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blog.demo.People;
import com.blog.demo.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlActivity extends Activity implements View.OnClickListener {
    private static final String CHARSET = "utf-8";
    private static final String TAG_ROOT    = "peoples";
    private static final String TAG_PEOPLE  = "people";
    private static final String TAG_ID      = "id";
    private static final String TAG_NAME    = "name";
    private static final String TAG_ADDR    = "addr";
    private static final String TAG_AGE     = "age";

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_application_storage_xml);

        findViewById(R.id.btn_write_xml).setOnClickListener(this);
        findViewById(R.id.btn_read_xml_dom).setOnClickListener(this);
        findViewById(R.id.btn_read_xml_sax).setOnClickListener(this);
        findViewById(R.id.btn_read_xml_pull).setOnClickListener(this);

        mListView = findViewById(R.id.list_view);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_write_xml) {
            List<People> peopleList = new ArrayList<>();
            peopleList.add(new People(1, "Mike", "ShangHai", 20));
            peopleList.add(new People(2, "Tom", "BeiJing", 23));
            peopleList.add(new People(3, "Lily", "ShenZhen", 21));
            peopleList.add(new People(4, "Jack", "GuangZhou", 22));
            try {
                writeXml(openFileOutput("people.xml", MODE_PRIVATE), peopleList);
            } catch (IOException e) {
            }
        } else if (v.getId() == R.id.btn_read_xml_dom) {
            mAdapter.clear();
            try {
                List<People> peopleList = readXmlByDom(openFileInput("people.xml"));
                for (People people : peopleList) {
                    mAdapter.add(people.description());
                }
            } catch (Exception e) {
            }
            mAdapter.notifyDataSetChanged();
        } else if (v.getId() == R.id.btn_read_xml_sax) {
            mAdapter.clear();
            try {
                List<People> peopleList = readXmlBySax(openFileInput("people.xml"));
                for (People people : peopleList) {
                    mAdapter.add(people.description());
                }
            } catch (Exception e) {
            }
            mAdapter.notifyDataSetChanged();
        } else if (v.getId() == R.id.btn_read_xml_pull) {
            mAdapter.clear();
            try {
                List<People> peopleList = readXmlByPull(openFileInput("people.xml"));
                for (People people : peopleList) {
                    mAdapter.add(people.description());
                }
            } catch (Exception e) {
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    private void writeXml(OutputStream output, List<People> peopleList) throws IOException {
        XmlSerializer xmlSerializer = Xml.newSerializer();

        xmlSerializer.setOutput(output, CHARSET);
        xmlSerializer.startDocument(CHARSET, true);

        xmlSerializer.startTag(null, TAG_ROOT);

        for (People people : peopleList) {
            xmlSerializer.startTag(null, TAG_PEOPLE);
            xmlSerializer.attribute(null, TAG_ID, Integer.toString(people.id));

            writeTextTag(xmlSerializer, TAG_NAME, people.name);
            writeTextTag(xmlSerializer, TAG_ADDR, people.addr);
            writeTextTag(xmlSerializer, TAG_AGE, Integer.toString(people.age));

            xmlSerializer.endTag(null, TAG_PEOPLE);
        }

        xmlSerializer.endTag(null, TAG_ROOT);
        xmlSerializer.endDocument();
    }

    private void writeTextTag(XmlSerializer xmlSerializer, String tag, String text)
            throws IOException {
        xmlSerializer.startTag(null, tag);
        xmlSerializer.text(text);
        xmlSerializer.endTag(null, tag);
    }


    private List<People> readXmlByDom(InputStream input) throws Exception {
        List<People> peopleList = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(input);
        Element root = document.getDocumentElement();

        NodeList nodeList = root.getElementsByTagName(TAG_PEOPLE);

        for (int index = 0; index < nodeList.getLength(); index++) {
            Element peopleElement = (Element) nodeList.item(index);
            String id = peopleElement.getAttribute(TAG_ID);

            People people = new People();
            people.id = Integer.parseInt(id);

            people.name = readText(peopleElement, TAG_NAME);
            people.addr = readText(peopleElement, TAG_ADDR);
            people.age = Integer.parseInt(readText(peopleElement, TAG_AGE));

            peopleList.add(people);
        }
        return peopleList;
    }

    private String readText(Element parent, String tag) {
        return parent.getElementsByTagName(tag).item(0).getTextContent();
    }

    private List<People> readXmlBySax(InputStream input) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        PeopleHandler handler = new PeopleHandler();

        SAXParser parser = factory.newSAXParser();
        XMLReader xmlReader = parser.getXMLReader();
        xmlReader.setContentHandler(handler);

        xmlReader.parse(new InputSource(input));

        return handler.getPeopleList();
    }

    private static class PeopleHandler extends DefaultHandler {
        private List<People> peopleList = new ArrayList<>();
        private People people;
        private String text;

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            if (TAG_PEOPLE.equals(localName)) {
                people = new People();
                people.id = Integer.parseInt(attributes.getValue(TAG_ID));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (TAG_PEOPLE.equals(localName)) {
                peopleList.add(people);
            } else if (TAG_NAME.equals(localName)) {
                people.name = text;
            } else if (TAG_ADDR.equals(localName)) {
                people.addr = text;
            } else if (TAG_AGE.equals(localName)) {
                people.age = Integer.parseInt(text);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

            text = new String(ch, start, length);
        }

        public List<People> getPeopleList() {
            return peopleList;
        }
    }

    private List<People> readXmlByPull(InputStream input) throws Exception {
        List<People> peopleList = new ArrayList<>();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new InputStreamReader(input));

        int eventType = parser.getEventType();
        People people = null;
        String text = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if (TAG_PEOPLE.equals(parser.getName())) {
                    people = new People();
                    people.id = Integer.parseInt(parser.getAttributeValue(null, TAG_ID));
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if (TAG_PEOPLE.equals(parser.getName())) {
                    peopleList.add(people);
                } else if (TAG_NAME.equals(parser.getName())) {
                    people.name = text;
                } else if (TAG_ADDR.equals(parser.getName())) {
                    people.addr = text;
                } else if (TAG_AGE.equals(parser.getName())) {
                    people.age = Integer.parseInt(text);
                }
            } else if (eventType == XmlPullParser.TEXT) {
                text = parser.getText();
            }
            eventType = parser.next();
        }
        return peopleList;
    }
}
