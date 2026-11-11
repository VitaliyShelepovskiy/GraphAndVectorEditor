package com.example.vectoreditor_;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VectorController {
    private static final long serialVersionUID = 1L;
    Drawing drawing;
    @FXML
    CheckBox PointModeCheckBox;
    @FXML
    CheckBox NewShapeCheckBox;
    @FXML
    CheckBox ChooseModeCheckBox;
    @FXML
    CheckBox MoveModeCheckBox;
    @FXML
    TextField strokeWidthFld;
    @FXML
    TextField txtField;
    @FXML
    ColorPicker FillColorPicker;
    @FXML
    Canvas canvas;
    @FXML
    ColorPicker StrokeColorPicker;
    double mouse_start_x;
    double mouse_start_y;
    double mouse_end_x;
    double mouse_end_y;
    boolean isPointMode;
    boolean isChooseMode;
    boolean isMoveMode;
    int curPolygonIndex;
    List<Drawable> polygons;
    GraphicsContext gc;
    @FXML
    public void initialize()
    {

        isPointMode=true;
        PointModeCheckBox.setSelected(isPointMode);
        polygons = new ArrayList<>();

        polygons.add(new Polygon());
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(5);
        gc.setStroke(Color.rgb(100,100,100));
    }
    @FXML
    protected void NewShapeBtnClick()
    {

        polygons.add(new Polygon());
        NewShapeCheckBox.setSelected(true);
    }
    @FXML
    protected void ChooseBtnClick()
    {
        isChooseMode = true;
        isPointMode = false;
        isMoveMode = false;
        PointModeCheckBox.setSelected(isPointMode);
        ChooseModeCheckBox.setSelected(isChooseMode);
        MoveModeCheckBox.setSelected(isMoveMode);
    }
    @FXML
    protected void PointBtnClick()
    {
        isPointMode = true;
        isChooseMode= false;
        isMoveMode = false;
        PointModeCheckBox.setSelected(isPointMode);
        ChooseModeCheckBox.setSelected(isChooseMode);
        MoveModeCheckBox.setSelected(isMoveMode);
    }
    @FXML
    protected void FillBtnClick()
    {
        ColorParser clrParser=new ColorParser();
        polygons.set(curPolygonIndex,
                        new FillDecorator(polygons.get(curPolygonIndex),
                                clrParser.toMyColor(FillColorPicker.getValue())));
        polygons.get(curPolygonIndex).draw(gc);
    }
    @FXML
    protected void StrokeBtnClick()
    {
        ColorParser clrParser=new ColorParser();
        int width = Integer.parseInt(strokeWidthFld.getText());
        polygons.set(curPolygonIndex,
                new StrokeDecorator(polygons.get(curPolygonIndex),
                        clrParser.toMyColor(StrokeColorPicker.getValue()),
                        width));
        polygons.get(curPolygonIndex).draw(gc);
    }
    @FXML
    protected void ShadeBtnClk()
    {
        polygons.set(curPolygonIndex,
                new ShadowDecorator(polygons.get(curPolygonIndex)));
        polygons.get(curPolygonIndex).draw(gc);
    }
    @FXML
    protected void MoveBtnClk()
    {
        isChooseMode = false;
        isPointMode = false;
        isMoveMode = true;
        PointModeCheckBox.setSelected(isPointMode);
        ChooseModeCheckBox.setSelected(isChooseMode);
        MoveModeCheckBox.setSelected(isMoveMode);
    }

    @FXML
    protected void DeleteBtnClk()
    {
        polygons.remove(curPolygonIndex);
        redraw();
    }
    @FXML
    private void canvasPressed(MouseEvent event)
    {
        if (isPointMode)
        {
            int polygonLength = polygons.size();
            int x = (int) event.getX();
            int y = (int) event.getY();
            polygons.get(polygonLength-1).addVertices(x,y);
            redraw();
            NewShapeCheckBox.setSelected(false);
        }
        if ((isChooseMode)||(isMoveMode))
        {
            int px = (int) event.getX();
            int py = (int) event.getY();
            for (int i = polygons.size() - 1; i >= 0; i--)
            {
                if (polygons.get(i).contains(px, py))
                {
                    txtField.setText(polygons.get(i).verticeNum() + "");
                    curPolygonIndex = i;
                    break;
                }
            }
            mouse_start_x = event.getX();
            mouse_start_y = event.getY();
        }
    }
    @FXML
    private void canvasDragged(MouseEvent event)
    {
        if (isMoveMode)
        {
            mouse_end_x = event.getX();
            mouse_end_y = event.getY();
            double dx = mouse_end_x - mouse_start_x;
            double dy = mouse_end_y - mouse_start_y;
            polygons.get(curPolygonIndex).move(dx,dy);
            redraw();
            mouse_start_x = mouse_end_x;
            mouse_start_y = mouse_end_y;
        }

    }

    @FXML
    public void saveToFile()
    {

        drawing = new Drawing(polygons);

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Сохранить данные");
        chooser.setInitialFileName("data.ser");
        chooser.setInitialDirectory(new File("C:\\Users\\kniga\\OneDrive\\Desktop"));
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Сериализованные файлы (*.ser)", "*.ser"));

        Window owner = StrokeColorPicker.getScene().getWindow();
        File file = chooser.showSaveDialog(owner);
        if (file == null) return;


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
        {
            oos.writeObject(drawing);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void loadFromFile()
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Загрузить данные");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Сериализованные файлы (*.ser)", "*.ser"));

        Window owner = StrokeColorPicker.getScene().getWindow();
        File file = chooser.showOpenDialog(owner);
        if (file == null) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            drawing = (Drawing) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }


        polygons = new ArrayList<>();
        for (int i = 0; i < drawing.getSize(); i++) {
            polygons.add(drawing.getPolygon(i));
        }
        redraw();

    }
    public void redraw()
    {
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        int polygonsLength = polygons.size();
        int i;
        for (i=0;i<polygonsLength;i++)
        {
            if (polygons.get(i).verticeNum()>1)
            {
                polygons.get(i).draw(gc);
            }

        }

    }


}


