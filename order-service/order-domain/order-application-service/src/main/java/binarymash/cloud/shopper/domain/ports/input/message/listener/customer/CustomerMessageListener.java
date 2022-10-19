package binarymash.cloud.shopper.domain.ports.input.message.listener.customer;

import binarymash.cloud.shopper.domain.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
