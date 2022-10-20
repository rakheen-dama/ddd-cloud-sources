package binarymash.cloud.shopper.domain.mapper;

import binarymash.cloud.shopper.domain.dto.message.CustomerModel;
import binarymash.cloud.shopper.domain.valueobject.CustomerId;
import binarymash.cloud.shopper.order.domain.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
}
