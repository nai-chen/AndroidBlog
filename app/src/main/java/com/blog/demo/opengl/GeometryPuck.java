package com.blog.demo.opengl;

import java.util.List;

/**
 * Created by cn on 2017/7/10.
 */

public class GeometryPuck extends Resource {
    private final static int POSITION_COMPONENT_COUNT = 3;
    public final float radius;
    public final float height;

    private final List<ResourceDataBuilder.DrawCommand> drawList;

    public GeometryPuck(ResourceDataBuilder.ResourceData data, float radius, float height) {
        super(data.vertexData);

        this.radius = radius;
        this.height = height;

        drawList = data.drawList;
    }

    public void bindData(GeometryColorProgram program) {
        bindData(program.getPositionLocation(), 0, POSITION_COMPONENT_COUNT, 0);
    }

    @Override
    public void draw() {
        for (ResourceDataBuilder.DrawCommand drawCommand : drawList) {
            drawCommand.draw();
        }
    }

}
