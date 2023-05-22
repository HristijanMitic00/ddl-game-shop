package lab4.gamescatalog.services;

import lab4.gamescatalog.domain.models.Game;
import lab4.gamescatalog.services.form.GameForm;

import java.util.List;

public interface GameService {

    Game findById(Long id);
    Game createGame(GameForm form);
    Game orderItemCreated(Long id,int quantity);
    Game orderItemRemoved(Long id,int quantity);
    List<Game> getAll();
}
