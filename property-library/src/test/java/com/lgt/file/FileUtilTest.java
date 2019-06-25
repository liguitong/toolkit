package com.lgt.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void getAllPropertyFiles() throws IOException {
        FileUtil fileUtil= new FileUtil();
        File currentFile = new File("..");

        System.out.println("Try to file the property files in : " +currentFile.getCanonicalPath());
        List<String> propertyFiles = fileUtil.getAllPropertyFiles(currentFile);
        propertyFiles.forEach(file->{
            System.out.println(file);
        });
        assertTrue(propertyFiles.size() == 1);
    }
}
