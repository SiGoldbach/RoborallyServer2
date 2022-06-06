package com.example.demo.board;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
@Component
public class BoardDataLayer {
    private static final String BoardFolder="boards";


    public File getURIFromResourceFolder(String fileName) throws FileNotFoundException, URISyntaxException {
        URL board=getClass().getClassLoader().getResource(BoardFolder+"/"+fileName+".json");
        if (board==null){
            throw new FileNotFoundException("File not found");
        }
        return new File(board.toURI());


    }



}
