package com.blog.demo.opengl;

import android.opengl.GLES20;

/**
 * Created by cn on 2017/7/10.
 */

public class Mallet extends Resource {
    private final static int POSITION_COMPONENT_COUNT = 2;
    private final static int COLOR_COMPONENT_COUNT = 3;
    private final static int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT)
            * VertexArray.BYTES_PER_FLOAT;

    private final static float[] vertexData = new float[]{
            0f, -0.25f, 0f, 0f, 1f,
            0f,  0.25f, 1f, 0f, 0f
    };

    public Mallet() {
        super(vertexData);
    }

    public void bindData(ColorProgram program) {
        bindData(program.getPositionLocation(), 0,
                POSITION_COMPONENT_COUNT, STRIDE);
        bindData(program.getColorLocation(), POSITION_COMPONENT_COUNT,
                COLOR_COMPONENT_COUNT, STRIDE);
    }

    @Override
    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 2);
    }

}
