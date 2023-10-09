package com.isep.discoverprais.wheel;

import android.graphics.Matrix;
import android.graphics.Point;

class Circle {

    private float cx;

    private float cy;

    private float radius;

    private Matrix matrix;

    public Circle() {
        matrix = new Matrix();
    }

    public Circle(float width, float height) {
        this();

        cx = width / 2f;
        cy = height / 2f;
        radius = Math.min(cx, cy);
    }

    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }

    public float getRadius() {
        return radius;
    }

    public boolean contains(float x, float y) {
        x = cx - x;
        y = cy - y;
        return x * x + y * y <= radius * radius;
    }

    public Point rotate(float angle, float x, float y) {
        matrix.setRotate(angle, cx, cy);
        float[] pts = new float[2];
        pts[0] = x;
        pts[1] = y;
        matrix.mapPoints(pts);
        return new Point((int) pts[0], (int) pts[1]);
    }
}
