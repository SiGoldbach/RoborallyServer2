package com.example.demo.board;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDataLayer {
    private static final String BoardFolder = "boards";
    private static final String TYPE = ".json";


    public File getURIFromResourceFolder(String fileName) throws FileNotFoundException, URISyntaxException {
        URL board = getClass().getClassLoader().getResource(BoardFolder + "/" + fileName + ".json");
        if (board == null) {
            throw new FileNotFoundException("File not found");
        }
        return new File(board.toURI());


    }

    public void CreateAndWriteToNewFile(String name, String board) throws IOException {


    }

    public String jsonToString(String fileName) throws IOException, NullPointerException, URISyntaxException {
        URL board = getClass().getClassLoader().getResource(BoardFolder + "/" + fileName + TYPE);
        System.out.println("line 2");
        File file = Paths.get(board.toURI()).toFile();
        System.out.println("line 3");
        String absolutePath = file.getAbsolutePath();
        System.out.println("line 4");

        return Files.readString(Path.of(absolutePath));


    }

}
