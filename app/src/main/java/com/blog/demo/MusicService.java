package com.blog.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by cn on 2017/3/23.
 */

public class MusicService extends Service {
    private IMusicPlayer mMusicPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMusicPlayer = new MusicPlayerImpl();
    }

    public void startMusic() {
        mMusicPlayer.start();
    }

    public void playMusic() {
        mMusicPlayer.play();
    }

    public void pauseMusic() {
        mMusicPlayer.pause();
    }

    public void stopMusic() {
        mMusicPlayer.stop();
    }

    public int state() {
        return mMusicPlayer.state();
    }

    public static interface IMusicPlayer {
        public final static int STATE_INIT  = 0; // 初始状态
        public final static int STATE_PLAY  = 1; // 播放状态
        public final static int STATE_PAUSE = 2; // 暂停状态
        public final static int STATE_STOP  = 3; // 结束状态

        public void start();
        public void play();
        public void pause();
        public void stop();
        public int state();
    }

    private static class MusicPlayerImpl implements IMusicPlayer {
        private int mState = STATE_INIT;

        @Override
        public void start() {
            if (mState == STATE_INIT || mState == STATE_STOP) {
                mState = STATE_PLAY;
            } else {
                throw new IllegalStateException("Illegal State");
            }
        }

        @Override
        public void play() {
            if (mState == STATE_PAUSE) {
                mState = STATE_PLAY;
            } else {
                throw new IllegalStateException("Illegal State");
            }
        }

        @Override
        public void pause() {
            if (mState == STATE_PLAY) {
                mState = STATE_PAUSE;
            } else {
                throw new IllegalStateException("Illegal State");
            }
        }

        @Override
        public void stop() {
            if (mState == STATE_PLAY || mState == STATE_PAUSE) {
                mState = STATE_STOP;
            } else {
                throw new IllegalStateException("Illegal State");
            }
        }

        @Override
        public int state() {
            return mState;
        }
    }

}
