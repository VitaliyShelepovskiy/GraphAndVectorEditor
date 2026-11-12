package com.example.vectoreditor_;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.Point;

public class FillDecorator extends VectorDecorator {
    private static final long serialVersionUID = 1L;
    private MyColor color;

    public FillDecorator(Drawable _polygon, MyColor _color) {

        super(_polygon);
        color = _color;

    }

    @Override
    public void draw(GraphicsContext gc) {

        polygon.draw(gc);
        double[] vertices_x = new double[verticeNum()];
        double[] vertices_y = new double[verticeNum()];
        for(int i=0;i<verticeNum();i++)
        {
            vertices_x[i]=(int)polygon.getVertice(i).getX();
            vertices_y[i]=(int)polygon.getVertice(i).getY();
        }
        ColorParser clrParser=new ColorParser();;
        gc.setFill(clrParser.toColor(color));
        gc.fillPolygon(vertices_x,vertices_y,verticeNum());

    }

    @Override public int verticeNum() { return polygon.verticeNum(); }
    @Override public void addVertices(int x, int y) { polygon.addVertices(x, y); }
    @Override public Point getVertice(int i) { return polygon.getVertice(i); }

}