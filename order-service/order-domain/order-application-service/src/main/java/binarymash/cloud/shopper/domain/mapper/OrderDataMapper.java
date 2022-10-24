package binarymash.cloud.shopper.domain.mapper;

import binarymash.cloud.shopper.domain.dto.create.CreateOrderCommand;
import binarymash.cloud.shopper.domain.dto.create.OrderAddress;
import binarymash.cloud.shopper.domain.dto.message.CustomerModel;
import binarymash.cloud.shopper.domain.valueobject.CustomerId;
import binarymash.cloud.shopper.domain.valueobject.Money;
import binarymash.cloud.shopper.domain.valueobject.ProductId;
import binarymash.cloud.shopper.domain.valueobject.SupplierId;
import binarymash.cloud.shopper.order.domain.entity.Customer;
import binarymash.cloud.shopper.order.domain.entity.Order;
import binarymash.cloud.shopper.order.domain.entity.OrderItem;
import binarymash.cloud.shopper.order.domain.entity.Product;
import binarymash.cloud.shopper.order.domain.entity.Supplier;
import binarymash.cloud.shopper.order.domain.valueobject.StreetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderDataMapper {
    private List<OrderItem> items;

    public Customer customerModelToCustomer(CustomerModel customerModel) {
        return new Customer(
                new CustomerId(UUID.fromString(customerModel.id())),
                customerModel.username(),
                customerModel.firstName(),
                customerModel.lastName()
        );
    }

    public Supplier createOrderCommandToSupplier(final CreateOrderCommand createOrderCommand) {
        return Supplier.builder()
                .supplierId(new SupplierId(createOrderCommand.supplierId()))
                .products(createOrderCommand.items().stream()
                        .map(orderItem -> new Product(new ProductId(orderItem.productId()))).collect(Collectors.toList())
                )
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .supplierId(new SupplierId(createOrderCommand.supplierId()))
                .customerId(new CustomerId(createOrderCommand.customerId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.orderAddress()))
                .price(new Money(createOrderCommand.price()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.items()))
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(List<binarymash.cloud.shopper.domain.dto.create.OrderItem> items) {
        return items.stream()
                .map(orderItem ->
                        OrderItem.builder()
                                .product(new Product(new ProductId(orderItem.productId())))
                                .price(new Money(orderItem.price()))
                                .quantity(orderItem.quantity())
                                .subTotal(new Money(orderItem.subTotal()))
                                .build()).collect(Collectors.toList());
    }


    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(UUID.randomUUID(), orderAddress.street(), orderAddress.postalCode(), orderAddress.city());
    }
}
