package com.kgsolutions.winboot.viewcontrollers;

import com.gluonhq.charm.glisten.control.ProgressIndicator;
import javafx.fxml.FXML;

public class ProcessViewController{



    @FXML
    public ProgressIndicator progressIndicator;

    public void setFormatProgressIndicatorStatus(double progress) {
        progressIndicator.setProgress(progress);
    }
}
