package lab4.gamescatalog.config;

import jakarta.annotation.PostConstruct;
import lab4.gamescatalog.domain.enums.Type;
import lab4.gamescatalog.domain.models.Game;
import lab4.gamescatalog.domain.repository.GameRepository;
import lab4.sharedkernel.domain.financial.Currency;
import lab4.sharedkernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final GameRepository gameRepository;

    @PostConstruct
    public void initData(){
        Game g1 = Game.build("GTA V", Money.valueOf(Currency.MKD,500),10,Type.ACTION);
        Game g2 = Game.build("GTA IV", Money.valueOf(Currency.MKD,400),8, Type.ACTION);

        if (gameRepository.findAll().isEmpty()){
            gameRepository.saveAll(Arrays.asList(g1,g2));
        }



    }

}
