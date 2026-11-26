module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.swing;
    requires java.desktop;

    opens com.example.grapheditor to javafx.fxml;
    opens com.example.vectoreditor_ to javafx.fxml; // если главный класс здесь
    exports com.example.grapheditor;
    exports com.example.vectoreditor_;
}