package com.blog.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.blog.demo.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/7/10.
 */

public class OpenGLGeometryShaderActivity extends AbstractOpenGLActivity {

    @Override
    protected GLSurfaceView.Renderer getOpenGLRender() {
        return new OpenGLGeometryShaderRender();
    }

    class OpenGLGeometryShaderRender implements GLSurfaceView.Renderer {
        private final float[] projectionMatrix = new float[16];
        private final float[] modelMatrix = new float[16];
        private final float[] viewMatrix = new float[16];
        private final float[] viewProjectionMatrix = new float[16];
        private final float[] modelViewProjectionMatrix = new float[16];

        private TextureProgram mTextureProgram;
        private GeometryColorProgram mColorProgram;

        private Table mTable;
        private GeometryMallet mMallet;
        private GeometryPuck mPuck;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

            mTable = new Table();

            // 创建木锥
            ResourceDataBuilder.ResourceData data = ResourceDataBuilder.createMallet(
                    new Geometry.Cylinder(new Geometry.Point(0f, 0f, 0f), 0.08f, 0.15f), 32);
            mMallet = new GeometryMallet(data, 0.08f, 0.15f);

            // 创建冰球
            data = ResourceDataBuilder.createPuck(
                    new Geometry.Cylinder(new Geometry.Point(0, 0, 0), 0.06f, 0.02f), 32);
            mPuck = new GeometryPuck(data, 0.06f, 0.02f);

            mTextureProgram = new TextureProgram(OpenGLGeometryShaderActivity.this,
                    R.drawable.air_hockey_surface);
            mColorProgram = new GeometryColorProgram(OpenGLGeometryShaderActivity.this);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);

            // 创建透视投影
            Matrix.perspectiveM(projectionMatrix, 0, 45, (float)width / (float)height, 1, 10);
            Matrix.setLookAtM(viewMatrix, 0, 0f, -2.4f, 1.4f, 0f, 0f, 0f, 0f, 1f, 0f);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

            // 绘制桌子
            positionTableInScene();
            mTextureProgram.setUniform(modelViewProjectionMatrix);
            mTable.bindData(mTextureProgram);
            mTable.draw();

            // 绘制红色木锥
            positionObjectInScene(0f, 0.4f, mMallet.height / 2f);
            mColorProgram.setUniform(modelViewProjectionMatrix);
            mColorProgram.setColor(1f, 0f, 0f);
            mMallet.bindData(mColorProgram);
            mMallet.draw();

            // 绘制蓝色木锥
            positionObjectInScene(0f, -0.4f, mMallet.height / 2f);
            mColorProgram.setUniform(modelViewProjectionMatrix);
            mColorProgram.setColor(0f, 0f, 1f);
            mMallet.bindData(mColorProgram);
            mMallet.draw();

            // 绘制冰球
            positionObjectInScene(0f, 0f, mPuck.height / 2f);
            mColorProgram.setUniform(modelViewProjectionMatrix);
            mColorProgram.setColor(0.8f, 0.8f, 1f);
            mPuck.bindData(mColorProgram);
            mPuck.draw();
        }

        private void positionTableInScene() {
            Matrix.setIdentityM(modelMatrix, 0);
            Matrix.multiplyMM(modelViewProjectionMatrix, 0, viewProjectionMatrix,
                    0, modelMatrix, 0);
        }

        // 移动木锥
        private void positionObjectInScene(float x, float y, float z) {
            Matrix.setIdentityM(modelMatrix, 0);
            Matrix.translateM(modelMatrix, 0, x, y, z);
            Matrix.multiplyMM(modelViewProjectionMatrix, 0, viewProjectionMatrix,
                    0, modelMatrix, 0);
        }

    }
}
