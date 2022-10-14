package binarymash.cloud.shopper.order.domain;

import binarymash.cloud.shopper.order.domain.entity.Order;
import binarymash.cloud.shopper.order.domain.entity.Supplier;
import binarymash.cloud.shopper.order.domain.event.OrderCancelledEvent;
import binarymash.cloud.shopper.order.domain.event.OrderCreatedEvent;
import binarymash.cloud.shopper.order.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Supplier supplier);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
