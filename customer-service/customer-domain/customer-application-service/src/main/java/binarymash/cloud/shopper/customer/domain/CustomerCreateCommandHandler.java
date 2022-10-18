package binarymash.cloud.shopper.customer.domain;

import binarymash.cloud.shopper.customer.domain.create.CreateCustomerCommand;
import binarymash.cloud.shopper.customer.domain.event.CustomerCreatedEvent;
import binarymash.cloud.shopper.customer.domain.exception.CustomerDomainException;
import binarymash.cloud.shopper.customer.domain.mapper.CustomerDataMapper;
import binarymash.cloud.shopper.customer.domain.ports.output.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerCreateCommandHandler {

    private final CustomerDomainService domainService;
    private final CustomerRepository repository;
    private final CustomerDataMapper mapper;

    public CustomerCreatedEvent createCustomer(CreateCustomerCommand createCustomerCommand) {
        var customer = mapper.customerCommandToCustomer(createCustomerCommand);
        var customerCreatedEvent = domainService.validateAndRegisterCustomer(customer);
        var persistedCustomer = repository.createCustomer(customer);
        if (persistedCustomer == null) {
            log.error("Could not save customer with id: {}", createCustomerCommand.getCustomerId());
            throw new CustomerDomainException("Could not save customer with id " + createCustomerCommand.getCustomerId());
        }
        log.debug("Returning CustomerCreatedEvent for customer id: {}", createCustomerCommand.getCustomerId());
        return customerCreatedEvent;
    }
}
