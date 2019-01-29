package com.blog.demo.image;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.blog.demo.LogTool;
import com.blog.demo.R;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MediaCodecActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "MediaCodecActivity";
    private boolean mStopAudio, mStopVideo;
    private Surface mSurface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_media_codec);
        findViewById(R.id.btn_start_audio).setOnClickListener(this);
        findViewById(R.id.btn_stop_audio).setOnClickListener(this);
        findViewById(R.id.btn_start_video).setOnClickListener(this);
        findViewById(R.id.btn_stop_video).setOnClickListener(this);

        SurfaceView surfaceView = findViewById(R.id.surface_view);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mSurface = holder.getSurface();
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
        if (v.getId() == R.id.btn_start_audio) {
            new Thread() {
                @Override
                public void run() {
                    startAudio("demo.mp3");
                }
            }.start();
        } else if (v.getId() == R.id.btn_stop_audio) {
            mStopAudio = true;
        } else if (v.getId() == R.id.btn_start_video) {
            new Thread() {
                @Override
                public void run() {
                    startVideo("video.3gp");
                }
            }.start();
        } else if (v.getId() == R.id.btn_stop_video) {
            mStopVideo = true;
        }
    }

    private void startAudio(String fileName) {
        mStopAudio = false;
        MediaExtractor extractor = new MediaExtractor();
        try {
            AssetFileDescriptor fd = getAssets().openFd(fileName);
            extractor.setDataSource(fd.getFileDescriptor(),
                    fd.getStartOffset(), fd.getLength());
        } catch (IOException e) {
            LogTool.loge(LOG_TAG, e);
            return;
        }
        MediaFormat format = null;

        for (int index = 0; index < extractor.getTrackCount(); index++) {
            MediaFormat mf = extractor.getTrackFormat(index);
            String mime = mf.getString(MediaFormat.KEY_MIME);

            if (mime.startsWith("audio/")) {
                format = mf;
                // 选择音频
                extractor.selectTrack(index);
                break;
            }
        }
        if (format != null) {
            int sampleRate = format.getInteger(MediaFormat.KEY_SAMPLE_RATE);
            // AudioTrack播放音频
            int bufferSize = AudioTrack.getMinBufferSize(sampleRate,
                    AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT);
            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate,
                    AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                    bufferSize, AudioTrack.MODE_STREAM);

            try {
                audioTrack.play();

                playAudio(extractor, format, audioTrack);
            } catch (Exception e) {
                LogTool.loge(LOG_TAG, e);
            } finally {
                extractor.release();

                audioTrack.stop();
                audioTrack.release();
            }
        }
    }

    private void playAudio(MediaExtractor extractor, MediaFormat format, AudioTrack audioTrack)
                throws IOException {
        String mime = format.getString(MediaFormat.KEY_MIME);

        MediaCodec mediaCodec = MediaCodec.createDecoderByType(mime);
        mediaCodec.configure(format, null, null, 0);
        mediaCodec.start();

        boolean outputEos = false;
        boolean inputEos = false;
        long timeoutUs = 10;

        MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
        while(!outputEos && !mStopAudio) {
            try {
                if (!inputEos) {
                    int inputBufferIndex = mediaCodec.dequeueInputBuffer(timeoutUs);
                    LogTool.logi(LOG_TAG, "inputBufferIndex = " + inputBufferIndex);
                    if (inputBufferIndex >= 0) {
                        ByteBuffer buffer = mediaCodec.getInputBuffer(inputBufferIndex);
                        int sampleSize = extractor.readSampleData(buffer, 0);
                        LogTool.logi(LOG_TAG, "sampleSize = " + sampleSize);

                        long presentationTime = 0;
                        if (sampleSize < 0) {
                            inputEos = true;
                            sampleSize = 0;
                        } else {
                            presentationTime = extractor.getSampleTime();
                        }
                        LogTool.logi(LOG_TAG, "presentationTime = " + presentationTime);
                        mediaCodec.queueInputBuffer(inputBufferIndex, 0, sampleSize, presentationTime,
                                inputEos ? MediaCodec.BUFFER_FLAG_END_OF_STREAM : 0);

                        if (!inputEos) {
                            extractor.advance();
                        }
                    }

                    int outputBufferIndex = mediaCodec.dequeueOutputBuffer(info, timeoutUs);
                    LogTool.logi(LOG_TAG, "outputBufferIndex = " + outputBufferIndex);
                    LogTool.logi(LOG_TAG, "info.presentationTimeUs = " + info.presentationTimeUs);
                    if (outputBufferIndex >= 0) {
                        ByteBuffer buffer = mediaCodec.getOutputBuffer(outputBufferIndex);
                        final byte[] buf = new byte[info.size];
                        buffer.get(buf);
                        buffer.clear();

                        if (buf.length > 0) {
                            audioTrack.write(buf, 0, buf.length);
                        }

                        mediaCodec.releaseOutputBuffer(outputBufferIndex, false);
                        if ((info.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                            LogTool.logi(LOG_TAG, "output EOS.");
                            outputEos = true;
                        }
                    }
                }
            } catch (Exception e) {
                LogTool.loge(LOG_TAG, e);
            }
        }

        if (mediaCodec != null) {
            mediaCodec.stop();
            mediaCodec.release();
        }
    }

    private void startVideo(String fileName) {
        mStopVideo = false;

        MediaExtractor extractor = new MediaExtractor();
        try {
            AssetFileDescriptor fd = getAssets().openFd(fileName);
            extractor.setDataSource(fd.getFileDescriptor(),
                    fd.getStartOffset(), fd.getLength());
        } catch (IOException e) {
            return;
        }

        MediaFormat format = null;
        for (int index = 0; index < extractor.getTrackCount(); index++) {
            MediaFormat mf = extractor.getTrackFormat(index);
            String mime = mf.getString(MediaFormat.KEY_MIME);

            // 选择视频
            if (mime.startsWith("video/")) {
                format = mf;
                extractor.selectTrack(index);
                break;
            }
        }

        if (format != null && mSurface != null) {
            try {
                playVideo(extractor, format, mSurface);
            } catch (Exception e) {
                LogTool.loge(LOG_TAG, e);
            } finally {
                extractor.release();
            }
        }
    }

    private void playVideo(MediaExtractor extractor, MediaFormat format, Surface surface)
                throws IOException {
        String mime = format.getString(MediaFormat.KEY_MIME);

        MediaCodec mediaCodec = MediaCodec.createDecoderByType(mime);
        // 绑定surface
        mediaCodec.configure(format, surface, null, 0);
        mediaCodec.start();

        ByteBuffer[] inputBufferArray = mediaCodec.getInputBuffers();

        boolean outputEos = false;
        boolean inputEos = false;
        long timeoutUs = 10;
        long startTimeMillis = 0;

        MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
        while (!outputEos && !mStopVideo) {
            try {
                if (!inputEos) {
                    int inputBufferIndex = mediaCodec.dequeueInputBuffer(timeoutUs);
                    if (inputBufferIndex >= 0) {
                        long presentationTime = 0;
                        ByteBuffer buffer = inputBufferArray[inputBufferIndex];
                        int sampleSize = extractor.readSampleData(buffer, 0);

                        if (sampleSize < 0) {
                            inputEos = true;
                            sampleSize = 0;
                        } else {
                            presentationTime = extractor.getSampleTime();
                        }

                        mediaCodec.queueInputBuffer(inputBufferIndex, 0,
                                sampleSize, presentationTime,
                                inputEos ? MediaCodec.BUFFER_FLAG_END_OF_STREAM : 0);

                        if (!inputEos) {
                            extractor.advance();
                        }
                    }

                    int outputBufferIndex = mediaCodec.dequeueOutputBuffer(info, timeoutUs);
                    if (outputBufferIndex >= 0) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (startTimeMillis == 0) {
                            startTimeMillis = currentTimeMillis;
                        } else {
                            long sleepTime = (info.presentationTimeUs / 1000) -
                                    (currentTimeMillis - startTimeMillis);
                            if (sleepTime > 0) {
                                try {
                                    Thread.sleep(sleepTime);
                                } catch (InterruptedException e) {}
                            }
                        }
                        mediaCodec.releaseOutputBuffer(outputBufferIndex, true);
                        if ((info.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
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

    }

}
