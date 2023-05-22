package lab4.gamescatalog.services.form;

import lab4.gamescatalog.domain.enums.Type;
import lab4.sharedkernel.domain.financial.Money;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class GameForm {
    private String gameName;
    private Money price;
    private int sales;
    private Type gameType;
}
