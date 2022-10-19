package binarymash.cloud.shopper.domain.ports.output.repository;

import binarymash.cloud.shopper.order.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);
    Customer save(Customer customer);
}
