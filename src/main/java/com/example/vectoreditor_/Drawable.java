package com.example.vectoreditor_;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.Point;
import java.io.Serializable;

public interface Drawable extends Serializable {
    void draw(GraphicsContext gc);

    int verticeNum();
    void addVertices(int x, int y);
    Point getVertice(int i);
    boolean contains(int px, int py);
    void move(double dx, double dy);
}