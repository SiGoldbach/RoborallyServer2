package com.example.demo.board;

import org.junit.jupiter.api.Test;

import java.io.IOException;

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
    void saveBoard() throws IOException {
        new BoardController().saveBoard("FunnyFileContent","test40");
    }
}