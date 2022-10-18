package binarymash.cloud.shopper.customer.domain;

import binarymash.cloud.shopper.customer.domain.create.CreateCustomerCommand;
import binarymash.cloud.shopper.customer.domain.create.CreateCustomerResponse;
import binarymash.cloud.shopper.customer.domain.event.CustomerCreatedEvent;
import binarymash.cloud.shopper.customer.domain.mapper.CustomerDataMapper;
import binarymash.cloud.shopper.customer.domain.ports.input.CustomerApplicationService;
import binarymash.cloud.shopper.customer.domain.ports.output.message.publisher.CustomerMessagePublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Slf4j
@Validated
@AllArgsConstructor
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private final CustomerCreateCommandHandler customerCreateCommandHandler;
    private final CustomerDataMapper mapper;
    private final CustomerMessagePublisher messagePublisher;

    @Override
    public CreateCustomerResponse createCustomer(final CreateCustomerCommand createCustomerCommand) {
        CustomerCreatedEvent customerCreatedEvent = customerCreateCommandHandler.createCustomer(createCustomerCommand);
        messagePublisher.publish(customerCreatedEvent);
        return mapper.customerToCreateCustomerResponse(customerCreatedEvent.getCustomer(), "Customer saved successfully!");
    }
}
