package com.example.vectoreditor_;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class StrokeDecorator extends VectorDecorator{
    private static final long serialVersionUID = 1L;
    private MyColor mycolor;
    private int width;

    public StrokeDecorator(Drawable _polygon, MyColor _mycolor, int _width)
    {
        super(_polygon);
        mycolor = _mycolor;
        width = _width;

    }
    public void draw(GraphicsContext gc)
    {

        polygon.draw(gc);
        ColorParser clrParser=new ColorParser();;
        gc.setStroke(clrParser.toColor(mycolor));
        gc.setLineWidth(width);
        int n = polygon.verticeNum();
        if (n < 2) return;
        for (int j = 0; j < n; j++)
        {
            int x1 = (int)polygon.getVertice(j).getX();
            int y1 = (int)polygon.getVertice(j).getY();
            int x2 = (int)polygon.getVertice((j+1) % n).getX();
            int y2 = (int)polygon.getVertice((j+1) % n).getY();
            gc.strokeLine(x1, y1, x2, y2);
        }
        gc.setLineWidth(5);
        gc.setStroke(Color.rgb(100,100,100));
    }


}
