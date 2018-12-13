package com.blog.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cn on 2017/7/17.
 */

public class OpenGLTouchShaderActivity extends AbstractOpenGLActivity {

    private OpenGLTouchShaderRender mTouchShaderRender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event != null) {
                    final float normalizedX = (event.getX() / (float) v.getWidth()) * 2 - 1;
                    final float normalizedY = -((event.getY() / (float) v.getHeight()) * 2 - 1);

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mSurfaceView.queueEvent(new Runnable() {
                            @Override
                            public void run() {
                                if (mTouchShaderRender != null)
                                    mTouchShaderRender.handleTouchPress(normalizedX, normalizedY);
                            }
                        });
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        mSurfaceView.queueEvent(new Runnable() {
                            @Override
                            public void run() {
                                if (mTouchShaderRender != null)
                                    mTouchShaderRender.handleTouchDrag(normalizedX, normalizedY);
                            }
                        });
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected GLSurfaceView.Renderer getOpenGLRender() {
        mTouchShaderRender = new OpenGLTouchShaderRender();
        return mTouchShaderRender;
    }

    class OpenGLTouchShaderRender implements GLSurfaceView.Renderer {
        private boolean malletPressed = false;
        private Geometry.Point blueMalletPoint;

        private final float[] invertedViewProjectionMatrix = new float[16];

        private final float[] projectionMatrix = new float[16];
        private final float[] modelMatrix = new float[16];
        private final float[] viewMatrix = new float[16];
        private final float[] viewProjectionMatrix = new float[16];
        private final float[] modelViewProjectionMatrix = new float[16];

        private TextureProgram mTextureProgram;
        private GeometryColorProgram mColorProgram;

        private Table mTable;
        private GeometryMallet mMallet;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

            mTable = new Table();

            // 创建木锥
            ResourceDataBuilder.ResourceData data = ResourceDataBuilder.createMallet(
                    new Geometry.Cylinder(new Geometry.Point(0f, 0f, 0f), 0.08f, 0.15f), 32);
            mMallet = new GeometryMallet(data, 0.08f, 0.15f);
            blueMalletPoint = new Geometry.Point(0, -0.4f, mMallet.height / 2);

            mTextureProgram = new TextureProgram(OpenGLTouchShaderActivity.this,
                    R.drawable.air_hockey_surface);
            mColorProgram = new GeometryColorProgram(OpenGLTouchShaderActivity.this);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);

            // 创建透视投影
            Matrix.perspectiveM(projectionMatrix, 0, 45, (float)width / (float)height, 1, 10);
            Matrix.setLookAtM(viewMatrix, 0, 0f, -2.4f, 1.4f, 0f, 0f, 0f, 0f, 1f, 0f);

            Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
            Matrix.invertM(invertedViewProjectionMatrix, 0, viewProjectionMatrix, 0);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            // 绘制桌子
            positionTableInScene();
            mTextureProgram.setUniform(modelViewProjectionMatrix);
            mTable.bindData(mTextureProgram);
            mTable.draw();

            // 绘制蓝色木锥
            positionObjectInScene(blueMalletPoint.x, blueMalletPoint.y,  blueMalletPoint.z);
            mColorProgram.setUniform(modelViewProjectionMatrix);
            mColorProgram.setColor(0f, 0f, 1f);
            mMallet.bindData(mColorProgram);
            mMallet.draw();
        }

        private void positionTableInScene() {
            Matrix.setIdentityM(modelMatrix, 0);
            Matrix.multiplyMM(modelViewProjectionMatrix, 0, viewProjectionMatrix,
                    0, modelMatrix, 0);
        }

        // 移动木锥
        private void positionObjectInScene(float x, float y, float z) {
            Matrix.setIdentityM(modelMatrix, 0);
            Matrix.translateM(modelMatrix, 0, x, y, z);
            Matrix.multiplyMM(modelViewProjectionMatrix, 0, viewProjectionMatrix,
                    0, modelMatrix, 0);
        }

        public void handleTouchPress(float normalizedX, float normalizedY) {
            Geometry.Ray ray = convertNormalized2DPointToRay(normalizedX, normalizedY);
            // 定义一个球体，判断是否与射线相交
            Geometry.Sphere malletBoundingSphere = new Geometry.Sphere(blueMalletPoint, mMallet.height/2);
            malletPressed = Geometry.intersects(malletBoundingSphere, ray);
            LogUtil.log("OpenGLTouchShaderRender", "pressed: " + malletPressed);
        }

        public void handleTouchDrag(float normalizedX, float normalizedY) {
            if (malletPressed) {
                Geometry.Ray ray = convertNormalized2DPointToRay(normalizedX, normalizedY);

                Geometry.Plane plane = new Geometry.Plane(new Geometry.Point(0, 0, 0),
                        new Geometry.Vector(0, 0, 1));
                Geometry.Point touchedPoint = Geometry.intersectionPoint(ray, plane);
//                blueMalletPoint = new Geometry.Point(touchedPoint.x, touchedPoint.y,
//                        mMallet.height/2f);
                blueMalletPoint = new Geometry.Point(
                        clamp(touchedPoint.x, -0.5f + mMallet.radius, 0.5f - mMallet.radius),
                        clamp(touchedPoint.y, -0.8f + mMallet.radius, 0.8f - mMallet.radius),
                        mMallet.height/2f);
            }
        }

        private float clamp(float value, float min, float max) {
            return Math.min(max, Math.max(value, min));
        }

        private Geometry.Ray convertNormalized2DPointToRay(float normalizedX, float normalizedY) {
            LogUtil.log("OpenGLTouchShaderRender", "normalizedX: " + normalizedX);
            LogUtil.log("OpenGLTouchShaderRender", "normalizedY: " + normalizedY);
            final float[] nearPointNdc = {normalizedX, normalizedY, -1, 1};
            final float[] farPointNdc = {normalizedX, normalizedY, 1, 1};

            final float[] nearPointWorld = new float[4];
            final float[] farPointWorld = new float[4];

            Matrix.multiplyMV(nearPointWorld, 0, invertedViewProjectionMatrix, 0, nearPointNdc, 0);
            Matrix.multiplyMV(farPointWorld, 0, invertedViewProjectionMatrix, 0, farPointNdc, 0);

            // 把x, y, z除以这些反转的w，这样就撤销了透视除法的影响
            divideByW(nearPointWorld);
            divideByW(farPointWorld);

            Geometry.Point nearPointRay =
                    new Geometry.Point(nearPointWorld[0], nearPointWorld[1], nearPointWorld[2]);
            Geometry.Point farPointRay =
                    new Geometry.Point(farPointWorld[0], farPointWorld[1], farPointWorld[2]);

            // 返回两点之间的射线
            return new Geometry.Ray(nearPointRay,
                    Geometry.vectorBetween(nearPointRay, farPointRay));

        }

        private void divideByW(float[] vector) {
            vector[0] /= vector[3];
            vector[1] /= vector[3];
            vector[2] /= vector[3];
        }

    }

}
