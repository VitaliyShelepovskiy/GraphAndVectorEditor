module com.example.vectoreditor_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.vectoreditor_ to javafx.fxml;
    exports com.example.vectoreditor_;
}