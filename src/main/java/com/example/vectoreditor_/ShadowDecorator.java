package com.example.vectoreditor_;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class ShadowDecorator extends VectorDecorator
{
    private static final long serialVersionUID = 1L;
    public ShadowDecorator(Drawable _polygon)
    {
        super(_polygon);
    }
    public void draw(GraphicsContext gc)
    {
        Color clr = Color.RED;
        double radius = 2;
        double offsetX = 15;
        double offsetY = 15;
        gc.setFill(clr);
        int layers = (int) Math.max(1, radius*2);
        for (int i=0; i < layers; i++)
        {
            gc.setGlobalAlpha(0.25);
            double angle = 2* Math.PI * i / layers;
            double r = radius * Math.random();
            double dx = offsetX + Math.cos(angle) * r;
            double dy = offsetY + Math.sin(angle)* r;

            double[] vertices_x = new double[verticeNum()];
            double[] vertices_y = new double[verticeNum()];
            for(int j=0;j<verticeNum();j++)
            {
                vertices_x[j]= polygon.getVertice(j).getX()+dx;
                vertices_y[j]= polygon.getVertice(j).getY()+dy;
            }
            gc.fillPolygon(vertices_x,vertices_y,verticeNum());
        }
        gc.setGlobalAlpha(1.0);
        polygon.draw(gc);
    }
}
