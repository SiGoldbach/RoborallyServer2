package com.example.demo.Game;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class GameDataLayer {

    private static final String gameFolder = "boards";
    private static final String TYPE = ".txt";


    public void CreateAndWriteToNewFile(String name, String board) throws IOException, URISyntaxException {
        URL pathGenerator=getClass().getClassLoader().getResource(gameFolder+"/"+name+TYPE);
        Path path = Path.of(name);
        try {
            System.out.println(name);
            new File(name).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Files.isWritable(path)) {
            Files.writeString(path, board, StandardCharsets.UTF_8);
        } else {
            throw new IOException("Can't find the file ");
        }
    }
}
