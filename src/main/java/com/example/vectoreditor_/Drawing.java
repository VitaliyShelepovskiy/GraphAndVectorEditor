package com.example.vectoreditor_;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Drawing implements Serializable
{
    private static final long serialVersionUID = 1L;
    List<Drawable> polygons;
    public Drawing(List<Drawable> pols)
    {
        polygons = new ArrayList<>();
        for (int i=0;i<pols.size();i++)
        {
            polygons.add(pols.get(i));
        }
    }
    public Drawable getPolygon(int i)
    {
        return polygons.get(i);
    }
    public int getSize()
    {
        return polygons.size();
    }
}
