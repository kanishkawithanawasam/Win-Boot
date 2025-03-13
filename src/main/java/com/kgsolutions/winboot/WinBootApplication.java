package com.kgsolutions.winboot;

import com.kgsolutions.winboot.controllers.MainController;
import com.kgsolutions.winboot.models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class WinBootApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        MainController mainController = new MainController(stage,model);


        mainController.startApp();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
