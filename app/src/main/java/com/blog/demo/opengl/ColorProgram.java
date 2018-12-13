package com.blog.demo.opengl;

import android.content.Context;
import android.opengl.GLES20;

import com.blog.demo.R;

/**
 * Created by cn on 2017/7/7.
 */

public class ColorProgram extends Program {
    private final static String A_POSITION = "a_Position";
    private final static String A_COLOR = "a_Color";
    private final static String U_MATRIX = "u_Matrix";

    private int aPositionLocation, aColorLocation, uMatrixLocation;

    public ColorProgram(Context context) {
        super(context, R.raw.ortho_vertex_shader, R.raw.ortho_fragment_shader);

        aPositionLocation = GLES20.glGetAttribLocation(mProgramId, A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(mProgramId, A_COLOR);
        uMatrixLocation = GLES20.glGetUniformLocation(mProgramId, U_MATRIX);
    }

    @Override
    public void setUniform(float[] projectionMatrix) {
        GLES20.glUseProgram(mProgramId);

        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);
    }

    public int getPositionLocation() {
        return aPositionLocation;
    }

    public int getColorLocation() {
        return aColorLocation;
    }

}
