package Board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class BoardController {
    /**
     * Method for getting a specific board
     * @param name Name of the board
     * @return A inputStream used ins loadGame method.
     */
    @GetMapping(value ="load/{name}")
    public InputStream load(@PathVariable String name) {
        System.out.println("In load");
        return getClass().getClassLoader().getResourceAsStream(name+".json");


    }

    @GetMapping(value="/Load2")
    public String load2() {
        return "hej";
    }

}
