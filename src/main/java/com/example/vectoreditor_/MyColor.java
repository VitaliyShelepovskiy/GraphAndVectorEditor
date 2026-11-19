package com.example.vectoreditor_;
import java.io.Serializable;
public class MyColor implements Serializable
{
    private static final long serialVersionUID = 1L;
    int r;
    int g;
    int b;
    public int get_r(){return r;}
    public int get_g(){return g;}
    public int get_b(){return b;}
    public void set_r(int _r){r=_r;}
    public void set_g(int _g){g=_g;}
    public void set_b(int _b){b=_b;}
    public MyColor(int _r, int _g, int _b)
    {
        r = _r;
        g = _g;
        b = _b;
    }
}