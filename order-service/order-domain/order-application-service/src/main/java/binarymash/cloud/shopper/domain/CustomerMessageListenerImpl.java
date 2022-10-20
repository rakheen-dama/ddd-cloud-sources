package binarymash.cloud.shopper.domain;

import binarymash.cloud.shopper.domain.dto.message.CustomerModel;
import binarymash.cloud.shopper.domain.mapper.OrderDataMapper;
import binarymash.cloud.shopper.domain.ports.input.message.listener.customer.CustomerMessageListener;
import binarymash.cloud.shopper.domain.ports.output.repository.CustomerRepository;
import binarymash.cloud.shopper.order.domain.exception.OrderDomainException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerMessageListenerImpl implements CustomerMessageListener {

    private final CustomerRepository customerRepository;
    private final OrderDataMapper orderDataMapper;

    @Override
    public void customerCreated(final CustomerModel customerModel) {
        var customer = customerRepository.save(orderDataMapper.customerModelToCustomer(customerModel));
        if (customer == null) {
            log.error("Customer could not be created in order database with id: {}", customerModel.id());
            throw new OrderDomainException("Customer could not be created in order database with id " +
                    customerModel.id());
        }
        log.info("Customer is created in order database with id: {}", customer.getId());
    }
}
