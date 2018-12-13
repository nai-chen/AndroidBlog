package com.blog.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.blog.demo.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/7/5.
 */

public class OpenGLPerspectiveShaderActivity extends AbstractOpenGLActivity {

    @Override
    protected GLSurfaceView.Renderer getOpenGLRender() {
        return new OpenGLPerspectiveShaderRender();
    }

    private class OpenGLPerspectiveShaderRender implements GLSurfaceView.Renderer {
        private final static String A_POSITION = "a_Position";
        private final static String A_COLOR = "a_Color";
        private final static String U_MATRIX = "u_Matrix";

        private static final int POSITION_COMPONENT_COUNT = 2;
        private static final int COLOR_COMPONENT_COUNT = 3;
        private static final int BYTES_PER_FLOAT = 4;
        private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT)
                * BYTES_PER_FLOAT;

        private float[] projectionMatrix = new float[16];
        private float[] modelMatrix = new float[16];
        private float[] modelProjectionMatrix = new float[16];
        private FloatBuffer vertexData;
        private int mProgramId;
        private int aPositionLocation, aColorLocation, uMatrixLocation;

        OpenGLPerspectiveShaderRender() {
            float[] tableVerticesWithTriangles = {
                    // 中心点
                    0f, 0f, 1f, 1f, 1f,

                    // 四个角
                    -0.5f, -0.8f, 0.7f, 0.7f, 0.7f,
                     0.5f, -0.8f, 0.7f, 0.7f, 0.7f,
                     0.5f,  0.8f, 0.7f, 0.7f, 0.7f,
                    -0.5f,  0.8f, 0.7f, 0.7f, 0.7f,
                    -0.5f, -0.8f, 0.7f, 0.7f, 0.7f,

                    // 直线
                    -0.5f, 0f, 1f, 0f, 0f,
                    0.5f, 0f, 1f, 0f, 0f,

                    // 点
                    0f, -0.25f, 0, 0, 1,
                    0f,  0.25f, 1, 0, 0
            };

            vertexData = ByteBuffer
                    .allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                    .order(ByteOrder.nativeOrder())
                    .asFloatBuffer();
            vertexData.put(tableVerticesWithTriangles);
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

            mProgramId = useProgram(R.raw.ortho_vertex_shader, R.raw.ortho_fragment_shader);

            // 获取Attribute位置
            aPositionLocation = GLES20.glGetAttribLocation(mProgramId, A_POSITION);
            aColorLocation = GLES20.glGetAttribLocation(mProgramId, A_COLOR);
            uMatrixLocation = GLES20.glGetUniformLocation(mProgramId, U_MATRIX);

            vertexData.position(0);
            GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT,
                    GLES20.GL_FLOAT, false, STRIDE, vertexData);
            GLES20.glEnableVertexAttribArray(aPositionLocation);

            vertexData.position(POSITION_COMPONENT_COUNT);
            GLES20.glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT,
                    GLES20.GL_FLOAT, false, STRIDE, vertexData);
            GLES20.glEnableVertexAttribArray(aColorLocation);
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

            // 给着色器传递正交投影矩阵
            GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, modelProjectionMatrix, 0);

            // 绘制三角形
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);

            // 绘制直线
            GLES20.glDrawArrays(GLES20.GL_LINES, 6, 2);

            // 绘制点
            GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);

            // 绘制点
            GLES20.glDrawArrays(GLES20.GL_POINTS, 9, 1);
        }

    }

}
