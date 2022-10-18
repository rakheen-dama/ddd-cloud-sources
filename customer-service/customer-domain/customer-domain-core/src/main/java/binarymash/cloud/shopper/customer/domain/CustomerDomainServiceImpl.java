package binarymash.cloud.shopper.customer.domain;

import binarymash.cloud.shopper.customer.domain.entity.Customer;
import binarymash.cloud.shopper.customer.domain.event.CustomerCreatedEvent;

public class CustomerDomainServiceImpl implements CustomerDomainService{

    @Override
    public CustomerCreatedEvent validateAndRegisterCustomer(final Customer customer) {
        return null;
    }
}
