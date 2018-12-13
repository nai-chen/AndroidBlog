package com.blog.demo.opengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by cn on 2017/7/7.
 */

public class VertexArray {
    public static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer floatBuffer;

    public VertexArray(float[] vertexData) {
        floatBuffer = ByteBuffer
                .allocateDirect(vertexData.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        floatBuffer.put(vertexData);
    }

    public void setVertexAttribPointer(int attributeLocation, int dataOffset,
                                       int componentCount, int stride) {
        floatBuffer.position(dataOffset);
        GLES20.glVertexAttribPointer(attributeLocation, componentCount,
                GLES20.GL_FLOAT, false, stride, floatBuffer);
        floatBuffer.position(0);
        GLES20.glEnableVertexAttribArray(attributeLocation);
    }

    public void updateBuffer(float[] vertexData, int start, int count) {
        floatBuffer.position(start);
        floatBuffer.put(vertexData, start, count);
        floatBuffer.position(0);
    }

}
