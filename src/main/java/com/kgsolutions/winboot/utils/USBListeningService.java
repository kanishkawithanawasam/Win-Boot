package com.kgsolutions.winboot.utils;

import com.kgsolutions.winboot.viewcontrollers.HomeViewController;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.nio.file.*;

public class USBListeningService extends Service<Void> {

    private final HomeViewController homeViewController;

    /**
     * Initialises the Home View Controller.
     * @param homeViewController
     */
    public USBListeningService(HomeViewController homeViewController) {
        this.homeViewController = homeViewController;
    }


    @Override
    protected Task<Void> createTask() {
        return new Task<>() {

            @Override
            protected Void call() throws Exception {
                try{
                    Path path = Paths.get("/Volumes");
                    WatchService watchService = FileSystems.getDefault().newWatchService();
                    path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
                    System.out.println("Watch Service created");
                    while(true){
                        WatchKey key = watchService.take();
                        for(WatchEvent<?> event : key.pollEvents()) {
                            Platform.runLater(homeViewController::updateUSBList);
                        }
                        key.reset();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
