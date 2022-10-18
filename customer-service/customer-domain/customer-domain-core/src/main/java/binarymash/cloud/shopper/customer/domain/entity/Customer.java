package binarymash.cloud.shopper.customer.domain.entity;

import binarymash.cloud.shopper.domain.entity.AggregateRoot;
import binarymash.cloud.shopper.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    private final String username;
    private final String firstName;
    private final String lastName;

    public Customer(CustomerId customerId, final String username, final String firstName, final String lastName) {
        super.setId(customerId);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
