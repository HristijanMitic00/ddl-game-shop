package lab4.ordermanagment.domain.model;

import jakarta.persistence.*;
import lab4.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

@Entity
@Table(name = "game_in_order")
@Getter
public class GameInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Money gameTotal;
    private int quantity;
    private Long gameId;

    public GameInOrder() {
    }


    public GameInOrder(@NonNull Long gameId,@NonNull Money price,@NonNull int quantity){
        this.gameId=gameId;
        this.gameTotal=price;
        this.quantity=quantity;
    }

    public Money subTotal(){
        return gameTotal.multiply(quantity);
    }
}
