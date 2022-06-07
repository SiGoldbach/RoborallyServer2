package com.example.demo.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "boards")
public class BoardController {
    /**
     * Method for getting a specific board, This will be used when starting a game.
     * Do not use does not return anything of value
     *
     * @param name Name of the board
     * @return A inputStream used ins loadGame method.
     */
    @GetMapping(value = "load/{name}")
    public File load(@PathVariable String name) {
        System.out.println("File requested: " + name);
        File file = null;
        try {
            file = new BoardDataLayer().getURIFromResourceFolder(name);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the requested file ");

        } catch (URISyntaxException e) {
            System.out.println("URI exception");
        }


        if (file == null) {
            System.out.println("Could not get file");
        }
        return file;


    }

    @GetMapping(value = "loads/{name}")
    public String loadBoard(@PathVariable String name) {
        String file = null;
        try {
            file = new BoardDataLayer().jsonToString(name);
        } catch (NullPointerException e) {
            System.out.println("Cant find the given file");
        } catch (IOException e) {
            System.out.println("IO exception");
        } catch (URISyntaxException e) {
            System.out.println("URI syntax exception");
        }


        return file;

    }

    @GetMapping(value = "/Load2")
    public String load2() {
        return "hej";
    }


    @GetMapping(value = "/getboards")
    //Create a GET request to all boards located in the boards folder and display all the json files in the boards folder.
    public List<String> getBoards() {
        List<String> boards = new ArrayList<>();
        try {
        String RESOURCEFOLDER = "SPringboot/demo/src/main/resources/boards";
        File folder = new File(RESOURCEFOLDER);
        String[] listOfFiles = folder.list();
            for (int i = 0; i < listOfFiles.length; i++) {
                listOfFiles[i] = listOfFiles[i].substring(0, listOfFiles[i].length() - 5);
            }
            boards.addAll(java.util.Arrays.asList(listOfFiles));

        } catch (NullPointerException e) {
            System.out.println("Could not find the folder");
        }

        return boards;
    }
}


