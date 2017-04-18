package com.frankslog.json.comparison.util.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

import org.testng.Assert;


public class FileContentReader {

    private static String dataPath = "src/test/resources/";

    public static void setDataPath(String dataPath) {
        System.out.println("Setting data path: " + dataPath);
        FileContentReader.dataPath = dataPath;
    }

    public static String readFromFile(String filename) {
        try {
            String pathToRead = dataPath + filename;
            System.out.println("Reading: " + pathToRead);
            List<String> lines = Files.readAllLines(FileSystems.getDefault().getPath(pathToRead));
            StringBuilder sb = new StringBuilder();
            lines.stream().forEach(s -> sb.append(s));
            return sb.toString();
        } catch (IOException e) {
            Assert.fail("Error reading file " + filename + " :: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return null;
    }

}
