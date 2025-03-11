package com.kgsolutions.winboot.viewcontrollers;


import com.kgsolutions.winboot.utils.USBListeningService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is responsible for handling home-view.fxml
 * @author kanishka withanawasam
 * @version 1.0
 */
public class HomeViewController {

    @FXML
    private ComboBox<String> comboBox;

    /**
     * This method starts USBListening service and updates the USB devices list
     */
    public void startUsbListeningService() {
        USBListeningService usbListeningService = new USBListeningService(this);
        usbListeningService.start();
    }


    public void updateUSBList(){
        ObservableList<String> devices = FXCollections.observableArrayList();
        try{
            Process process = new ProcessBuilder("ls","/Volumes").start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                devices.add(line);
            }
            bufferedReader.close();
            comboBox.setItems(devices);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
