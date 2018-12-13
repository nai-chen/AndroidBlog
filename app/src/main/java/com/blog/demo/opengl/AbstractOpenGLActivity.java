package com.blog.demo.opengl;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.Toast;

import com.blog.demo.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cn on 2017/7/3.
 */

public abstract class AbstractOpenGLActivity extends Activity {

    protected GLSurfaceView mSurfaceView;
    protected boolean mRender = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSurfaceView = new GLSurfaceView(this);

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo confInfo = am.getDeviceConfigurationInfo();
        boolean supportEs2 = confInfo.reqGlEsVersion >= 0x20000;

        if (supportEs2) {
            // 选择OpenGL ES 2.0
            mSurfaceView.setEGLContextClientVersion(2);
            // 设置渲染
            mSurfaceView.setRenderer(getOpenGLRender());
            mRender = true;
        } else {
            Toast.makeText(this , "This device does not support OpenGL ES 2.0",
                    Toast.LENGTH_LONG).show();
        }
        setContentView(mSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mRender) {
            mSurfaceView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mRender) {
            mSurfaceView.onPause();
        }
    }

    protected abstract GLSurfaceView.Renderer getOpenGLRender();

    protected String readShaderFromRaw(int resId) {
        BufferedReader br = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(
                    getResources().openRawResource(resId)));
            String line = null;
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }
        } catch (IOException e) {

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return stringBuffer.toString();
    }

    protected int compileShader(int type, String shaderCode) {
        // 创建一个新的着色器对象
        int shaderObjectId = GLES20.glCreateShader(type);
        if (shaderObjectId == 0) {
            // 创建失败
            return 0;
        }

        // 上传和编译着色器代码
        GLES20.glShaderSource(shaderObjectId, shaderCode);
        GLES20.glCompileShader(shaderObjectId);

        // 获取编译状态
        int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        // 获取着色器信息日志
        LogUtil.log("OpenGL", GLES20.glGetShaderInfoLog(shaderObjectId));

        if (compileStatus[0] == 0) {
            // 如果失败，删除着色器对象
            GLES20.glDeleteShader(shaderObjectId);
            return 0;
        }

        return shaderObjectId;
    }

    protected int linkProgram(int vertexShaderId, int fragmentShaderId) {
        // 创建一个新的程序对象
        int programId = GLES20.glCreateProgram();
        if (programId == 0) {
            return 0;
        }

        // 新建程序对象附上着色器，并链接程序
        GLES20.glAttachShader(programId, vertexShaderId);
        GLES20.glAttachShader(programId, fragmentShaderId);
        GLES20.glLinkProgram(programId);

        // 获取链接状态
        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        // 获取着程序链接信息日志
        LogUtil.log("OpenGL", GLES20.glGetProgramInfoLog(programId));

        if (linkStatus[0] == 0) {
            // 如果链接失败，删除程序对象
            GLES20.glDeleteProgram(programId);
            return 0;
        }

        return programId;
    }

    protected boolean validateProgram(int programId) {
        // 验证程序，只在开发阶段需要
        GLES20.glValidateProgram(programId);
        LogUtil.log("OpenGL", GLES20.glGetProgramInfoLog(programId));

        int[] validateStatus = new int[1];
        GLES20.glGetProgramiv(programId, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);

        return validateStatus[0] != 0;
    }

    protected int useProgram(int vertexShaderResId, int fragmentShaderResId) {
        String vertexShaderCode = readShaderFromRaw(vertexShaderResId);
        String fragmentShaderCode = readShaderFromRaw(fragmentShaderResId);

        int vertexShaderId = compileShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShaderId = compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        int programId = linkProgram(vertexShaderId, fragmentShaderId);
        validateProgram(programId);

        GLES20.glUseProgram(programId);

        return programId;
    }

}
