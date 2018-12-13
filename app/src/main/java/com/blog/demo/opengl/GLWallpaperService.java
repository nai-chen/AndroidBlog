package com.blog.demo.opengl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.blog.demo.LogUtil;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/8/8.
 */

public class GLWallpaperService extends WallpaperService {

    @Override
    public Engine onCreateEngine() {
        return new GLEngine();
    }

    public class GLEngine extends Engine {
        private WallpaperGLSurfaceView mSurfaceView;
        private OpenGLParticleShaderRender mShaderRender;
        private boolean mRender;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            mSurfaceView = new WallpaperGLSurfaceView(GLWallpaperService.this);

            ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            ConfigurationInfo configurationInfo = am.getDeviceConfigurationInfo();

            boolean supportEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

            if (supportEs2) {
                // 选择OpenGL ES 2.0
                mSurfaceView.setEGLContextClientVersion(2);
                mShaderRender = new OpenGLParticleShaderRender();
                // 设置渲染
                mSurfaceView.setRenderer(mShaderRender);
                mRender = true;
            } else {
                Toast.makeText(GLWallpaperService.this ,
                        "This device does not support OpenGL ES 2.0",
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            if (mRender) {
                if (visible) {
                    mSurfaceView.onResume();
                } else {
                    mSurfaceView.onPause();
                }
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();

            mSurfaceView.onWallpaperDestroy();
        }

        @Override
        public void onOffsetsChanged(final float xOffset, final float yOffset, float xOffsetStep,
                                     float yOffsetStep, int xPixelOffset, int yPixelOffset) {
            mSurfaceView.queueEvent(new Runnable() {
                @Override
                public void run() {
                    LogUtil.log("GLEngine", "xOffset = " + xOffset + ", yOffset = " + yOffset);
                    mShaderRender.handleOffsetsChanged(xOffset, yOffset);
                }
            });
        }

        class WallpaperGLSurfaceView extends GLSurfaceView {

            public WallpaperGLSurfaceView(Context context) {
                super(context);
            }

            @Override
            public SurfaceHolder getHolder() {
                return GLEngine.this.getSurfaceHolder();
            }

            public void onWallpaperDestroy() {
                super.onDetachedFromWindow();
            }
        }

    }

    private class OpenGLParticleShaderRender implements GLSurfaceView.Renderer {
        private Particle mParticle;
        private ParticleProgram mParticleProgram;
        private ParticleShooter mRedParticleShooter, mGreenParticleShooter, mBlueParticleShooter;
        private long mGlobalStartTime;

        private float[] projectionMatrix = new float[16];
        private float[] viewMatrix = new float[16];
        private float[] viewProjectionMatrix = new float[16];

        private float xOffset, yOffset;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.1f);

            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE);

            mParticleProgram = new ParticleProgram(GLWallpaperService.this);
            mParticle = new Particle(10000);

            mGlobalStartTime = System.nanoTime();

            final Geometry.Vector particleDirection = new Geometry.Vector(0f, 0.5f, 0f);

            mRedParticleShooter = new ParticleShooter(new Geometry.Point(-1, 0, 0),
                    particleDirection, Color.rgb(250, 50, 5));

            mGreenParticleShooter = new ParticleShooter(new Geometry.Point(0, 0, 0),
                    particleDirection, Color.rgb(25, 255, 25));

            mBlueParticleShooter = new ParticleShooter(new Geometry.Point(1, 0, 0),
                    particleDirection, Color.rgb(5, 50, 255));
            final float angleVarianceInDegrees = 5f;
            final float speedVariance = 1f;

            mRedParticleShooter = new ParticleShooter(new Geometry.Point(-1, 0, 0),
                    particleDirection, Color.rgb(250, 50, 5), angleVarianceInDegrees, speedVariance);

            mGreenParticleShooter = new ParticleShooter(new Geometry.Point(0, 0, 0),
                    particleDirection, Color.rgb(25, 255, 25), angleVarianceInDegrees, speedVariance);

            mBlueParticleShooter = new ParticleShooter(new Geometry.Point(1, 0, 0),
                    particleDirection, Color.rgb(5, 50, 255), angleVarianceInDegrees, speedVariance);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            // 设置视图尺寸
            GLES20.glViewport(0, 0, width, height);

            // 创建透视投影
            Matrix.perspectiveM(projectionMatrix, 0, 45, (float)width / (float)height, 1f, 10f);

            updateViewMatrices();
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            // 清空屏幕
            GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT);

            float currentTime = (System.nanoTime() - mGlobalStartTime) / 1000000000f;
            mRedParticleShooter.addParticles(mParticle, currentTime, 5);
            mGreenParticleShooter.addParticles(mParticle, currentTime, 5);
            mBlueParticleShooter.addParticles(mParticle, currentTime, 5);

            mParticleProgram.setUniform(viewProjectionMatrix);
            mParticleProgram.setCurrentTime(currentTime);
            mParticle.bindData(mParticleProgram);
            mParticle.draw();
        }

        private void handleOffsetsChanged(float xOffset, float yOffset) {
            this.xOffset = (xOffset - 0.5f) * 2.5f;
            this.yOffset = (yOffset - 0.5f) * 2.5f;
            updateViewMatrices();
        }

        private void updateViewMatrices() {
            // 定义模型矩阵
            Matrix.setIdentityM(viewMatrix, 0);
            Matrix.translateM(viewMatrix, 0, 0f - xOffset, -1.5f - yOffset, -5f);

            Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
        }

    }

}
