package com.blog.demo.opengl;

import android.opengl.GLES20;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/7/11.
 */

public class ResourceDataBuilder {
    private static final int FLOATS_PER_VERTEX = 3;
    private final float[] mVertexData;
    private int mOffset;

    private final List<DrawCommand> drawList = new ArrayList<>();

    private ResourceDataBuilder(int size) {
        mVertexData = new float[size * FLOATS_PER_VERTEX];
    }

    private static int sizeOfCircleInVertices(int numPoints) {
        return 1 + (numPoints + 1);
    }

    private static int sizeOfCylinderInVertices(int numPoints) {
        return (numPoints + 1) * 2;
    }

    public static ResourceData createPuck(Geometry.Cylinder cylinder, int numPoints) {
        int size = sizeOfCircleInVertices(numPoints) + sizeOfCylinderInVertices(numPoints);

        ResourceDataBuilder builder = new ResourceDataBuilder(size);
        // 顶部圆形
        Geometry.Circle topCircle = new Geometry.Circle(
                cylinder.center.translateZ(cylinder.height/2), cylinder.radius);
        builder.addCircle(topCircle, numPoints);
        builder.addCylinder(cylinder, numPoints);

        return builder.build();
    }

    public static ResourceData createMallet(Geometry.Cylinder cylinder, int numPoints) {
        int size = sizeOfCircleInVertices(numPoints) * 2
                + sizeOfCylinderInVertices(numPoints) * 2;

        // 底部圆柱体顶部圆形
        Geometry.Circle baseTopCircle = new Geometry.Circle(
                cylinder.center.translateZ(-cylinder.height/4), cylinder.radius);
        // 底部圆柱体
        Geometry.Cylinder baseCylinder = new Geometry.Cylinder(
                baseTopCircle.center.translateZ(-cylinder.height/8),
                cylinder.radius, cylinder.height/4);

        ResourceDataBuilder builder = new ResourceDataBuilder(size);
        builder.addCircle(baseTopCircle, numPoints);
        builder.addCylinder(baseCylinder, numPoints);

        // 手柄圆柱体顶部圆形
        Geometry.Circle handleTopCircle = new Geometry.Circle(
                cylinder.center.translateZ(cylinder.height/2), cylinder.radius/3);
        // 手柄圆柱体
        Geometry.Cylinder handleCylinder = new Geometry.Cylinder(
                handleTopCircle.center.translateZ(-cylinder.height*0.375f),
                cylinder.radius/3, cylinder.height*0.75f);
        builder.addCircle(handleTopCircle, numPoints);
        builder.addCylinder(handleCylinder, numPoints);

        return builder.build();
    }

    private void addCircle(Geometry.Circle circle, int numPoints) {
        final int start = mOffset / FLOATS_PER_VERTEX;
        final int size = sizeOfCircleInVertices(numPoints);

        mVertexData[mOffset++] = circle.center.x;
        mVertexData[mOffset++] = circle.center.y;
        mVertexData[mOffset++] = circle.center.z;

        for (int i = 0; i <= numPoints; i++) {
            float angleInRadius = ((float)i / (float)numPoints) * ((float) Math.PI * 2f);

            mVertexData[mOffset++] = circle.center.x
                    + circle.radius * (float) Math.cos(angleInRadius);
            mVertexData[mOffset++] = circle.center.y
                    + circle.radius *(float) Math.sin(angleInRadius);
            mVertexData[mOffset++] = circle.center.z;
        }

        drawList.add(new DrawCommand() {
            @Override
            public void draw() {
                GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, start, size);
            }
        });
    }

    private void addCylinder(Geometry.Cylinder cylinder, int numPoints) {
        final int start = mOffset / FLOATS_PER_VERTEX;
        final int size = sizeOfCylinderInVertices(numPoints);

        for (int i = 0; i <= numPoints; i++) {
            float angleInRadius = ((float)i / (float)numPoints) * ((float) Math.PI * 2f);
            float x = cylinder.center.x + cylinder.radius * (float) Math.cos(angleInRadius);
            float y = cylinder.center.y + cylinder.radius * (float) Math.sin(angleInRadius);

            mVertexData[mOffset++] = x;
            mVertexData[mOffset++] = y;
            mVertexData[mOffset++] = cylinder.center.z + cylinder.height / 2;

            mVertexData[mOffset++] = x;
            mVertexData[mOffset++] = y;
            mVertexData[mOffset++] = cylinder.center.z - cylinder.height / 2;
        }

        drawList.add(new DrawCommand() {
            @Override
            public void draw() {
                GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, start, size);
            }
        });
    }

    private ResourceData build() {
        return new ResourceData(mVertexData, drawList);
    }

    static interface DrawCommand {
        void draw();
    }

    static class ResourceData {
        public final float[] vertexData;
        public final List<DrawCommand> drawList;

        ResourceData(float[] vertexData, List<DrawCommand> drawList) {
            this.vertexData = vertexData;
            this.drawList = drawList;
        }
    }

}
