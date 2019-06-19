package com.lgt.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public List<String> getAllPropertyFiles(File projectRoot) {
        List<String> propertyFiles = new ArrayList<>();
        File[] files = projectRoot.listFiles();
        for (File file : files) {
            if (file.getName().startsWith(".")) {
                continue;
            } else if (file.isDirectory()) {
                propertyFiles.addAll(getAllPropertyFiles(file));
            } else {
                if (file.isFile() && file.getName().endsWith(".properties")) {
                    try {
                        System.out.println("Find property file: " + file.getCanonicalPath());
                        propertyFiles.add(file.getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return propertyFiles;
    }
}
