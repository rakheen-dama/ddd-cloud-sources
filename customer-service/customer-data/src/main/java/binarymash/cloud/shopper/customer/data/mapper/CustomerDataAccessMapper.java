package binarymash.cloud.shopper.customer.data.mapper;

import binarymash.cloud.shopper.customer.data.entity.CustomerEntity;
import binarymash.cloud.shopper.customer.domain.entity.Customer;
import binarymash.cloud.shopper.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public CustomerEntity customerToCustomerEntity(final Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }

    public Customer customerEntityToCustomer(final CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()),
                customerEntity.getUsername(),
                customerEntity.getFirstName(),
                customerEntity.getLastName());
    }
}
