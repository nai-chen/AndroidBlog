package com.blog.demo.opengl;

import android.content.Context;
import android.opengl.GLES20;

import com.blog.demo.R;

/**
 * Created by cn on 2017/7/7.
 */

public class GeometryColorProgram extends Program {
    private final static String A_POSITION = "a_Position";
    private final static String U_MATRIX = "u_Matrix";
    private final static String U_COLOR = "u_Color";

    private int aPositionLocation, uMatrixLocation, uColorLocation;

    public GeometryColorProgram(Context context) {
        super(context, R.raw.geometry_vertex_shader, R.raw.geometry_fragment_shader);

        aPositionLocation = GLES20.glGetAttribLocation(mProgramId, A_POSITION);
        uMatrixLocation = GLES20.glGetUniformLocation(mProgramId, U_MATRIX);
        uColorLocation = GLES20.glGetUniformLocation(mProgramId, U_COLOR);
    }

    @Override
    public void setUniform(float[] projectionMatrix) {
        GLES20.glUseProgram(mProgramId);

        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);
    }

    public void setColor(float r, float g, float b) {
        GLES20.glUniform4f(uColorLocation, r, g, b, 1f);
    }

    public int getPositionLocation() {
        return aPositionLocation;
    }

}
