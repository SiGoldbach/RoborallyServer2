package com.example.demo.board;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class BoardControllerTest {

    @Test
    void loadBoard() {
    }

    @Test
    void load2() {
    }

    @Test
    void getBoards() {
    }

    @Test
    void saveBoard() throws IOException, URISyntaxException {
        new BoardDataLayer().CreateAndWriteToNewFile("SPringboot/demo/src/main/resources/boards/FileNameFromClientr2.json","TheName42qwe");
    }
    @Test
    void tryingToCreatAFileForTestingPath() throws IOException {
        new File("src/main/resources/boards/FileNameFromClient.json").createNewFile();
    }
}