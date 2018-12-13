package com.blog.demo.media;

import android.app.Activity;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.blog.demo.LogUtil;
import com.blog.demo.R;
import com.blog.demo.Util;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by cn on 2017/4/13.
 */

public class MediaCodecVideoActivity extends Activity implements View.OnClickListener {

    private SurfaceView mSurfaceView;
    private boolean mStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_codec_video);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);

        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                new Thread() {
                    @Override
                    public void run() {
                        startMediaCodec();
                    }
                }.start();
                break;
            case R.id.btn_stop:
                mStop = true;
                break;
        }
    }

    private void startMediaCodec() {
        mStop = false;
        MediaExtractor extractor = new MediaExtractor();
        String filePath = Util.copyFromAsset(this, "video.3gp");
        try {
            extractor.setDataSource(filePath);
        } catch (IOException e) {
            return;
        }

        MediaFormat format = null;
        String mime = null;

        for (int index = 0; index < extractor.getTrackCount(); index++) {
            MediaFormat mf = extractor.getTrackFormat(index);
            mime = mf.getString(MediaFormat.KEY_MIME);

            if (mime.startsWith("video/")) {
                LogUtil.log("MediaCodecVideoActivity", "mime = " + mime);
                format = mf;
                extractor.selectTrack(index);
                break;
            }
        }

        if (format == null) {
            LogUtil.log("MediaCodecVideoActivity", "not video");
            return;
        }

        try {
            MediaCodec mediaCodec = MediaCodec.createDecoderByType(mime);
            mediaCodec.configure(format, mSurfaceView.getHolder().getSurface(), null, 0);

            mediaCodec.start();

            ByteBuffer[] inputBufferArray = mediaCodec.getInputBuffers();

            boolean outputEos = false;
            boolean inputEos = false;
            long timeoutUs = 10;
            long startTimeMillis = 0;

            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
            while (!outputEos && !mStop) {
                try {
                    if (!inputEos) {
                        int inputBufferIndex = mediaCodec.dequeueInputBuffer(timeoutUs);
                        LogUtil.log("MediaCodecVideoActivity", "inputBufferIndex = " + inputBufferIndex);
                        if (inputBufferIndex >= 0) {
                            long presentationTime = 0;
                            ByteBuffer buffer = inputBufferArray[inputBufferIndex];
                            int sampleSize = extractor.readSampleData(buffer, 0);
                            LogUtil.log("MediaCodecVideoActivity", "sampleSize = " + sampleSize);
                            if (sampleSize < 0) {
                                inputEos = true;
                                sampleSize = 0;
                            } else {
                                presentationTime = extractor.getSampleTime();
                            }
                            LogUtil.log("MediaCodecVideoActivity", "presentationTime = " + presentationTime);
                            mediaCodec.queueInputBuffer(inputBufferIndex, 0, sampleSize, presentationTime,
                                    inputEos ? MediaCodec.BUFFER_FLAG_END_OF_STREAM : 0);

                            if (!inputEos) {
                                extractor.advance();
                            }
                        }

                        int outputBufferIndex = mediaCodec.dequeueOutputBuffer(info, timeoutUs);
                        LogUtil.log("MediaCodecVideoActivity", "outputBufferIndex = " + outputBufferIndex);
                        LogUtil.log("MediaCodecVideoActivity", "info.presentationTimeUs = " + info.presentationTimeUs);
                        if (outputBufferIndex >= 0) {
                            long currentTimeMillis = System.currentTimeMillis();
                            if (startTimeMillis == 0) {
                                startTimeMillis = currentTimeMillis;
                            } else {
                                long sleepTime = (info.presentationTimeUs / 1000) - (currentTimeMillis - startTimeMillis);
                                if (sleepTime > 0) {
                                    try {
                                        Thread.sleep(sleepTime);
                                    } catch (InterruptedException e) {}
                                }
                            }
                            mediaCodec.releaseOutputBuffer(outputBufferIndex, true);
                            if ((info.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                                LogUtil.log("MediaCodecVideoActivity", "output EOS.");
                                outputEos = true;
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }

            mediaCodec.stop();
            mediaCodec.release();
        } catch (IOException e) {
        } finally {
             extractor.release();
        }

    }

}
