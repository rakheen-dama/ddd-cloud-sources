package binarymash.cloud.shopper.order.domain;

import binarymash.cloud.shopper.domain.valueobject.ProductId;
import binarymash.cloud.shopper.order.domain.entity.Order;
import binarymash.cloud.shopper.order.domain.entity.OrderItem;
import binarymash.cloud.shopper.order.domain.entity.Product;
import binarymash.cloud.shopper.order.domain.entity.Supplier;
import binarymash.cloud.shopper.order.domain.event.OrderCancelledEvent;
import binarymash.cloud.shopper.order.domain.event.OrderCreatedEvent;
import binarymash.cloud.shopper.order.domain.event.OrderPaidEvent;
import binarymash.cloud.shopper.order.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Supplier supplier) {
        validateSupplier(supplier);
        setOrderProductInformation(order, supplier);
        order.validateOrder();
        order.initializeOrder();
        log.debug("Order with id: {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now());
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.debug("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now());
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.debug("Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.debug("Order payment is cancelling for order id: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now());
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.debug("Order with id: {} is cancelled", order.getId().getValue());
    }

    private void validateSupplier(Supplier supplier) {
        if (!supplier.isActive()) {
            throw new OrderDomainException("Supplier with id " + supplier.getId().getValue() + " is not active!");
        }
    }

    private void setOrderProductInformation(Order order, Supplier supplier) {
        // Todo must be a better way
        Map<Product, Product> productMap = supplier.getProducts().stream()
                .collect(Collectors.toMap(Function.identity(), Function.identity()));
        for (OrderItem item : order.getItems()) {
            Product currentProduct = item.getProduct();
            Product catalogProduct = productMap.get(currentProduct);
            currentProduct.updateWithConfirmedNameAndPrice(catalogProduct.getName(), catalogProduct.getPrice());
        }
    }
}
