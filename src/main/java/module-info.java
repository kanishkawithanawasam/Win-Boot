module com.kgsolutions.winboot {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kgsolutions.winboot to javafx.fxml;
    exports com.kgsolutions.winboot;
}