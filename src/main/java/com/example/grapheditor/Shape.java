package com.example.grapheditor;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {
    int x,y;
    Color color;
    public abstract void draw(GraphicsContext gc);
}
