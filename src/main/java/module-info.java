module com.example.cl {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cl to javafx.fxml;
    exports com.example.cl;
}