package com.blog.demo.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import com.blog.demo.R;

/**
 * Created by cn on 2017/7/20.
 */

public class SkyBoxProgram extends Program {
    private final static String U_MATRIX = "u_Matrix";
    private final static String A_POSITION = "a_Position";
    private final static String U_TEXTURE_UNIT = "u_textureUnit";

    private int uMatrixLocation, aPositionLocation, uTextureUnitLocation;
    private int mTextureObjectId;

    public SkyBoxProgram(Context context) {
        super(context, R.raw.skybox_vertex_shader, R.raw.skybox_fragment_shader);

        uMatrixLocation = GLES20.glGetUniformLocation(mProgramId, U_MATRIX);
        aPositionLocation = GLES20.glGetAttribLocation(mProgramId, A_POSITION);
        uTextureUnitLocation = GLES20.glGetAttribLocation(mProgramId, U_TEXTURE_UNIT);

        mTextureObjectId = loadCubeMap(context, new int[]{
                R.drawable.left, R.drawable.right,
                R.drawable.bottom, R.drawable.top,
                R.drawable.front, R.drawable.back
        });
    }

    @Override
    public void setUniform(float[] projectionMatrix) {
        GLES20.glUseProgram(mProgramId);

        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_CUBE_MAP, mTextureObjectId);
        GLES20.glUniform1i(uTextureUnitLocation, 0);
    }

    private int loadCubeMap(Context context, int[] cubeResources) {
        final int[] textureObjectIds = new int[1];
        GLES20.glGenTextures(1, textureObjectIds, 0);

        if (textureObjectIds[0] == 0) {
            return 0;
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        final Bitmap[] cubeBitmaps = new Bitmap[6];

        for (int i = 0; i < 6; i++) {
            cubeBitmaps[i] = BitmapFactory.decodeResource(context.getResources(),
                    cubeResources[i], options);

            if (cubeBitmaps[i] == null) {
                GLES20.glDeleteTextures(1, textureObjectIds, 0);
                return 0;
            }
        }

        // 配置纹理过滤器
        GLES20.glBindTexture(GLES20.GL_TEXTURE_CUBE_MAP, textureObjectIds[0]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_CUBE_MAP, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_CUBE_MAP, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR);

        // left/right
        GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_NEGATIVE_X, 0, cubeBitmaps[0], 0);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_POSITIVE_X, 0, cubeBitmaps[1], 0);

        // bottom/top
        GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, 0, cubeBitmaps[2], 0);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_POSITIVE_Y, 0, cubeBitmaps[3], 0);

        // front/back
        GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, 0, cubeBitmaps[4], 0);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_CUBE_MAP_POSITIVE_Z, 0, cubeBitmaps[5], 0);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_CUBE_MAP, 0);
        for (Bitmap bitmap : cubeBitmaps) {
            bitmap.recycle();
        }
        return textureObjectIds[0];
    }

    public int getPositionLocation() {
       return aPositionLocation;
    }

}
