package lab4.sharedkernel.domain.events.orders;

import lab4.sharedkernel.domain.config.TopicHolder;
import lab4.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class OrderGameCreated extends DomainEvent {

    private Long gameId;
    private int quantiy;

    public OrderGameCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public OrderGameCreated(Long gameId, int quantiy) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.gameId = gameId;
        this.quantiy = quantiy;
    }
}
