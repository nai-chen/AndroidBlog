package com.blog.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.blog.demo.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/7/6.
 */

public class OpenGLTextureShaderActivity extends AbstractOpenGLActivity {

    @Override
    protected GLSurfaceView.Renderer getOpenGLRender() {
        return new OpenGLTextureShaderRender();
    }

    class OpenGLTextureShaderRender implements GLSurfaceView.Renderer {
        private float[] projectionMatrix = new float[16];
        private float[] modelMatrix = new float[16];
        private float[] modelProjectionMatrix = new float[16];

        private TextureProgram mTextureProgram;
        private ColorProgram mColorProgram;

        private Table mTable;
        private Mallet mMallet;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

            mTable = new Table();
            mMallet = new Mallet();

            mTextureProgram = new TextureProgram(OpenGLTextureShaderActivity.this,
                    R.drawable.air_hockey_surface);
            mColorProgram = new ColorProgram(OpenGLTextureShaderActivity.this);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);

            // 创建透视投影
            Matrix.perspectiveM(projectionMatrix, 0, 45, (float)width / (float)height, 1, 10);

            // 定义模型矩阵
            Matrix.setIdentityM(modelMatrix, 0);
            // z轴平移-2.8
            Matrix.translateM(modelMatrix, 0, 0, 0, -2.8f);
            Matrix.rotateM(modelMatrix, 0, -60, 1f, 0f, 0f);

            // 把投影矩阵和模型矩阵相乘
            Matrix.multiplyMM(modelProjectionMatrix, 0, projectionMatrix, 0, modelMatrix, 0);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            mTextureProgram.setUniform(modelProjectionMatrix);
            mTable.bindData(mTextureProgram);
            mTable.draw();

            mColorProgram.setUniform(modelProjectionMatrix);
            mMallet.bindData(mColorProgram);
            mMallet.draw();
        }
    }
}
