package com.example.grapheditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    int size1,size2;
    public Square(int _x, int _y, int _size1,int _size2, Color _color)
    {
        x = _x;
        y = _y;
        size1 = _size1;
        size2 = _size2;
        color = _color;
    }
    public void draw(GraphicsContext gc)
    {
        gc.setFill(color);
        gc.fillRect(x,y,size1,size2);
    }
}