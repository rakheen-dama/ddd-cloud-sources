package binarymash.cloud.shopper.order.messaging.mapper;

import binarymash.cloud.shopper.domain.dto.message.CustomerModel;
import binarymash.cloud.shopper.kafka.avro.model.CustomerAvroModel;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMessageMapper {

    public CustomerModel customerAvroModelToCustomerModel(CustomerAvroModel customerAvroModel) {
        return new CustomerModel(customerAvroModel.getId(), customerAvroModel.getUsername(), customerAvroModel.getFirstName(), customerAvroModel.getLastName());
    }
}
