package com.blog.demo.media;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blog.demo.LogUtil;
import com.blog.demo.R;
import com.blog.demo.Util;

/**
 * Created by cn on 2017/3/30.
 */

public class MediaMetadataRetrieverActivity extends Activity {
    private ImageView mIvFrame;
    private TextView mTvMusic;
    private Bitmap mBmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_metadata_retriever);
        mIvFrame = (ImageView) findViewById(R.id.iv_frame);
        mTvMusic = (TextView) findViewById(R.id.tv_music);

        getVideoInfo();
        getMusicInfo();
    }

    private void getVideoInfo() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float width = dm.widthPixels;
        float height = dm.density * 200;

        String destFilePath = Util.copyFromAsset(this, "video.3gp");
        if (destFilePath != null) {
            try {
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                retriever.setDataSource(destFilePath);
                mBmp = retriever.getFrameAtTime();
                LogUtil.log("MediaMetadataRetriever", "bmp width = " + mBmp.getWidth());
                LogUtil.log("MediaMetadataRetriever", "bmp height = " + mBmp.getHeight());
                LogUtil.log("MediaMetadataRetriever", "video width = " +
                        retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
                LogUtil.log("MediaMetadataRetriever", "video height = " +
                        retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));

                if (width / mBmp.getWidth() > height / mBmp.getHeight()) {
                    width = (int) (height / mBmp.getHeight() * mBmp.getWidth());
                } else {
                    height = (int) (width / mBmp.getWidth() * mBmp.getHeight());
                }
                mIvFrame.setLayoutParams(new RelativeLayout.LayoutParams((int)width, (int)height));
                mIvFrame.setImageBitmap(mBmp);
//                bmp.recycle();
                retriever.release();
                LogUtil.log("MediaMetadataRetriever", "width = " + width);
                LogUtil.log("MediaMetadataRetriever", "height = " + height);
            } catch (Exception e) {
                LogUtil.log("MediaMetadataRetriever", e.getMessage());
            }
        }
    }

    private void getMusicInfo() {
        String destFilePath = Util.copyFromAsset(this, "demo.mp3");
        if (destFilePath != null) {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(destFilePath);
            String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE); // 获取音乐标题信息
            String album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM); // 获得音乐专辑的标题
            String mime = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE); // 获取音乐mime类型
            String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST); // 获取音乐的艺术家信息
            String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION); // 获取音乐持续时间
            String bitrate = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE); // 获取音乐比特率，位率
            String date = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE); // 获取音乐的日期

            mTvMusic.setText("title = " + title
                    + "\nalbum = " + album
                    + "\nmime = " + mime
                    + "\nartist = " + artist
                    + "\nduration = " + duration
                    + "\nbitrate = " + bitrate
                    + "\ndate = " + date );
            mmr.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBmp != null) {
            mBmp.recycle();
        }
    }
}
