package com.kgsolutions.winboot.models;


import javafx.collections.ObservableList;

import java.io.File;

/**
 * This class acts as the main data model of the system
 * @author kanishka withanawasam
 * @version 1.0
 */
public class Model {
    private ObservableList<String> devices;
    private File selectedIsoFile;
    private String selectedIsoFileName;
    String selectedDevice;


    public void setSelectedIsoFile(File selectedIsoFile) {
        this.selectedIsoFile = selectedIsoFile;
    }

    public File getSelectedIsoFile() {
        return selectedIsoFile;
    }
    public void setSelectedIsoFileName(String selectedIsoFileName) {
        this.selectedIsoFileName = selectedIsoFileName;
    }
    public String getSelectedIsoFileName() {
        return selectedIsoFileName;
    }

    public void setSelectedDevice(String selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

    public String getSelectedDevice() {
        return selectedDevice;
    }

}
