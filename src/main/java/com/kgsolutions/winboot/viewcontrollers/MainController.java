package com.kgsolutions.winboot.viewcontrollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    public MainController(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
    }

    public void startApp() throws IOException {
        displayHomeView();
    }


    private void displayHomeView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-view.fxml"));
        Parent root = fxmlLoader.load();
        HomeViewController homeViewController = fxmlLoader.getController();
        homeViewController.initializeUsbListeningService();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
