package com.blog.demo.image

import android.app.Activity
import android.media.*
import android.os.Bundle
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import com.blog.demo.LogTool
import com.blog.demo.R
import java.io.IOException

class MediaCodecActivity : Activity(), View.OnClickListener {
    private val LOG_TAG = "MediaCodecActivity"

    private var mStopAudio = false
    private var mStopVideo = false
    private var mSurface: Surface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_media_codec)

        findViewById<Button>(R.id.btn_start_audio).setOnClickListener(this)
        findViewById<Button>(R.id.btn_stop_audio).setOnClickListener(this)
        findViewById<Button>(R.id.btn_start_video).setOnClickListener(this)
        findViewById<Button>(R.id.btn_stop_video).setOnClickListener(this)

        val surfaceView: SurfaceView = findViewById(R.id.surface_view)
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                mSurface = holder.surface
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }
        })
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_start_audio) {
            Thread {
                startAudio("demo.mp3")
            }.start()
        } else if (v.id == R.id.btn_stop_audio) {
            mStopAudio = true
        } else if (v.id == R.id.btn_start_video) {
            Thread {
                startVideo("video_demo.mp4")
            }.start()
        } else if (v.id == R.id.btn_stop_video) {
            mStopVideo = true
        }
    }

    private fun startAudio(fileName: String) {
        mStopAudio = false
        val extractor = MediaExtractor()
        try {
            val fd = assets.openFd(fileName)
            extractor.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        } catch (e: IOException) {
            LogTool.loge(LOG_TAG, e)
            return
        }
        var format: MediaFormat? = null
        for (index in 0 until extractor.trackCount) {
            val mf = extractor.getTrackFormat(index)
            val mime = mf.getString(MediaFormat.KEY_MIME)
            if (mime?.startsWith("audio/") == true) {
                format = mf
                // 选择音频
                extractor.selectTrack(index)
                break
            }
        }
        if (format != null) {
            val sampleRate = format.getInteger(MediaFormat.KEY_SAMPLE_RATE)
            // AudioTrack播放音频
            val bufferSize = AudioTrack.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT)
            val audioTrack = AudioTrack(AudioManager.STREAM_MUSIC, sampleRate,
                AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                bufferSize, AudioTrack.MODE_STREAM)
            try {
                audioTrack.play()
                playAudio(extractor, format, audioTrack)
            } catch (e: Exception) {
                LogTool.loge(LOG_TAG, e)
            } finally {
                extractor.release()
                audioTrack.stop()
                audioTrack.release()
            }
        }
    }

    @Throws(IOException::class)
    private fun playAudio(extractor: MediaExtractor, format: MediaFormat, audioTrack: AudioTrack) {
        val mime = format.getString(MediaFormat.KEY_MIME)
        val mediaCodec = MediaCodec.createDecoderByType(mime!!)
        mediaCodec.configure(format, null, null, 0)
        mediaCodec.start()

        var outputEos = false
        var inputEos = false
        val timeoutUs: Long = 10
        val info = MediaCodec.BufferInfo()

        while (!outputEos && !mStopAudio) {
            try {
                if (!inputEos) {
                    val inputBufferIndex = mediaCodec.dequeueInputBuffer(timeoutUs)
                    LogTool.logi(LOG_TAG, "inputBufferIndex = $inputBufferIndex")
                    if (inputBufferIndex >= 0) {
                        val buffer = mediaCodec.getInputBuffer(inputBufferIndex)
                        var sampleSize = extractor.readSampleData(buffer!!, 0)
                        LogTool.logi(LOG_TAG, "sampleSize = $sampleSize")

                        var presentationTime: Long = 0
                        if (sampleSize < 0) {
                            inputEos = true
                            sampleSize = 0
                        } else {
                            presentationTime = extractor.sampleTime
                        }
                        LogTool.logi(LOG_TAG, "presentationTime = $presentationTime")
                        var flags = if (inputEos) MediaCodec.BUFFER_FLAG_END_OF_STREAM else 0
                        mediaCodec.queueInputBuffer(inputBufferIndex, 0, sampleSize, presentationTime, flags)
                        if (!inputEos) {
                            extractor.advance()
                        }
                    }
                    val outputBufferIndex = mediaCodec.dequeueOutputBuffer(info, timeoutUs)
                    LogTool.logi(LOG_TAG, "outputBufferIndex = $outputBufferIndex")
                    LogTool.logi(LOG_TAG, "info.presentationTimeUs = " + info.presentationTimeUs)

                    if (outputBufferIndex >= 0) {
                        val buffer = mediaCodec.getOutputBuffer(outputBufferIndex)
                        val buf = ByteArray(info.size)
                        buffer?.get(buf)
                        buffer?.clear()

                        if (buf.isNotEmpty()) {
                            audioTrack.write(buf, 0, buf.size)
                        }
                        mediaCodec.releaseOutputBuffer(outputBufferIndex, false)
                        if (info.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0) {
                            LogTool.logi(LOG_TAG, "output EOS.")
                            outputEos = true
                        }
                    }
                }
            } catch (e: Exception) {
                LogTool.loge(LOG_TAG, e)
            }
        }
        if (mediaCodec != null) {
            mediaCodec.stop()
            mediaCodec.release()
        }
    }

    private fun startVideo(fileName: String) {
        mStopVideo = false
        val extractor = MediaExtractor()
        try {
            val fd = assets.openFd(fileName)
            extractor.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        } catch (e: IOException) {
            return
        }

        var format: MediaFormat? = null
        for (index in 0 until extractor.trackCount) {
            val mf = extractor.getTrackFormat(index)
            val mime = mf.getString(MediaFormat.KEY_MIME)

            // 选择视频
            if (mime!!.startsWith("video/")) {
                format = mf
                extractor.selectTrack(index)
                break
            }
        }
        if (format != null && mSurface != null) {
            try {
                playVideo(extractor, format, mSurface!!)
            } catch (e: Exception) {
                LogTool.loge(LOG_TAG, e)
            } finally {
                extractor.release()
            }
        }
    }

    @Throws(IOException::class)
    private fun playVideo(extractor: MediaExtractor, format: MediaFormat, surface: Surface) {
        val mime = format.getString(MediaFormat.KEY_MIME)
        val mediaCodec = MediaCodec.createDecoderByType(mime!!)

        // 绑定surface
        mediaCodec.configure(format, surface, null, 0)
        mediaCodec.start()

        val inputBufferArray = mediaCodec.inputBuffers
        var outputEos = false
        var inputEos = false
        val timeoutUs: Long = 10
        var startTimeMillis: Long = 0
        val info = MediaCodec.BufferInfo()

        while (!outputEos && !mStopVideo) {
            try {
                if (!inputEos) {
                    val inputBufferIndex = mediaCodec.dequeueInputBuffer(timeoutUs)
                    if (inputBufferIndex >= 0) {
                        var presentationTime: Long = 0
                        val buffer = inputBufferArray[inputBufferIndex]
                        var sampleSize = extractor.readSampleData(buffer, 0)
                        if (sampleSize < 0) {
                            inputEos = true
                            sampleSize = 0
                        } else {
                            presentationTime = extractor.sampleTime
                        }

                        var flags = if (inputEos) MediaCodec.BUFFER_FLAG_END_OF_STREAM else 0
                        mediaCodec.queueInputBuffer(inputBufferIndex, 0, sampleSize, presentationTime, flags)
                        if (!inputEos) {
                            extractor.advance()
                        }
                    }

                    val outputBufferIndex = mediaCodec.dequeueOutputBuffer(info, timeoutUs)
                    if (outputBufferIndex >= 0) {
                        val currentTimeMillis = System.currentTimeMillis()
                        if (startTimeMillis == 0L) {
                            startTimeMillis = currentTimeMillis
                        } else {
                            val sleepTime = info.presentationTimeUs / 1000 - (currentTimeMillis - startTimeMillis)
                            if (sleepTime > 0) {
                                try {
                                    Thread.sleep(sleepTime)
                                } catch (e: InterruptedException) {
                                }
                            }
                        }

                        mediaCodec.releaseOutputBuffer(outputBufferIndex, true)
                        if (info.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0) {
                            outputEos = true
                        }
                    }
                }
            } catch (e: Exception) {
            }
        }
        if (mediaCodec != null) {
            mediaCodec.stop()
            mediaCodec.release()
        }
    }

}