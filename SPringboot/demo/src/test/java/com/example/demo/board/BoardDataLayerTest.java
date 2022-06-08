package com.example.demo.board;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class BoardDataLayerTest {
    /**
     * This method is for testing The "Database access" aka finding a file in the resource folder properly,
     * if test fails there is probably something wrong with this database access
     *
     * @throws URISyntaxException Luckily we have not had this exception yet but if it does not activate on this test,
     *                            it probably will not be thrown on other request since the syntax is completely the same just different filenames
     *                            //Sebastian Goldbach
     */
    @Test
    void getURIFromResourceFolder() throws URISyntaxException {
        BoardDataLayer test = new BoardDataLayer();
        File fTest = null;
        try {
            fTest = test.getURIFromResourceFolder("TestFile");
        } catch (FileNotFoundException e) {
            fail("Cant find the file");
        }
        if (fTest == null) {
            fail("Cant find the file ");
        }
    }

    @Test
        //Method for saving a board as a json file to the resource folder boards.
    void saveBoard() throws IOException, URISyntaxException {
        BoardDataLayer test = new BoardDataLayer();
        String RESOURCEFOLDER = "SPringboot/demo/src/main/resources/boards";
        File folder = new File(RESOURCEFOLDER);
        if (folder.canWrite()) {
            System.out.println("Getting to the upload");
            System.out.println("name: TestFile");
            System.out.println("board: TestBoard");
            try {
                FileWriter writer = new FileWriter(folder);
                writer.write("TestBoard");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void writeToFile() throws IOException, URISyntaxException {
        new BoardDataLayer().CreateAndWriteToNewFile("src/main/resources/boards/File2.json","BigMon");

    }

}