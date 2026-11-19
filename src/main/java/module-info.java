
module com.example.grapheditor {



    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.grapheditor to javafx.fxml;
    exports com.example.grapheditor;




    opens com.example.vectoreditor_ to javafx.fxml;
    exports com.example.vectoreditor_;

}