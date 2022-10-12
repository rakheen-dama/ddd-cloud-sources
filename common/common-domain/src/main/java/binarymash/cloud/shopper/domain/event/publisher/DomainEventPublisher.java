package binarymash.cloud.shopper.domain.event.publisher;

import binarymash.cloud.shopper.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
