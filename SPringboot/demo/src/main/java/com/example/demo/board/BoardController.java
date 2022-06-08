package com.example.demo.board;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
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
     */
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

    @PostMapping(value = "/saveboard/{name}")
    //Create a POST request to save a board to the boards folder.
    public void saveBoard(@RequestBody String boardContent,@PathVariable String name) throws IOException {
        System.out.println("In saveBoard");
        System.out.println(name);
        System.out.println(boardContent);
        boolean file = new File("src/main/resources/boards/" + name + ".json").createNewFile();
        new BoardDataLayer().CreateAndWriteToNewFile("src/main/resources/boards/" + name + ".json",boardContent);
    }


}
