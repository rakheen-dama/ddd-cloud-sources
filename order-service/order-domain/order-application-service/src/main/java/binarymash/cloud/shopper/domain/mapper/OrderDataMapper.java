package binarymash.cloud.shopper.domain.mapper;

import binarymash.cloud.shopper.domain.dto.create.CreateOrderCommand;
import binarymash.cloud.shopper.domain.dto.message.CustomerModel;
import binarymash.cloud.shopper.domain.valueobject.CustomerId;
import binarymash.cloud.shopper.domain.valueobject.ProductId;
import binarymash.cloud.shopper.domain.valueobject.SupplierId;
import binarymash.cloud.shopper.order.domain.entity.Customer;
import binarymash.cloud.shopper.order.domain.entity.Product;
import binarymash.cloud.shopper.order.domain.entity.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderDataMapper {
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
}
