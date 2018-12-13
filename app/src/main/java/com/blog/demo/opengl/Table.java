package com.blog.demo.opengl;

import android.opengl.GLES20;

/**
 * Created by cn on 2017/7/10.
 */

public class Table extends Resource {
    private final static int POSITION_COMPONENT_COUNT = 2;
    private final static int TEXTURE_COMPONENT_COUNT = 2;

    private final static int STRIDE = (POSITION_COMPONENT_COUNT + TEXTURE_COMPONENT_COUNT)
            * VertexArray.BYTES_PER_FLOAT;

    private final static float[] vertexData = new float[]{
               0f,    0f, 0.5f, 0.5f,
            -0.5f, -0.8f,   0f, 0.9f,
             0.5f, -0.8f,   1f, 0.9f,
             0.5f,  0.8f,   1f, 0.1f,
            -0.5f,  0.8f,   0f, 0.1f,
            -0.5f, -0.8f,   0f, 0.9f,
    };

    public Table() {
        super(vertexData);
    }

    public void bindData(TextureProgram program) {
        bindData(program.getPositionLocation(), 0, POSITION_COMPONENT_COUNT, STRIDE);
        bindData(program.getTextureCoordLocation(),
                POSITION_COMPONENT_COUNT, TEXTURE_COMPONENT_COUNT, STRIDE);
    }

    @Override
    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);
    }

}
