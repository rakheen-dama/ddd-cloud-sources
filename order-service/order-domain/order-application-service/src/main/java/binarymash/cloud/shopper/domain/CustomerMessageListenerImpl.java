package binarymash.cloud.shopper.domain;

import binarymash.cloud.shopper.domain.dto.message.CustomerModel;
import binarymash.cloud.shopper.domain.ports.input.message.listener.customer.CustomerMessageListener;

public class CustomerMessageListenerImpl implements CustomerMessageListener {

    @Override
    public void customerCreated(final CustomerModel customerModel) {

    }
}
