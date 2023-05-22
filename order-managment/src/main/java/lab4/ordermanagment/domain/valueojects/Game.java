package lab4.ordermanagment.domain.valueojects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lab4.ordermanagment.domain.model.Type;
import lab4.sharedkernel.domain.base.ValueObject;
import lab4.sharedkernel.domain.financial.Currency;
import lab4.sharedkernel.domain.financial.Money;
import lombok.Getter;

@Getter
public class Game implements ValueObject {

    private final Long gameId;
    private final Money price;
    private final String name;
    private final Type gameType;
    private final int sales;


    private Game(){
        this.gameId= 0L;
        this.name="";
        this.price=new Money(Currency.MKD,0);
        this.gameType=Type.ACTION;
        sales=0;
    }
    @JsonCreator
    public Game(@JsonProperty("gameId") Long gameId,@JsonProperty("gamePrice") Money price, @JsonProperty("gameName") String name,
                @JsonProperty("gameType") Type gameType,
                @JsonProperty("sales") int sales) {
        this.gameId = gameId;
        this.price = price;
        this.name = name;
        this.gameType=gameType;
        this.sales=sales;
    }
}
