package com.blog.demo.opengl;

import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/6/29.
 */

public class OpenGLParticleShaderActivity extends AbstractOpenGLActivity {

    @Override
    protected GLSurfaceView.Renderer getOpenGLRender() {
        return new OpenGLParticleShaderRender();
    }

    private class OpenGLParticleShaderRender implements GLSurfaceView.Renderer {
        private Particle mParticle;
        private ParticleProgram mParticleProgram;
        private ParticleShooter mRedParticleShooter, mGreenParticleShooter, mBlueParticleShooter;
        private long mGlobalStartTime;

        private float[] projectionMatrix = new float[16];
        private float[] viewlMatrix = new float[16];
        private float[] viewProjectionMatrix = new float[16];

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.1f);

            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE);

            mParticleProgram = new ParticleProgram(OpenGLParticleShaderActivity.this);
            mParticle = new Particle(10000);

            mGlobalStartTime = System.nanoTime();

            final Geometry.Vector particleDirection = new Geometry.Vector(0f, 0.5f, 0f);

//            mRedParticleShooter = new ParticleShooter(new Geometry.Point(-1, 0, 0),
//                    particleDirection, Color.rgb(250, 50, 5));
//
//            mGreenParticleShooter = new ParticleShooter(new Geometry.Point(0, 0, 0),
//                    particleDirection, Color.rgb(25, 255, 25));
//
//            mBlueParticleShooter = new ParticleShooter(new Geometry.Point(1, 0, 0),
//                    particleDirection, Color.rgb(5, 50, 255));
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

            // 定义模型矩阵
            Matrix.setIdentityM(viewlMatrix, 0);
            Matrix.translateM(viewlMatrix, 0, 0f, -1.5f, -5f);

            Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewlMatrix, 0);
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
    }

}
