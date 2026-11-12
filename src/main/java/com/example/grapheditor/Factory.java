package com.example.grapheditor;
import javafx.scene.paint.Color;
public class Factory
{
    public Factory()
    {}
    public Shape createShape(String shapeStr, int x, int y, int size1,int size2, Color color)
    {
        switch(shapeStr)
        {
            case "circle":
                return new Circle(x,y,size1,color);
            case "square":
                return new Square(x,y, size1, size2, color);
            default:
                return null;
        }

    }
}
