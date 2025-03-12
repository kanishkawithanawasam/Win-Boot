package com.kgsolutions.winboot.utils;

import com.kgsolutions.winboot.viewcontrollers.HomeViewController;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.nio.file.*;

public class USBListeningService extends Service<Void> {

    private final HomeViewController homeViewController;

    /**
     * Initialises the Home View Controller.
     * @param homeViewController Home view controller
     */
    public USBListeningService(HomeViewController homeViewController) {
        this.homeViewController = homeViewController;

    }


    @Override
    protected Task<Void> createTask() {
        return new Task<>() {

            @Override
            protected Void call()  {
                try{
                    Path path = Paths.get("/Volumes");
                    WatchService watchService = FileSystems.getDefault().newWatchService();
                    path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
                    System.out.println("Watch Service created");
                    while(true){
                        WatchKey key = watchService.take();
                        for(WatchEvent<?> _ : key.pollEvents()) {
                            Platform.runLater(()->{
                                try {
                                    DevicesReader devicesReader = new DevicesReader();
                                    homeViewController.updateUSBList(devicesReader.getDevices());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                        // Ensure the key is still valid before resetting
                        if (!key.reset()) {
                            System.out.println("WatchKey is no longer valid. Exiting loop.");
                            break; // Exit loop when WatchKey is invalid
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };
    }
}
