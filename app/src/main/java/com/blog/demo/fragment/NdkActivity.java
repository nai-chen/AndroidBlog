package com.blog.demo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.blog.demo.MessageInfoAdapter;
import com.blog.demo.jni.NativeCalc;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/3/27.
 */

public class NdkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);

        ListView lvCalc = (ListView) findViewById(R.id.id_listview);
        Calc calc = new Calc();
        List<MessageInfoAdapter.MessageInfo> infoList = new ArrayList<MessageInfoAdapter.MessageInfo>();
        infoList.add(new MessageInfoAdapter.MessageInfo("add = ", calc.add(8.0, 2.0)+ ""));
        infoList.add(new MessageInfoAdapter.MessageInfo("substract = ", calc.subtract(8.0, 2.0)+ ""));
        infoList.add(new MessageInfoAdapter.MessageInfo("multiply = ", calc.multiply(8.0, 2.0)+ ""));
        infoList.add(new MessageInfoAdapter.MessageInfo("divide = ", calc.divide(8.0, 2.0)+ ""));
        lvCalc.setAdapter(new MessageInfoAdapter(this, infoList));
    }

    private static class Calc {
        static {
            System.loadLibrary("calcLibs");
        }

        private NativeCalc mCalc;
        public Calc() {
            mCalc = new NativeCalc();
        }

        public double add(double v1, double v2) {
            return mCalc.add(v1, v2);
        }

        public double subtract(double v1, double v2) {
            return mCalc.subtract(v1, v2);
        }

        public double multiply(double v1, double v2) {
            return mCalc.multiply(v1, v2);
        }

        public double divide(double v1, double v2) {
            return  mCalc.divide(v1, v2);
        }

    }
}
