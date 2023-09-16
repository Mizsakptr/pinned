module com.example.pinned {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.example.pinned to javafx.fxml;
    exports com.example.pinned;
}