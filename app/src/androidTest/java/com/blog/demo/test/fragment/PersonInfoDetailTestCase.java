package com.blog.demo.test.fragment;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.blog.demo.R;
import com.blog.demo.fragment.PersonalInfoDetailActivity;

/**
 * Created by cn on 2017/3/21.
 */

public class PersonInfoDetailTestCase extends ActivityInstrumentationTestCase2<PersonalInfoDetailActivity> {
    private PersonalInfoDetailActivity mActivity;

    public final static String GENDER    = "男";
    public final static String FACORITE  = "暂无爱好";

    public PersonInfoDetailTestCase() {
        super(PersonalInfoDetailActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(false);
        Intent intent = new Intent();
        Context cxt = getInstrumentation().getContext();
        intent.setClass(cxt, PersonalInfoDetailActivity.class);
        intent.putExtra("name", "Jack");
        intent.putExtra("gender", GENDER);
        intent.putExtra("favorite", FACORITE);
        setActivityIntent(intent);

        mActivity = getActivity();
    }

    public void testIntent() {
        TextView tvName = (TextView) mActivity.findViewById(R.id.tv_name);
        assertEquals("Jack", tvName.getText().toString());

        TextView tvGender = (TextView) mActivity.findViewById(R.id.tv_gender);
        assertEquals(GENDER, tvGender.getText().toString());

        TextView tvFavorite = (TextView) mActivity.findViewById(R.id.tv_fav);
        assertEquals(FACORITE, tvFavorite.getText().toString());
    }

}
