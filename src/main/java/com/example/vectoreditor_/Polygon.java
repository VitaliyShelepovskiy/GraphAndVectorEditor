package com.example.vectoreditor_;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.Point;
import java.util.*;

public class Polygon implements Drawable
{
    List<Point> vertices;

    public Polygon()
    {
        vertices = new ArrayList<>();

    }
    @Override
    public boolean contains(int px, int py)
    {
        int n = vertices.size();
        if (n<3) return false;
        boolean IsInside = false;
        for(int i=0, j=n-1; i < n; j=i++)
        {
            double xi = vertices.get(i).getX();
            double yi = vertices.get(i).getY();
            double xj = vertices.get(j).getX();
            double yj = vertices.get(j).getY();
            boolean crosses = (yi > py) != (yj > py);
            if (crosses)
            {
                double xIntersect = (xj-xi)*(py-yi)/(yj-yi)+xi;
                if (px < xIntersect)
                {
                    IsInside = !IsInside;
                }
            }
        }
        return IsInside;
    }


    public void addVertices(int x, int y)
    {
        vertices.add(new Point(x,y));
    }
    public Point getVertice(int i)
    {
        return vertices.get(i);
    }
    public int verticeNum()
    {
        return vertices.size();
    }
    public void draw(GraphicsContext gc)
    {
        int n = vertices.size();
        if (n < 2) return;
        for (int j = 0; j < n; j++)
        {
            int x1 = (int)vertices.get(j).getX();
            int y1 = (int)vertices.get(j).getY();
            int x2 = (int)vertices.get((j+1) % n).getX();
            int y2 = (int)vertices.get((j+1) % n).getY();
            gc.strokeLine(x1, y1, x2, y2);
        }

    }
    public void move(double dx, double dy)
    {
        for (int i=0; i<verticeNum();i++)
        {
            double cur_x = vertices.get(i).getX();
            double cur_y = vertices.get(i).getY();
            vertices.get(i).setLocation(cur_x+dx,cur_y+dy);
        }
    }

}
