package com.blog.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.blog.demo.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/7/11.
 */

public class OpenGLEyeTextureShaderActivity extends AbstractOpenGLActivity {

    @Override
    protected GLSurfaceView.Renderer getOpenGLRender() {
        return new OpenGLEyeTextureShaderRender();
    }

    class OpenGLEyeTextureShaderRender implements GLSurfaceView.Renderer {
        private float[] projectionMatrix = new float[16];
        private float[] viewMatrix = new float[16];
        private float[] viewProjectMatrix = new float[16];
        private float[] modelMatrix = new float[16];
        private float[] modelViewProjectionMatrix = new float[16];

        private TextureProgram mTextureProgram;
        private ColorProgram mColorProgram;

        private Table mTable;
        private Mallet mMallet;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

            mTable = new Table();
            mMallet = new Mallet();

            mTextureProgram = new TextureProgram(OpenGLEyeTextureShaderActivity.this,
                    R.drawable.air_hockey_surface);
            mColorProgram = new ColorProgram(OpenGLEyeTextureShaderActivity.this);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);

            // 创建透视投影
            Matrix.perspectiveM(projectionMatrix, 0, 45, (float)width / (float)height, 1, 10);
//            // 移动视角，等同于模型矩阵沿z轴移动2.8
//            Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 2.8f, 0f, 0f, 0f, 0f, 1f, 0f);
            // 移动视角，代替模型翻转
             Matrix.setLookAtM(viewMatrix, 0, 0f, -2.4f, 1.4f, 0f, 0f, 0f, 0f, 1f, 0f);

            // 生成新的视图工程模型
            Matrix.multiplyMM(viewProjectMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

            // 定义模型矩阵
            Matrix.setIdentityM(modelMatrix, 0);
//            Matrix.rotateM(modelMatrix, 0, -60, 1f, 0f, 0f);
            // 生成工程模型矩阵
            Matrix.multiplyMM(modelViewProjectionMatrix, 0, viewProjectMatrix,
                    0, modelMatrix, 0);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            mTextureProgram.setUniform(modelViewProjectionMatrix);
            mTable.bindData(mTextureProgram);
            mTable.draw();

            mColorProgram.setUniform(modelViewProjectionMatrix);
            mMallet.bindData(mColorProgram);
            mMallet.draw();
        }
    }
}
