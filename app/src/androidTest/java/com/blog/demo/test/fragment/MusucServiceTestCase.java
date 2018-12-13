package com.blog.demo.test.fragment;

import android.content.Intent;
import android.test.ServiceTestCase;

import com.blog.demo.MusicService;
import com.blog.demo.MusicService.IMusicPlayer;

/**
 * Created by cn on 2017/3/23.
 */

public class MusucServiceTestCase extends ServiceTestCase<MusicService> {
    private MusicService mService;

    public MusucServiceTestCase() {
        super(MusicService.class);
    }

    public void testState() {
        Intent intent = new Intent(getContext(), MusicService.class);
        startService(intent);

        mService = getService();
        assertEquals(IMusicPlayer.STATE_INIT, mService.state());

        // 测试startMusic方法
        mService.startMusic();
        assertEquals(IMusicPlayer.STATE_PLAY, mService.state());

        try {
            mService.startMusic();
            fail();
        } catch (IllegalStateException e) {
        }

        // 测试pauseMusic方法
        mService.pauseMusic();
        assertEquals(IMusicPlayer.STATE_PAUSE, mService.state());

        try {
            mService.pauseMusic();
            fail();
        } catch (IllegalStateException e) {
        }

        // 测试playMusic方法
        mService.playMusic();
        assertEquals(IMusicPlayer.STATE_PLAY, mService.state());

        try {
            mService.playMusic();
            fail();
        } catch (IllegalStateException e) {
        }

        // 测试stopMusic方法
        mService.stopMusic();
        assertEquals(IMusicPlayer.STATE_STOP, mService.state());

        try {
            mService.stopMusic();
            fail();
        } catch (IllegalStateException e) {
        }

    }

}
