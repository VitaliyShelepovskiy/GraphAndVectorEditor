package com.example.grapheditor;

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class PixelEditorController {

    @FXML
    Canvas canvas;
    @FXML
    ColorPicker colorPicker;
    GraphicsContext gc;
    @FXML
    TextField sizeField;
    @FXML
    TextField sizeField2;
    String figChoice;
    Factory pixelFactory;
    @FXML
    public void initialize()
    {
        figChoice = "circle";
        pixelFactory = new Factory();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }
    @FXML
    protected void CircleButtonClick()
    {
        figChoice = "circle";
    }
    @FXML
    protected void SquareButtonClick()
    {
        figChoice = "square";
    }

    @FXML
    private void canvasDragged(MouseEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int size1 = Integer.parseInt(sizeField.getText());
        int size2 = Integer.parseInt(sizeField2.getText());
        Color color = colorPicker.getValue();
        Shape currPixel = pixelFactory.createShape(figChoice, x,y, size1,size2, color);

        currPixel.draw(gc);
    }

    @FXML
    private void SaveBtnClk() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Сохранить изображение");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JPEG", "*.jpg", "*.jpeg"));
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG", "*.png"));

        File file = chooser.showSaveDialog(canvas.getScene().getWindow());
        if (file == null) return;

        String format = file.getName().toLowerCase().endsWith(".png") ? "png" : "jpg";

        try {
            saveCanvasToFile(file, format);
            showInfo("Сохранено", "Файл успешно сохранен:\n" + file.getAbsolutePath());
        } catch (IOException e) {
            showError("Ошибка сохранения", e.getMessage());
        }
    }
    private void saveCanvasToFile(File file, String format) throws IOException {
        // 1. Снимок холста
        SnapshotParameters params = new SnapshotParameters();
        if ("jpg".equals(format)) {
            params.setFill(Color.WHITE); // JPG не поддерживает прозрачность
        }

        WritableImage snapshot = canvas.snapshot(params, null);

        // 2. Конвертация WritableImage -> BufferedImage (без SwingFXUtils)
        BufferedImage bufferedImage = toBufferedImage(snapshot, "jpg".equals(format));

        // 3. Запись на диск
        boolean ok = ImageIO.write(bufferedImage, format, file);
        if (!ok) {
            throw new IOException("Формат " + format + " не поддерживается ImageIO");
        }
    }
    private BufferedImage toBufferedImage(WritableImage image, boolean forceOpaque) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // TYPE_INT_RGB для JPG (без альфы), TYPE_INT_ARGB для PNG (с альфой)
        int type = forceOpaque ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        BufferedImage bufferedImage = new BufferedImage(width, height, type);

        PixelReader reader = image.getPixelReader();
        if (reader == null) {
            throw new IllegalStateException("Не удалось получить PixelReader у снимка");
        }

        int[] pixels = new int[width * height];
        reader.getPixels(
                0, 0, width, height,
                javafx.scene.image.PixelFormat.getIntArgbInstance(),
                pixels, 0, width);

        if (forceOpaque) {
            // Если фон не был задан в SnapshotParameters — здесь можно
            // "сплющить" прозрачность на белый фон:
            for (int i = 0; i < pixels.length; i++) {
                int argb = pixels[i];
                int a = (argb >>> 24) & 0xFF;
                if (a < 255) {
                    int r = (argb >> 16) & 0xFF;
                    int g = (argb >> 8) & 0xFF;
                    int b = argb & 0xFF;
                    // смешивание с белым
                    r = (r * a + 255 * (255 - a)) / 255;
                    g = (g * a + 255 * (255 - a)) / 255;
                    b = (b * a + 255 * (255 - a)) / 255;
                    pixels[i] = (255 << 24) | (r << 16) | (g << 8) | b;
                }
            }
        }

        bufferedImage.setRGB(0, 0, width, height, pixels, 0, width);
        return bufferedImage;
    }

    // ===== ЗАГРУЗКА =====

    @FXML
    private void OpenBtnClk() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Открыть изображение");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*"));

        File file = chooser.showOpenDialog(canvas.getScene().getWindow());
        if (file == null) return;

        try {
            loadImageToCanvas(file);
            showInfo("Загружено", "Изображение загружено:\n" + file.getAbsolutePath());
        } catch (Exception e) {
            showError("Ошибка загрузки", e.getMessage());
        }
    }

    private void loadImageToCanvas(File file) {
        Image image = new Image(file.toURI().toString());

        if (image.isError()) {
            throw new IllegalStateException("Не удалось прочитать изображение");
        }

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Масштабируем изображение под размер холста
        gc.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void showInfo(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showError(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    @FXML
    protected void DeleteButtonClick()
    {
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }
}
