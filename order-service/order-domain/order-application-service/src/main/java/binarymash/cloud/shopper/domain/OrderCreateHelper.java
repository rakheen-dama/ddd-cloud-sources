package binarymash.cloud.shopper.domain;

import binarymash.cloud.shopper.domain.dto.create.CreateOrderCommand;
import binarymash.cloud.shopper.domain.mapper.OrderDataMapper;
import binarymash.cloud.shopper.domain.ports.output.repository.CustomerRepository;
import binarymash.cloud.shopper.domain.ports.output.repository.OrderRepository;
import binarymash.cloud.shopper.domain.ports.output.repository.SupplierRepository;
import binarymash.cloud.shopper.order.domain.OrderDomainService;
import binarymash.cloud.shopper.order.domain.entity.Order;
import binarymash.cloud.shopper.order.domain.entity.Supplier;
import binarymash.cloud.shopper.order.domain.event.OrderCreatedEvent;
import binarymash.cloud.shopper.order.domain.exception.OrderDomainException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateHelper {
    
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;

    private final OrderDataMapper orderDataMapper;

    @Transactional
    public OrderCreatedEvent persistOrder(final CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.customerId());
        Supplier supplier = checkSupplier(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, supplier);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private Supplier checkSupplier(final CreateOrderCommand createOrderCommand) {
        Supplier supplierAgg = orderDataMapper.createOrderCommandToSupplier(createOrderCommand);
        Optional<Supplier> supplier = supplierRepository.findSupplier(supplierAgg);
        if (supplier.isEmpty()) {
            log.warn("Could not find restaurant with restaurant id: {}", createOrderCommand.supplierId());
            throw new OrderDomainException("Could not find restaurant with restaurant id: " +
                    createOrderCommand.supplierId());
        }
        return supplier.get();
    }

    private void checkCustomer(final UUID customerId) {
        customerRepository.findCustomer(customerId).orElseThrow(() -> new OrderDomainException("Could not find customer with id: " + customerId));
    }

    private Order saveOrder(Order order) {
        Order persistedOrder = orderRepository.save(order);
        if (persistedOrder == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.debug("Order is saved with id: {}", persistedOrder.getId().getValue());
        return persistedOrder;
    }
}
