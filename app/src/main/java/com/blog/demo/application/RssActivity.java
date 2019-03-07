package com.blog.demo.application;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.blog.demo.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RssActivity extends Activity {
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view);

        mListView = findViewById(R.id.list_view);
        new RssTask().execute();
    }
    private class RssTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://www.sciencenet.cn/xml/news-0.aspx?news=0");

                URLConnection connection = url.openConnection();

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = null;
                StringBuffer buffer = new StringBuffer();

                while ((line = br.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                final List<ChannelItem> itemList = parseRss(s);
                List<Map<String, String>> data = new ArrayList<>();

                for (ChannelItem item : itemList) {
                    Map<String, String> itemMap = new HashMap<>();
                    itemMap.put("title", item.title);
                    itemMap.put("desc", item.description);
                    data.add(itemMap);
                }

                mListView.setAdapter(new SimpleAdapter(RssActivity.this, data,
                        R.layout.list_item_rss,
                        new String[]{"title", "desc"},
                        new int[]{R.id.tv_title, R.id.tv_description}));
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        Toast.makeText(RssActivity.this, itemList.get(position).link,
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

        }

        private List<ChannelItem> parseRss(String content) {
            List<ChannelItem> itemList = new ArrayList<>();
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new BufferedReader(new StringReader(content)));

                int eventType = parser.getEventType();
                ChannelItem item = null;
                String text = null;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if ("item".equals(parser.getName())) {
                            item = new ChannelItem();
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if ("item".equals(parser.getName())) {
                            itemList.add(item);
                        } else if ("title".equals(parser.getName())) {
                            if (item != null)
                                item.title = text;
                        } else if ("link".equals(parser.getName())) {
                            if (item != null)
                                item.link = text;
                        } else if ("description".equals(parser.getName())) {
                            if (item != null)
                                item.description = text;
                        }
                    } else if (eventType == XmlPullParser.TEXT) {
                        text = parser.getText();
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return itemList;
        }

    }

    private class ChannelItem {
        String title;
        String link;
        String description;
    }

}
