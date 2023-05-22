package lab4.ordermanagment.domain.model;

import jakarta.persistence.*;
import lab4.ordermanagment.domain.valueojects.Game;
import lab4.sharedkernel.domain.financial.Money;

import java.time.Instant;
import lab4.sharedkernel.domain.financial.Currency;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "naracka")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String deliveryAddress;
    @Enumerated(value = EnumType.STRING)
    private Status orderStatus;
    private Instant orderDate;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<GameInOrder> gameInOrderSet;

    private Order() {
    }

    public Order(Instant now, @NotNull Currency currency){
        this.orderDate = now;
        this.currency = currency;
    }

    public Money total(){
        return gameInOrderSet.stream().map(GameInOrder::subTotal).reduce(new Money(currency,0), Money::add);
    }

    public GameInOrder addGame(@NonNull Game game, @NonNull int quantity){
        Objects.requireNonNull(game,"game must not be null!");
        var item = new GameInOrder(game.getGameId(),game.getPrice(),quantity);
        gameInOrderSet.add(item);
        return item;
    }

    public void removeGame(@NonNull Long id){
        Objects.requireNonNull(id,"game must not be null!");
        gameInOrderSet.removeIf(g->g.getId().equals(id));
    }

}
