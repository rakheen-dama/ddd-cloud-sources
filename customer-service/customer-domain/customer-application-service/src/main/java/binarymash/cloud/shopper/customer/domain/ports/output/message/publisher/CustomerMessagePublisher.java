package binarymash.cloud.shopper.customer.domain.ports.output.message.publisher;

import binarymash.cloud.shopper.customer.domain.event.CustomerCreatedEvent;

public interface CustomerMessagePublisher {

    void publish(CustomerCreatedEvent createdEvent);
}
