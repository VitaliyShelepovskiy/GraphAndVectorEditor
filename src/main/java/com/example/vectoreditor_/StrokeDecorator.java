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
        ColorParser clrParser=new ColorParser();;
        gc.setStroke(clrParser.toColor(mycolor));
        gc.setLineWidth(width);
        polygon.draw(gc);
    }


}
