package com.example.grapheditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    int a;
    Color color;
    public Square(int _x, int _y, int _a, Color _color)
    {
        x = _x;
        y = _y;
        a = _a;
        color = _color;
    }
    public void draw(GraphicsContext gc)
    {
        gc.setFill(color);
        gc.fillRect(x,y,a,a);
    }
}