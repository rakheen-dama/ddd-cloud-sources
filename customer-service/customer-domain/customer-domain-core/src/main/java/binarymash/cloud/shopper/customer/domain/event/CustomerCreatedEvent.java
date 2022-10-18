package binarymash.cloud.shopper.customer.domain.event;

import binarymash.cloud.shopper.customer.domain.entity.Customer;
import binarymash.cloud.shopper.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class CustomerCreatedEvent implements DomainEvent<Customer> {

    private final Customer customer;
    private final ZonedDateTime createdAt;

    public CustomerCreatedEvent(final Customer customer, final ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }
}
