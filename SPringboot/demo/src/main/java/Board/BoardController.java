package Board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class BoardController {

    @GetMapping("Load")
    public InputStream load(){

        return getClass().getClassLoader().getResourceAsStream("defaultboard.json");


    }

}
