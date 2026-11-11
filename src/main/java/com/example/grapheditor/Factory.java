package com.example.grapheditor;
import javafx.scene.paint.Color;
public class Factory
{
    public Factory()
    {}
    public Shape createShape(String shapeStr, int x, int y, int a, Color color)
    {
        switch(shapeStr)
        {
            case "circle":
                return new Circle(x,y,a,color);
            case "square":
                return new Square(x,y, a, color);
            default:
                return null;
        }

    }
}
