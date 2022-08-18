package com.blog.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class MainActivity extends Activity {

    private static final String ACTION = "com.blog.demo.content";
    private final static String EXTRA_DATA = "data";

    @Override
    @SuppressLint("WrongConstant")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Layer layer = getIntent().getParcelableExtra(EXTRA_DATA);
        if (layer == null) {
            layer = new Layer("Demo");

            Intent intent = new Intent(ACTION);
            List<ResolveInfo> infoList = getPackageManager().queryIntentActivities(intent,
                    PackageManager.GET_INTENT_FILTERS);

            if (infoList != null) {
                for (ResolveInfo info : infoList) {
                    layer.addItem(info.loadLabel(getPackageManager()).toString(), info.activityInfo.name);
                }
            }
            findViewById(R.id.iv_back).setVisibility(View.GONE);
        } else {
            findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(layer.getName());


        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new MainAdapter(layer));

        final Layer content = layer;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(content.getValue(position));
            }
        });
    }

    private void startActivity(Layer layer) {
        if (layer.isEmpty()) {
            try {
                Class<?> c = Class.forName(layer.getClassName());
                startActivity(new Intent(this, c));
            } catch (ClassNotFoundException e) {
                LogTool.loge("MainActivity", e);
            }
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_DATA, layer);
            startActivity(intent);
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
                convertView = getLayoutInflater().inflate(R.layout.list_item_main, parent, false);
            }
            TextView tv = convertView.findViewById(R.id.tv_item_title);
            tv.setText(mLayer.getValue(position).getName());

            return convertView;
        }
    }

}
