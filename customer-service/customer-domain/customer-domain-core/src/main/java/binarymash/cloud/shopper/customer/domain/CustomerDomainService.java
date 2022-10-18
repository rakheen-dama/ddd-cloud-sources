package binarymash.cloud.shopper.customer.domain;

import binarymash.cloud.shopper.customer.domain.entity.Customer;
import binarymash.cloud.shopper.customer.domain.event.CustomerCreatedEvent;

public interface CustomerDomainService {

    CustomerCreatedEvent validateAndRegisterCustomer(Customer customer);
}
