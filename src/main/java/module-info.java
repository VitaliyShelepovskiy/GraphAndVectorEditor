module com.example.grapheditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens com.example.grapheditor to javafx.fxml;
    exports com.example.grapheditor;
}