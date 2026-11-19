package com.example.vectoreditor_;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PNGScreen
{
    public static void ExportCanvasToPNG(Canvas canvas) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Сохранить данные");
        chooser.setInitialFileName("image.png");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Изображение PNG", "*.png" , "*.png"));
        Window owner = canvas.getScene().getWindow();
        File file = chooser.showSaveDialog(owner);

        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        writableImage = canvas.snapshot(params, new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight()));

        ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null),"png",file);
    }
}
