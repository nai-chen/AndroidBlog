package com.blog.demo.opengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;

/**
 * Created by cn on 2017/7/20.
 */

public class SkyBox extends Resource {
    private final static int POSITION_COMPONENT_COUNT = 3;

    private final ByteBuffer indexArray;

    private final static float[] vertexData = new float[]{
             1,  1,  1,
             1,  1, -1,
            -1,  1, -1,
            -1,  1,  1,
             1, -1,  1,
             1, -1, -1,
            -1, -1, -1,
            -1, -1,  1,
    };

    public SkyBox() {
        super(vertexData);

        indexArray = ByteBuffer.allocate(3 * 2 * 6).put(new byte[]{
                // Front
                0, 3, 7,
                0, 7, 4,

                // Back
                1, 2, 6,
                1, 6, 5,

                // Left
                2, 3, 7,
                2, 7, 6,

                // Right
                0, 4, 5,
                0, 5, 1,

                // Top
                0, 1, 2,
                0, 2, 3,

                // Bottom
                4, 5, 6,
                4, 6, 7
        });
        indexArray.position(0);
    }

    public void bindData(SkyBoxProgram program) {
        bindData(program.getPositionLocation(), 0, POSITION_COMPONENT_COUNT, 0);
    }

    @Override
    public void draw() {
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 36, GLES20.GL_UNSIGNED_BYTE, indexArray);
    }
}
