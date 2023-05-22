package lab4.gamescatalog.xport.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import lab4.gamescatalog.services.GameService;
import lab4.sharedkernel.domain.config.TopicHolder;
import lab4.sharedkernel.domain.events.DomainEvent;
import lab4.sharedkernel.domain.events.orders.OrderGameCreated;
import lab4.sharedkernel.domain.events.orders.OrderItemRemoved;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameEventListener {

    private final GameService gameService;

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED,groupId = "productCatalog")
    public void consumerOrderItemCreatedEvent(String jsonMessage){
        try {
            OrderGameCreated event = DomainEvent.fromJson(jsonMessage,OrderGameCreated.class);
            gameService.orderItemCreated(event.getGameId(),event.getQuantiy());
        } catch (JsonProcessingException e) {

        }
    }
    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_REMOVED,groupId = "productCatalog")
    public void consumerOrderItemRemovedEvent(String jsonMessage){
        try {
            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage,OrderItemRemoved.class);
            gameService.orderItemRemoved(event.getId(),event.getQuantity());
        } catch (JsonProcessingException e) {

        }
    }

}
