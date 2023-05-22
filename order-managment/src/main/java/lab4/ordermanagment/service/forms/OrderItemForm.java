package lab4.ordermanagment.service.forms;

import lab4.ordermanagment.domain.valueojects.Game;
import lombok.Data;
import javax.validation.Validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderItemForm {

    @NotNull
    private Game game;

    @Min(1)
    private int quantity =1;

}
