package com.blog.demo.opengl;

/**
 * Created by cn on 2017/7/10.
 */

public class Resource {

    protected VertexArray mVertextArray;

    public Resource(float[] vertexData) {
        mVertextArray = new VertexArray(vertexData);
    }

    public void bindData(int attributeLocation, int dataOffset,
        int componentCount, int stride) {
        mVertextArray.setVertexAttribPointer(attributeLocation,
                dataOffset, componentCount, stride);
    }

    public void draw() {
    }

}
