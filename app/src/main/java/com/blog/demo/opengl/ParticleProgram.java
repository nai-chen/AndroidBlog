package com.blog.demo.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2017/8/7.
 */

public class ParticleProgram extends Program {
    private final static String U_MATRIX = "u_Matrix";
    private final static String U_TIME = "u_Time";
    private final static String U_TEXT_UNIT = "u_TextUnit";

    private final static String A_POSITION = "a_Position";
    private final static String A_COLOR = "a_Color";
    private final static String A_DIRECTION_VECTOR = "a_DirectionVector";
    private final static String A_PARTICLE_START_TIME = "a_ParticleStartTime";

    private int uMatrixLocation, uTimeLocation, uTextUnitLocation;
    private int aPositionLocation, aColorLocation, aDirectionVectorLocation, aParticleStartTimeLocation;
    private int mTextureId;

    public ParticleProgram(Context context) {
        super(context, R.raw.particle_vertex_shader, R.raw.particle_fragment_shader);

        uMatrixLocation = GLES20.glGetUniformLocation(mProgramId, U_MATRIX);
        uTimeLocation = GLES20.glGetUniformLocation(mProgramId, U_TIME);
        uTextUnitLocation = GLES20.glGetUniformLocation(mProgramId, U_TEXT_UNIT);

        aPositionLocation = GLES20.glGetAttribLocation(mProgramId, A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(mProgramId, A_COLOR);
        aDirectionVectorLocation = GLES20.glGetAttribLocation(mProgramId, A_DIRECTION_VECTOR);
        aParticleStartTimeLocation = GLES20.glGetAttribLocation(mProgramId, A_PARTICLE_START_TIME);

        mTextureId = loadTexture(context, R.drawable.particle_texture);
    }

    protected int loadTexture(Context context, int resId) {
        // 生成一个新的OpenGL纹理的ID
        int[] textureObjectIds = new int[1];
        GLES20.glGenTextures(1, textureObjectIds, 0);

        if (textureObjectIds[0] == 0) {
            LogUtil.log("OpenGL", "Could not generate a new OpenGL texture object.");
            return 0;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        // 需要原始的图像数据，而不是缩放版
        options.inScaled = false;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options);
        if (bitmap == null) {
            LogUtil.log("OpenGL", "Resource ID " + resId + " could not be decoded.");
            // 如果失败，删除纹理对象
            GLES20.glDeleteTextures(1, textureObjectIds, 0);
            return 0;
        }

        // 我们需要告诉后面的纹理调用，都应该应用于这个纹理对象
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureObjectIds[0]);

        // 设置过滤参数
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_LINEAR_MIPMAP_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR);

        // 加载纹理到OpenGL
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        // 生成MIP贴图
        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);

        // 回收图片
        bitmap.recycle();
        // 与当前纹理解除绑定，防止被修改
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        return textureObjectIds[0];
    }

    @Override
    public void setUniform(float[] projectionMatrix) {
        GLES20.glUseProgram(mProgramId);

        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);

        // 把活动的纹理单元设置为纹理单元0
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        // 把纹理绑定到这个单元
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureId);
        // 把被选择的纹理单元传递给片段着色器中的
        GLES20.glUniform1i(uTextUnitLocation, 0);
    }

    public void setCurrentTime(float elapsedTime) {
        GLES20.glUniform1f(uTimeLocation, elapsedTime);
    }

    public int getPositionLocation() {
        return aPositionLocation;
    }

    public int getColorLocation() {
        return aColorLocation;
    }

    public int getDirectionVectorLocation() {
        return aDirectionVectorLocation;
    }

    public int getParticleStartTimeLocation() {
        return aParticleStartTimeLocation;
    }
}
