package com.example.grapheditor;

import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {
    int x,y;
    public abstract void draw(GraphicsContext gc);
}
