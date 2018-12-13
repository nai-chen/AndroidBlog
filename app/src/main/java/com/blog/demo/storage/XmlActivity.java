package com.blog.demo.storage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;

import com.blog.demo.LogUtil;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by cn on 2017/6/21.
 */

public class XmlActivity extends Activity implements View.OnClickListener {
    private final static String XML_FILE =
            Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + "demo.xml";

    private final static String TAG_ROOT = "students";
    private final static String TAG_STUD = "student";
    private final static String TAG_ID = "id";
    private final static String TAG_NAME = "name";
    private final static String TAG_AGE = "age";

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_xml);
        findViewById(R.id.btn_read_xml_dom).setOnClickListener(this);
        findViewById(R.id.btn_read_xml_sax).setOnClickListener(this);
        findViewById(R.id.btn_read_xml_pull).setOnClickListener(this);
        findViewById(R.id.btn_write_xml).setOnClickListener(this);

        mTv = (TextView) findViewById(R.id.tv_xml);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_read_xml_dom:
                setText(readXmlByDom(XML_FILE));
                break;
            case R.id.btn_read_xml_sax:
                setText(readXmlBySax(XML_FILE));
                break;
            case R.id.btn_read_xml_pull:
                setText(readXmlByPull(XML_FILE));
                break;
            case R.id.btn_write_xml:
                boolean result = writeXml(XML_FILE, initStudentList());
                if (result) {
                    readXml(XML_FILE);
                }
                break;
        }
    }

    private void setText(List<Student> studentList) {
        StringBuffer buffer = new StringBuffer();
        for (Student stud : studentList) {
            buffer.append(TAG_STUD + " " + TAG_ID + "=" + stud.id + ", "
                + TAG_NAME + "=" + stud.name + ", "
                    + TAG_AGE + "=" + stud.age + "\n\n");
        }
        mTv.setText(buffer.toString());
    }

    private List<Student> readXmlByDom(String filePath) {
        List<Student> studentList = new ArrayList<Student>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            Element root = document.getDocumentElement();

            NodeList nodeList = root.getElementsByTagName(TAG_STUD);

            LogUtil.log("XmlActivity", nodeList.getLength() + "");
            for (int index = 0; index < nodeList.getLength(); index++) {
                Element studElement = (Element) nodeList.item(index);
                String id = studElement.getAttribute(TAG_ID);

                Student student = new Student();
                student.id = id;

                Element nameElement = (Element) studElement.getElementsByTagName(TAG_NAME).item(0);
                String name = nameElement.getTextContent();
                student.name = name;

                Element ageElement = (Element) studElement.getElementsByTagName(TAG_AGE).item(0);
                String age = ageElement.getTextContent();
                student.age = Integer.parseInt(age);

                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    private List<Student> readXmlBySax(String filePath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        StudentHandler handler = new StudentHandler();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();
            xmlReader.setContentHandler(handler);

            xmlReader.parse(new InputSource(new FileInputStream(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler.getStudentList();
    }

    private List<Student> readXmlByPull(String filePath) {
        List<Student> studentList = new ArrayList<Student>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new BufferedReader(new InputStreamReader(new FileInputStream(filePath))));

            int eventType = parser.getEventType();
            Student student = null;
            String text = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (TAG_STUD.equals(parser.getName())) {
                        student = new Student();
                        student.id = parser.getAttributeValue(null, TAG_ID);
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (TAG_STUD.equals(parser.getName())) {
                        studentList.add(student);
                    } else if (TAG_NAME.equals(parser.getName())) {
                        student.name = text;
                    } else if (TAG_AGE.equals(parser.getName())) {
                        student.age = Integer.parseInt(text);
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    text = parser.getText();
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }

    private List<Student> initStudentList() {
        List<Student> studentList = new ArrayList<Student>();

        Student student = new Student();
        student.id = "1";
        student.name = "Mike";
        student.age = 20;
        studentList.add(student);

        student = new Student();
        student.id = "2";
        student.name = "Tom";
        student.age = 23;
        studentList.add(student);

        student = new Student();
        student.id = "3";
        student.name = "Lily";
        student.age = 21;
        studentList.add(student);

        student = new Student();
        student.id = "4";
        student.name = "Jack";
        student.age = 22;
        studentList.add(student);

        return studentList;
    }

    private boolean writeXml(String filePath, List<Student> studentList) {
        XmlSerializer xmlSerializer = Xml.newSerializer();
        OutputStream output = null;
        try {
            output = new FileOutputStream(filePath);
            xmlSerializer.setOutput(output, "utf-8");
            xmlSerializer.startDocument("utf-8", true);

            xmlSerializer.startTag(null, TAG_ROOT);

            for (Student student : studentList) {
                xmlSerializer.startTag(null, TAG_STUD);
                xmlSerializer.attribute(null, TAG_ID, student.id);

                xmlSerializer.startTag(null, TAG_NAME);
                xmlSerializer.text(student.name);
                xmlSerializer.endTag(null, TAG_NAME);

                xmlSerializer.startTag(null, TAG_AGE);
                xmlSerializer.text(Integer.toString(student.age));
                xmlSerializer.endTag(null, TAG_AGE);

                xmlSerializer.endTag(null, TAG_STUD);
            }

            xmlSerializer.endTag(null, TAG_ROOT);
            xmlSerializer.endDocument();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private void readXml(String filePath) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));

            String line = null;
            StringBuffer sBuffer = new StringBuffer();

            while ((line = br.readLine()) != null) {
                sBuffer.append(line + "\n");
            }
            mTv.setText(sBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class StudentHandler extends DefaultHandler {
        private List<Student> mStudentList = new ArrayList<Student>();
        private Student mStudent;
        private String mText;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            LogUtil.log("XmlActivity", localName);

            if (TAG_STUD.equals(localName)) {
                mStudent = new Student();
                mStudent.id = attributes.getValue(TAG_ID);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            LogUtil.log("XmlActivity", localName);

            if (TAG_STUD.equals(localName)) {
                mStudentList.add(mStudent);
            } else if (TAG_NAME.equals(localName)) {
                mStudent.name = mText;
            } else if (TAG_AGE.equals(localName)) {
                mStudent.age = Integer.parseInt(mText);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

            mText = new String(ch, start, length);
        }

        public List<Student> getStudentList() {
            return mStudentList;
        }
    }


    private class Student {
        String id;
        String name;
        int age;
    }

}
