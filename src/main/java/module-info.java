module com.kgsolutions.winboot {
    requires javafx.fxml;
    requires java.desktop;
    requires com.gluonhq.charm.glisten;

    // Open the package that contains the FXML controller to javafx.fxml
    opens com.kgsolutions.winboot.viewcontrollers to javafx.fxml;

    // Also open the main package if needed
    opens com.kgsolutions.winboot to javafx.fxml;

    // Export the main package
    exports com.kgsolutions.winboot;
    exports com.kgsolutions.winboot.viewcontrollers;
    exports com.kgsolutions.winboot.controllers;
    opens com.kgsolutions.winboot.controllers to javafx.fxml;
}
