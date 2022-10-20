package binarymash.cloud.shopper.domain;

import binarymash.cloud.shopper.domain.dto.create.CreateOrderCommand;
import binarymash.cloud.shopper.domain.mapper.OrderDataMapper;
import binarymash.cloud.shopper.domain.ports.output.repository.CustomerRepository;
import binarymash.cloud.shopper.domain.ports.output.repository.OrderRepository;
import binarymash.cloud.shopper.order.domain.OrderDomainService;
import binarymash.cloud.shopper.order.domain.entity.Supplier;
import binarymash.cloud.shopper.order.domain.exception.OrderDomainException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateHelper {
    
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    private final OrderDataMapper orderDataMapper;

    public void persistOrder(final CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.customerId());
        Supplier supplier = checkSupplier(createOrderCommand);
    }

    private Supplier checkSupplier(final CreateOrderCommand createOrderCommand) {
        Supplier supplier = orderDataMapper.createOrderCommandToSupplier(createOrderCommand);
    }

    private void checkCustomer(final UUID customerId) {
        customerRepository.findCustomer(customerId).orElseThrow(() -> new OrderDomainException("Could not find customer with id: " + customerId));
    }
}
