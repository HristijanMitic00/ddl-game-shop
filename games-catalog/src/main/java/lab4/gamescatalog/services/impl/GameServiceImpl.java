package lab4.gamescatalog.services.impl;


import jakarta.transaction.Transactional;
import lab4.gamescatalog.domain.models.Game;
import lab4.gamescatalog.domain.repository.GameRepository;
import lab4.gamescatalog.services.GameService;
import lab4.gamescatalog.services.form.GameForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Game createGame(GameForm form) {
        Game g = Game.build(form.getGameName(),form.getPrice(),form.getSales(),form.getGameType());
        gameRepository.save(g);
        return g;
    }

    @Override
    public Game orderItemCreated(Long id, int quantity) {
        Game g = gameRepository.findById(id).orElseThrow(RuntimeException::new);
        g.addSales(quantity);
        gameRepository.saveAndFlush(g);
        return g;
    }

    @Override
    public Game orderItemRemoved(Long id, int quantity) {
        Game g = gameRepository.findById(id).orElseThrow(RuntimeException::new);
        g.removeSales(quantity);
        gameRepository.saveAndFlush(g);
        return g;
    }

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }
}
