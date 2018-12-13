package com.blog.demo.opengl;

import android.graphics.Color;
import android.opengl.GLES20;

/**
 * Created by cn on 2017/8/7.
 */

public class Particle extends Resource {
    private final static int POSITION_COMPONENT_COUNT = 3;
    private final static int COLOR_COMPONENT_COUNT = 3;
    private final static int VECTOR_COMPONENT_COUNT = 3;
    private final static int PARTICLE_START_TIME_COMPONENT_COUNT = 1;

    private final static int TOTAL_COMPONENT_COUNT = POSITION_COMPONENT_COUNT +
            COLOR_COMPONENT_COUNT +
            VECTOR_COMPONENT_COUNT +
            PARTICLE_START_TIME_COMPONENT_COUNT;

    private static final int STRIDE = TOTAL_COMPONENT_COUNT * VertexArray.BYTES_PER_FLOAT;

    private int maxParticleCount, currentParticleCount, nextParticle;
    private float[] particles;

    public Particle(int maxParticleCount) {
        super(new float[maxParticleCount * TOTAL_COMPONENT_COUNT]);
        this.particles = new float[maxParticleCount * TOTAL_COMPONENT_COUNT];
        this.maxParticleCount = maxParticleCount;
    }

    public void addParticle(Geometry.Point position, int color, Geometry.Vector direction,
                            float particleStartTime) {
        final int particleOffset = nextParticle * TOTAL_COMPONENT_COUNT;
        int currentOffset = particleOffset;
        nextParticle++;

        if (currentParticleCount < maxParticleCount) {
            currentParticleCount++;
        }

        // 如果到队尾，从0开始
        if (nextParticle == maxParticleCount) {
            nextParticle = 0;
        }

        // 开始位置
        particles[currentOffset++] = position.x;
        particles[currentOffset++] = position.y;
        particles[currentOffset++] = position.z;

        // 开始颜色
        particles[currentOffset++] = Color.red(color) / 255f;
        particles[currentOffset++] = Color.green(color) / 255f;
        particles[currentOffset++] = Color.blue(color) / 255f;

        // 移动位置
        particles[currentOffset++] = direction.x;
        particles[currentOffset++] = direction.y;
        particles[currentOffset++] = direction.z;

        // 当前创建时间
        particles[currentOffset++] = particleStartTime;

        // 修改顶点数据
        mVertextArray.updateBuffer(particles, particleOffset, TOTAL_COMPONENT_COUNT);
    }

    public void bindData(ParticleProgram program) {
        int dataOffset = 0;
        bindData(program.getPositionLocation(), dataOffset, POSITION_COMPONENT_COUNT, STRIDE);

        dataOffset += POSITION_COMPONENT_COUNT;
        bindData(program.getColorLocation(), dataOffset, COLOR_COMPONENT_COUNT, STRIDE);

        dataOffset += COLOR_COMPONENT_COUNT;
        bindData(program.getDirectionVectorLocation(), dataOffset, VECTOR_COMPONENT_COUNT, STRIDE);

        dataOffset += VECTOR_COMPONENT_COUNT;
        bindData(program.getParticleStartTimeLocation(), dataOffset,
                PARTICLE_START_TIME_COMPONENT_COUNT, STRIDE);
    }

    @Override
    public void draw() {
        super.draw();
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, currentParticleCount);
    }

}
