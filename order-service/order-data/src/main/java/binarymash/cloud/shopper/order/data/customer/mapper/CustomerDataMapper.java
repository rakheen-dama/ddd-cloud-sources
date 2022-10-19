package binarymash.cloud.shopper.order.data.customer.mapper;

import binarymash.cloud.shopper.domain.valueobject.CustomerId;
import binarymash.cloud.shopper.order.data.customer.entity.CustomerEntity;
import binarymash.cloud.shopper.order.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }
}
