package binarymash.cloud.shopper.customer.data.adaptor;

import binarymash.cloud.shopper.customer.data.mapper.CustomerDataAccessMapper;
import binarymash.cloud.shopper.customer.data.repository.CustomerJpaRepository;
import binarymash.cloud.shopper.customer.domain.entity.Customer;
import binarymash.cloud.shopper.customer.domain.ports.output.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerDataAccessMapper mapper;

    @Override
    public Customer createCustomer(final Customer customer) {
        return mapper.customerEntityToCustomer(customerJpaRepository.save(mapper.customerToCustomerEntity(customer)));
    }
}
