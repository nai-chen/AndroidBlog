package com.blog.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.blog.demo.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/6/29.
 */

public class OpenGLVertexShaderActivity extends AbstractOpenGLActivity {

    @Override
    protected GLSurfaceView.Renderer getOpenGLRender() {
        return new OpenGLVertexShaderRender();
    }

    private class OpenGLVertexShaderRender implements GLSurfaceView.Renderer {
        private static final String U_COLOR = "u_Color";
        private static final String A_POSITION = "a_Position";
        private static final int POSITION_COMPONENT_COUNT = 2;
        private static final int BYTES_PER_FLOAT = 4;
        private FloatBuffer vertexData;

        private int mProgramId;
        private int uColorLocation;
        private int aPositionLocation;

        OpenGLVertexShaderRender() {
            float[] tableVerticesWithTriangles = {
                // 左上角
                -0.5f, -0.5f,
                0.5f,  0.5f,
                -0.5f,  0.5f,

                // 右下角
                -0.5f, -0.5f,
                0.5f, -0.5f,
                0.5f,  0.5f,

                // 直线
                -0.5f, 0f,
                0.5f, 0f,

                // 点
                0f, -0.25f,
                0f,  0.25f
            };

            // ByteBuffer.allocateDirect分配一块本地内存，不被垃圾回收，保存到FloatBuffer中
            vertexData = ByteBuffer
                    .allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                    .order(ByteOrder.nativeOrder())
                    .asFloatBuffer();
            vertexData.put(tableVerticesWithTriangles);
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

            mProgramId = useProgram(R.raw.simple_vertex_shader, R.raw.simple_fragment_shader);

            // 获取Uniform的位置
            uColorLocation = GLES20.glGetUniformLocation(mProgramId, U_COLOR);
            // 获取Attribute位置
            aPositionLocation = GLES20.glGetAttribLocation(mProgramId, A_POSITION);

            vertexData.position(0);
            GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT,
                    GLES20.GL_FLOAT, false, 0, vertexData);
            GLES20.glEnableVertexAttribArray(aPositionLocation);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            // 绘制三角形
            GLES20.glUniform4f(uColorLocation, 1, 1, 1, 1);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

            // 绘制直线
            GLES20.glUniform4f(uColorLocation, 1, 0, 0, 1);
            GLES20.glDrawArrays(GLES20.GL_LINES, 6, 2);

            // 绘制点
            GLES20.glUniform4f(uColorLocation, 0, 0, 1, 1);
            GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);

            // 绘制点
            GLES20.glUniform4f(uColorLocation, 1, 0, 0, 1);
            GLES20.glDrawArrays(GLES20.GL_POINTS, 9, 1);
        }

    }

}
