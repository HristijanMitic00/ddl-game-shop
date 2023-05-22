package lab4.ordermanagment.infra;

import lab4.sharedkernel.domain.events.DomainEvent;
import lab4.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DomainEventPublisheImpl implements DomainEventPublisher {

    private final KafkaTemplate<String,String> kafkaTemplate;


    @Override
    public void publish(DomainEvent domainEvent) {
        this.kafkaTemplate.send(domainEvent.topic(),domainEvent.toJson());
    }
}
