package lab4.sharedkernel.infra;

import lab4.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {

    void publish(DomainEvent domainEvent);
}
