package com.kgsolutions.winboot.viewcontrollers;


import com.kgsolutions.winboot.utils.DevicesReader;
import com.kgsolutions.winboot.utils.USBListeningService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.List;

/**
 * This is responsible for handling home-view.fxml
 * @author kanishka withanawasam
 * @version 1.0
 */
public class HomeViewController {

    @FXML
    private ComboBox<String> comboBox;

    public void initializeUsbListeningService() throws IOException {
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
}
