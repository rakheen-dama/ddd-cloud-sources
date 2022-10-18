package binarymash.cloud.shopper.customer.domain.mapper;

import binarymash.cloud.shopper.customer.domain.create.CreateCustomerCommand;
import binarymash.cloud.shopper.customer.domain.create.CreateCustomerResponse;
import binarymash.cloud.shopper.customer.domain.entity.Customer;
import binarymash.cloud.shopper.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {
    public Customer customerCommandToCustomer(final CreateCustomerCommand createCustomerCommand) {
        return new Customer(
                new CustomerId(createCustomerCommand.getCustomerId()),
                createCustomerCommand.getUsername(),
                createCustomerCommand.getFirstName(),
                createCustomerCommand.getLastName()
        );
    }

    public CreateCustomerResponse customerToCreateCustomerResponse(Customer customer, String messsage) {
        return new CreateCustomerResponse(customer.getId().getValue(), messsage);
    }
}
