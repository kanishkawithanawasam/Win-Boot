package com.kgsolutions.winboot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DevicesReader {

    public List<String> getDevices() throws IOException {
        // Rading the volumes list
        List<String> volumeNames = new ArrayList<>();
        Process process = new ProcessBuilder("ls","/Volumes").start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            volumeNames.add(line.trim());
        }
        reader.close();

        // Reading external devices
        List<String> externalDeviceNames = new ArrayList<>();
        process = new ProcessBuilder("diskutil","list", "external").start();
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((line = reader.readLine()) != null) {
            String words[] = line.trim().split("\\s+");

            for (String externalDevice : words) {
                if (volumeNames.contains(externalDevice)) {
                    externalDeviceNames.add(externalDevice);
                }
            }
        }

        return externalDeviceNames;
    }
}
