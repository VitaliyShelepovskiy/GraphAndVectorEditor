package com.example.grapheditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    int radius;
    Color color;
    public Circle(int _x, int _y, int _radius, Color _color)
    {
        x = _x;
        y = _y;
        radius = _radius;
        color = _color;
    }
    public void draw(GraphicsContext gc)
    {
        gc.setFill(color);
        gc.fillOval(x,y,radius,radius);
    }
}
