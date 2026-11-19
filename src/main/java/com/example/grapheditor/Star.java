package com.example.grapheditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Path2D;

public class Star extends Shape{
    int rays, size;
    public Star(int _x, int _y, int _rays, int _size, Color _color)
    {
        x = _x;
        y = _y;
        rays = _rays;
        size = _size;
        color = _color;
    }
    public void draw(GraphicsContext gc)
    {
        if (rays < 3) rays = 3;

        double outerR = size*0.45;
        double innerR = outerR*0.382;
        double[] xPoints = new double[rays*2];
        double[] yPoints = new double[rays*2];
        for (int i = 0; i < rays*2; i++)
        {
            double r = (i%2 == 0) ? outerR : innerR;
            double angle = Math.PI / rays * i - Math.PI/2;
            xPoints[i] = x + r * Math.cos(angle);
            yPoints[i] = y + r * Math.sin(angle);
        }
        gc.setFill(color);
        gc.fillPolygon(xPoints, yPoints, rays*2);
    }
}
