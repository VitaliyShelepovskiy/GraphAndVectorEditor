package com.example.grapheditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Star extends Shape{
    int rays, size;
    public Star(int _x, int _y, int _rays, int _size)
    {
        x = _x;
        y = _y;
        rays = _rays;
        size = _size;
    }
    public void draw(GraphicsContext gc)
    {
        if (rays < 3) rays = 3;

    }
}
