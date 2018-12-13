package com.blog.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/6/29.
 */

public class OpenGLHelloWorldActivity extends AbstractOpenGLActivity {

    @Override
    protected GLSurfaceView.Renderer getOpenGLRender() {
        return new OpenGLHelloWorldRender();
    }

    private class OpenGLHelloWorldRender implements GLSurfaceView.Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            // 清空屏幕颜色，参数对应红色、绿色、蓝色和透明度
            // 显示为红色
            GLES20.glClearColor(1, 0, 0, 0);
//            gl.glClearColor(1, 0, 0, 0);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            // 设置视图尺寸
            GLES20.glViewport(0, 0, width, height);
//            gl.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            // 清空屏幕
            GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT);
//            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        }

    }

}
