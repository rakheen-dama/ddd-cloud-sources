package binarymash.cloud.shopper.customer.domain.ports.input;

import binarymash.cloud.shopper.customer.domain.create.CreateCustomerCommand;
import binarymash.cloud.shopper.customer.domain.create.CreateCustomerResponse;

public interface CustomerApplicationService {

    CreateCustomerResponse createCustomer(CreateCustomerCommand createCustomerCommand);
}
