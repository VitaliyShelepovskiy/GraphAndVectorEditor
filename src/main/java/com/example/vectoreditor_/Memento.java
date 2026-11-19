package com.example.vectoreditor_;

import java.io.*;

public class Memento
{
    private final int polygonIndex;
    private final Drawable polygon;

    public Memento(int polygonIndex, Drawable polygon)
    {
        this.polygonIndex = polygonIndex;
        this.polygon = deepCopy(polygon);
    }

    public int getPolygonIndex() { return polygonIndex; }
    public Drawable getPolygon() { return polygon; }

    @SuppressWarnings("unchecked")
    private static <T extends Serializable> T deepCopy(T obj) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
            try (ObjectInputStream ois =
                         new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()))) {
                return (T) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
