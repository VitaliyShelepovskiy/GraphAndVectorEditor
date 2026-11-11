package com.example.vectoreditor_;

import javafx.scene.paint.Color;

public class ColorParser
{
    public MyColor toMyColor(Color clr)
    {
        int r = (int)(clr.getRed()*255);
        int g = (int)(clr.getGreen()*255);
        int b = (int)(clr.getBlue()*255);
        return new MyColor(r,g,b);
    }
    public Color toColor(MyColor clr)
    {
        int r = clr.get_r();
        int g = clr.get_g();
        int b = clr.get_b();
        return Color.rgb(r,g,b);
    }
}