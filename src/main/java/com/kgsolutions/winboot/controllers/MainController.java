package com.kgsolutions.winboot.controllers;

import com.kgsolutions.winboot.models.Model;
import com.kgsolutions.winboot.viewcontrollers.HomeViewController;
import com.kgsolutions.winboot.viewcontrollers.ProcessViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Model model;


    public MainController(Stage stage, Model model) {
        this.model = model;
        this.stage = stage;
        stage.setResizable(false);
    }

    public void startApp() throws IOException {
        displayHomeView();
    }

    private void displayHomeView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/home-view.fxml"));
        Parent root = fxmlLoader.load();
        HomeViewController homeViewController = fxmlLoader.getController();
        homeViewController.init(this,model,stage);
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void startBootableDeviceCreation() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/process-view.fxml"));
        Parent root = fxmlLoader.load();
        ProcessViewController processViewController = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        stage.show();

    }

   private void runBootableDeviceCreation(ProcessViewController processViewController) throws IOException, InterruptedException {
        // Update the screen to run format device progress
        processViewController.setFormatProgressIndicatorStatus(ProgressIndicator.INDETERMINATE_PROGRESS);

        // Formatting process
        if (model.getSelectedIsoFile() != null) {
            Process process = new ProcessBuilder("diskutil","eraseDisk","MS-DOS",
                    "\"Windows Bootable Drive\"", "MBR","/Volumes/"+model.getSelectedDevice()).start();
            process.waitFor();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("You have not selected a removable device");
            displayHomeView();
        }

        // Copying files
       if (model.getSelectedIsoFile() != null) {

           // Custom mount location for the iso file.
           String mountLocation = "/Volumes/WindowsISO";

           // Preparing custom mount location
           Process process = new ProcessBuilder("mkdir","-p",mountLocation).start();
           process.waitFor();

           // Mounting the ISO file to custom mount location
           process = new ProcessBuilder("hdiutil","attach","-mountpount",mountLocation,
                   model.getSelectedIsoFile().getAbsolutePath()).start();
           process.waitFor();

            //

       }
   }



}
