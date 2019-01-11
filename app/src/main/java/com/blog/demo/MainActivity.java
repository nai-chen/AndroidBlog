package com.blog.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends Activity {

    private static final String ACTION = "com.blog.demo.content";
    private final static String EXTRA_DATA = "data";

    @Override
    @SuppressLint("WrongConstant")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Layer layer = (Layer) getIntent().getSerializableExtra(EXTRA_DATA);
        if (layer == null) {
            Intent intent = new Intent(ACTION);
            List<ResolveInfo> infoList = getPackageManager().queryIntentActivities(intent,
                    PackageManager.GET_INTENT_FILTERS);

            if (infoList != null) {
                for (ResolveInfo info : infoList) {
                    layer.addItem(info.loadLabel(getPackageManager()), info.activityInfo.name);
                }
            }
        }

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new MainAdapter(layer));
    }

    private class Layer implements Serializable {
        public void addItem(CharSequence path, String name) {

        }

        public String getValue(int position) {
            return null;
        }

        public int getCount() {
            return 0;
        }
    }

    private class MainAdapter extends BaseAdapter {
        Layer mLayer;

        MainAdapter(Layer layer) {
            this.mLayer = layer;
        }

        @Override
        public int getCount() {
            return mLayer == null ? 0 : mLayer.getCount();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_main, parent);
            }
            TextView tv = convertView.findViewById(R.id.tv_item_title);
            tv.setText(mLayer.getValue(position));

            return convertView;
        }
    }

}
