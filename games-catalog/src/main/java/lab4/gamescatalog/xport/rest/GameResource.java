package lab4.gamescatalog.xport.rest;


import lab4.gamescatalog.domain.models.Game;
import lab4.gamescatalog.services.GameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class GameResource {

    private final GameService gameService;


    @GetMapping
    public List<Game> getAll(){
        return gameService.getAll();
    }
}
