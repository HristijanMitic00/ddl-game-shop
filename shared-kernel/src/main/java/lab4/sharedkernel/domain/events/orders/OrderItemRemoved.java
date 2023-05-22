package lab4.sharedkernel.domain.events.orders;

import lab4.sharedkernel.domain.config.TopicHolder;
import lab4.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class OrderItemRemoved extends DomainEvent {

    private Long id;
    private int quantity;

    public OrderItemRemoved(String topic, Long id, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.id = id;
        this.quantity = quantity;
    }

    public OrderItemRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }
}
