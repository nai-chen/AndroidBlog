package com.blog.demo.media;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.View;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by cn on 2017/3/30.
 */

public class MediaCodecAudioActivity extends Activity implements View.OnClickListener {

    private boolean mStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_codec_audio);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
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
        try {
            extractor.setDataSource("/sdcard/demo.mp3");
        } catch (IOException e) {
            return;
        }
        MediaFormat format = null;
        String mime = null;
        int sampleRate = 0;

        for (int index = 0; index < extractor.getTrackCount(); index++) {
            MediaFormat mf = extractor.getTrackFormat(index);
            mime = mf.getString(MediaFormat.KEY_MIME);
            sampleRate = format.getInteger(MediaFormat.KEY_SAMPLE_RATE);

            if (mime.startsWith("audio/")) {
                format = mf;
                // 选择音频
                extractor.selectTrack(index);
                break;
            }
        }
        if (format == null) { return; }

        // AudioTrack播放音频
        int bufferSize = AudioTrack.getMinBufferSize(sampleRate,
                AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT);
        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate,
                AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                bufferSize, AudioTrack.MODE_STREAM);
        audioTrack.play();

        try {
            MediaCodec mediaCodec = MediaCodec.createDecoderByType(mime);
            mediaCodec.configure(format, null, null, 0);
            mediaCodec.start();

            ByteBuffer[] inputBufferArray = mediaCodec.getInputBuffers();
            ByteBuffer[] outputBufferArray = mediaCodec.getOutputBuffers();

            boolean outputEos = false;
            boolean inputEos = false;
            long timeoutUs = 10;

            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
            while(!outputEos && !mStop) {
                try {
                    if (!inputEos) {
                        int inputBufferIndex = mediaCodec.dequeueInputBuffer(timeoutUs);
                        LogUtil.log("MediaCodecAudioActivity", "inputBufferIndex = " + inputBufferIndex);
                        if (inputBufferIndex >= 0) {
                            ByteBuffer buffer = inputBufferArray[inputBufferIndex];
                            int sampleSize = extractor.readSampleData(buffer, 0);
                            LogUtil.log("MediaCodecAudioActivity", "sampleSize = " + sampleSize);

                            long presentationTime = 0;
                            if (sampleSize < 0) {
                                inputEos = true;
                                sampleSize = 0;
                            } else {
                                presentationTime = extractor.getSampleTime();
                            }
                            LogUtil.log("MediaCodecAudioActivity", "presentationTime = " + presentationTime);
                            mediaCodec.queueInputBuffer(inputBufferIndex, 0, sampleSize, presentationTime,
                                    inputEos ? MediaCodec.BUFFER_FLAG_END_OF_STREAM : 0);

                            if (!inputEos) {
                                extractor.advance();
                            }
                        }

                        int outputBufferIndex = mediaCodec.dequeueOutputBuffer(info, timeoutUs);
                        LogUtil.log("MediaCodecAudioActivity", "outputBufferIndex = " + outputBufferIndex);
                        LogUtil.log("MediaCodecAudioActivity", "info.presentationTimeUs = " + info.presentationTimeUs);
                        if (outputBufferIndex >= 0) {
                            ByteBuffer buffer = outputBufferArray[outputBufferIndex];
                            final byte[] buf = new byte[info.size];
                            buffer.get(buf);
                            buffer.clear();

                            if (buf.length > 0) {
                                audioTrack.write(buf, 0, buf.length);
                            }

                            mediaCodec.releaseOutputBuffer(outputBufferIndex, false);
                            if ((info.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                                LogUtil.log("MediaCodecActivity", "output EOS.");
                                outputEos = true;
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }

            if (mediaCodec != null) {
                mediaCodec.stop();
                mediaCodec.release();
            }
        } catch (IOException e) {

        } finally {
            extractor.release();

            audioTrack.stop();
            audioTrack.release();
        }

    }

}
