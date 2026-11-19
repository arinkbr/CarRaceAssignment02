module com.mycompany.carrace {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.mycompany.carrace to javafx.fxml;
    exports com.mycompany.carrace;
}
