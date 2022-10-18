package binarymash.cloud.shopper.customer.domain.ports.output.repository;

import binarymash.cloud.shopper.customer.domain.entity.Customer;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);
}
