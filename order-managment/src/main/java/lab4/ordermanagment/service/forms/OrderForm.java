package lab4.ordermanagment.service.forms;


import lab4.ordermanagment.domain.model.Order;
import lab4.sharedkernel.domain.financial.Currency;
import lombok.Data;


import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {

    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<OrderItemForm> games = new ArrayList<>();


}
