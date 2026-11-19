package com.example.vectoreditor_;

import javafx.scene.paint.Color;

import java.awt.*;

public abstract class VectorDecorator implements Drawable
{
    protected Drawable polygon;

    public VectorDecorator(Drawable _polygon)
    {
        polygon=_polygon;
    }
    @Override public boolean contains(int px, int py) {return polygon.contains(px, py);}
    @Override public int verticeNum() { return polygon.verticeNum(); }
    @Override public void addVertices(int x, int y) { polygon.addVertices(x, y); }
    @Override public Point getVertice(int i){return polygon.getVertice(i);}
    @Override public void move(double dx, double dy){polygon.move(dx,dy);};
}