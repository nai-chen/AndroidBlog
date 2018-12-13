package com.blog.demo.opengl;

import android.opengl.Matrix;

import java.util.Random;

/**
 * Created by cn on 2017/8/7.
 */

public class ParticleShooter {
    private final Geometry.Point position;
    private final Geometry.Vector direction;
    private final int color;

    private final float angleVariance;
    private final float speendVariance;

    private final Random random = new Random();
    private float[] rotationMatrix = new float[16];
    private float[] directionVector = new float[4];
    private float[] resultVector = new float[4];

    ParticleShooter(Geometry.Point position, Geometry.Vector direction, int color) {
        this.position = position;
        this.direction = direction;
        this.color = color;

        angleVariance = 0;
        speendVariance = 0;
    }

    ParticleShooter(Geometry.Point position, Geometry.Vector direction, int color,
                           float angleVariance, float speendVariance) {
        this.position = position;
        this.direction = direction;
        this.color = color;

        this.angleVariance = angleVariance;
        this.speendVariance = speendVariance;

        directionVector[0] = direction.x;
        directionVector[1] = direction.y;
        directionVector[2] = direction.z;
    }

    public void addParticles(Particle particle, float currentTime, int count) {
        for (int i = 0; i < count; i++) {
//            particle.addParticle(position, color, direction, currentTime);

            // 创建一个旋转矩阵，用angleVariance的一个随机量改变发射角度
            Matrix.setRotateEulerM(rotationMatrix, 0,
                    (random.nextFloat() - 0.5f) * angleVariance,
                    (random.nextFloat() - 0.5f) * angleVariance,
                    (random.nextFloat() - 0.5f) * angleVariance);
            Matrix.multiplyMV(resultVector, 0, rotationMatrix, 0, directionVector, 0);

            // 调整发射速度
            float speedAdjustment = 1f + random.nextFloat() * speendVariance;
            Geometry.Vector thisDirection = new Geometry.Vector(
                    resultVector[0] * speedAdjustment,
                    resultVector[1] * speedAdjustment,
                    resultVector[2] * speedAdjustment);
            particle.addParticle(position, color, thisDirection, currentTime);
        }
    }

}
