package com.kgsolutions.winboot;

import com.kgsolutions.winboot.viewcontrollers.HomeViewController;
import com.kgsolutions.winboot.viewcontrollers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class WinBootApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MainController mainController = new MainController(stage);
        mainController.startApp();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
