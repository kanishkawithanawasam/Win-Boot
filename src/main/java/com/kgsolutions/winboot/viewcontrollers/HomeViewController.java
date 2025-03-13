package com.kgsolutions.winboot.viewcontrollers;


import com.kgsolutions.winboot.controllers.MainController;
import com.kgsolutions.winboot.models.Model;
import com.kgsolutions.winboot.utils.DevicesReader;
import com.kgsolutions.winboot.utils.USBListeningService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This is responsible for handling home-view.fxml
 * @author kanishka withanawasam
 * @version 1.0
 */
public class HomeViewController {

    private MainController mainController;
    private Model model;
    private Stage stage;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label isoFileLocationLabel;


    public void init(MainController controller, Model model, Stage stage) throws IOException {
        this.mainController = controller;
        this.model = model;
        this.stage = stage;
        DevicesReader devicesReader = new DevicesReader();
        this.updateUSBList(devicesReader.getDevices());
        this.startUsbListeningService();
    }

    /**
     * This method starts USBListening service and updates the USB devices list
     */
    private void startUsbListeningService() {
        USBListeningService usbListeningService = new USBListeningService(this);
        usbListeningService.start();
    }


    public void updateUSBList(List<String> usbList) {
        ObservableList<String> devices = FXCollections.observableArrayList(usbList);
        this.comboBox.setItems(devices);
    }

    @FXML
    public void  setSelectedDevice(ActionEvent event) {
        model.setSelectedDevice(comboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void openFileSelectAction() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter for ISO files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ISO Files (*.iso)", "*.iso");
        fileChooser.getExtensionFilters().add(extFilter);

        // Open the dialog
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String[] pathComponents = file.getAbsolutePath().split("/");
            isoFileLocationLabel.setText(pathComponents[pathComponents.length - 1]);
            model.setSelectedIsoFile(file);
        } else {
            isoFileLocationLabel.setText("No file selected");
        }
    }

    @FXML
    public void startBootableDeviceCreationAction() {

    }
}
