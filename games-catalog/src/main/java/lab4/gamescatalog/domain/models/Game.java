package lab4.gamescatalog.domain.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lab4.gamescatalog.domain.enums.Type;
import lab4.sharedkernel.domain.financial.Money;
import lombok.Getter;

@Entity
@Getter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    private String gameName;
    private int sales=0;
    private Money gamePrice;
    private Type gameType;

    private Game(){}


    public static Game build(String name, Money price, int sales,Type gameType){
        Game g = new Game();
        g.gamePrice=price;
        g.gameName=name;
        g.sales=sales;
        g.gameType=gameType;
        return g;
    }

    public void addSales(int quantity){
        this.sales=this.sales-quantity;
    }

    public void removeSales(int quantity){
        this.sales-=quantity;
    }
}
