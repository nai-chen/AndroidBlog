package com.blog.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/7/20.
 */

public class OpenGLSkyBoxShaderActivity extends AbstractOpenGLActivity {
    private OpenGLSkyBoxShaderRender mSkyBoxShaderRender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            float previousX, previousY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event != null) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        previousX = event.getX();
                        previousY = event.getY();
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        final float deltaX = event.getX() - previousX;
                        final float deltaY = event.getY() - previousY;

                        previousX = event.getX();
                        previousY = event.getY();

                        mSurfaceView.queueEvent(new Runnable() {
                            @Override
                            public void run() {
                                mSkyBoxShaderRender.handleTouchDrag(deltaX, deltaY);
                            }
                        });

                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected GLSurfaceView.Renderer getOpenGLRender() {
        mSkyBoxShaderRender = new OpenGLSkyBoxShaderRender();
        return mSkyBoxShaderRender;
    }

    class OpenGLSkyBoxShaderRender implements GLSurfaceView.Renderer {
        private float[] projectionMatrix = new float[16];
        private float[] modelMatrix = new float[16];
        private float[] modelProjectionMatrix = new float[16];

        private SkyBox mSkyBox;
        private SkyBoxProgram mSkyBoxProgram;

        private float xRotation, yRotation;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            mSkyBox = new SkyBox();
            mSkyBoxProgram = new SkyBoxProgram(OpenGLSkyBoxShaderActivity.this);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);

            Matrix.perspectiveM(projectionMatrix, 0, 45, (float)width / (float)height, 1, 10);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            Matrix.setIdentityM(modelMatrix, 0);
            Matrix.rotateM(modelMatrix, 0, -yRotation, 1, 0, 0);
            Matrix.rotateM(modelMatrix, 0, -xRotation, 0, 1, 0);

            Matrix.multiplyMM(modelProjectionMatrix, 0, projectionMatrix, 0, modelMatrix, 0);

            mSkyBoxProgram.setUniform(modelProjectionMatrix);
            mSkyBox.bindData(mSkyBoxProgram);
            mSkyBox.draw();
        }

        private void handleTouchDrag(float deltaX, float deltaY) {
            xRotation += deltaX / 16f;
            yRotation += deltaY / 16f;

            if (yRotation < -90) {
                yRotation = -90;
            } else if (yRotation > 90){
                yRotation = 90;
            }

        }
    }
}
