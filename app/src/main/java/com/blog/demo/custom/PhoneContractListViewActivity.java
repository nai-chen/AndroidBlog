package com.blog.demo.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blog.demo.R;
import com.blog.demo.custom.control.PhoneContractListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/2/4.
 */

public class PhoneContractListViewActivity extends Activity {

    private TextView mTvTop;
    private List<PhoneContract> mPhoneContracts = new ArrayList<PhoneContract>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_contract_listview);

        PhoneContract phoneContract = new PhoneContract("L");
        phoneContract.addName("李一");
        phoneContract.addName("李二");
        phoneContract.addName("李三");
        phoneContract.addName("李四");
        phoneContract.addName("李五");
        phoneContract.addName("李六");
        phoneContract.addName("李七");
        phoneContract.addName("李八");
        mPhoneContracts.add(phoneContract);

        phoneContract = new PhoneContract("S");
        phoneContract.addName("孙一");
        phoneContract.addName("孙二");
        phoneContract.addName("孙三");
        phoneContract.addName("孙四");
        phoneContract.addName("孙五");
        phoneContract.addName("孙六");
        phoneContract.addName("孙七");
        phoneContract.addName("孙八");
        mPhoneContracts.add(phoneContract);

        phoneContract = new PhoneContract("W");
        phoneContract.addName("王一");
        phoneContract.addName("王二");
        phoneContract.addName("王三");
        phoneContract.addName("王四");
        phoneContract.addName("王五");
        phoneContract.addName("王六");
        phoneContract.addName("王七");
        mPhoneContracts.add(phoneContract);

        phoneContract = new PhoneContract("Z");
        phoneContract.addName("张一");
        phoneContract.addName("张二");
        phoneContract.addName("张三");
        phoneContract.addName("张四");
        phoneContract.addName("张五");
        phoneContract.addName("张六");
        phoneContract.addName("张七");
        phoneContract.addName("张八");
        mPhoneContracts.add(phoneContract);

        PhoneContractListView lv = (PhoneContractListView) findViewById(R.id.listview_phone_contract);
        lv.setAdapter(new NameAdapter());
    }

    private static class PhoneContract {
        public String letter;
        public List<String> mNames;

        public PhoneContract(String letter) {
            this.letter = letter;
            mNames = new ArrayList<String>();
        }

        public void addName(String name) {
            this.mNames.add(name);
        }

        public List<String> getNames() {
            return mNames;
        }
    }

    private class NameAdapter extends PhoneContractListView.PhoneContractAdapter {

        public NameAdapter() {
            super(PhoneContractListViewActivity.this);
        }

        @Override
        public int getGroupCount() {
            return mPhoneContracts.size();
        }

        @Override
        public int getGroupItemCount(int gPosition) {
            return mPhoneContracts.get(gPosition).getNames().size();
        }

        @Override
        public View getGroupTitleView(int gPosition, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listview_head_phone_contract, parent, false);
            }

            ((TextView) convertView).setText(mPhoneContracts.get(gPosition).letter);

            return convertView;
        }

        @Override
        public View getGroupContentView(int gPosition, int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listview_item_phone_contract, parent, false);
            }
            ((TextView) convertView).setText(mPhoneContracts.get(gPosition).getNames().get(position));
            return convertView;
        }

    }

}
