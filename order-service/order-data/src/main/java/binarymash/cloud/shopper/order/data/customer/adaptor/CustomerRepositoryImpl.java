package binarymash.cloud.shopper.order.data.customer.adaptor;

import binarymash.cloud.shopper.domain.ports.output.repository.CustomerRepository;
import binarymash.cloud.shopper.order.data.customer.mapper.CustomerDataMapper;
import binarymash.cloud.shopper.order.data.customer.repository.CustomerJpaRepository;
import binarymash.cloud.shopper.order.domain.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository jpaRepository;
    private final CustomerDataMapper mapper;

    @Override
    public Optional<Customer> findCustomer(final UUID customerId) {
        return jpaRepository.findById(customerId).map(mapper::customerEntityToCustomer);
    }

    @Override
    public Customer save(final Customer customer) {
        return mapper.customerEntityToCustomer(
                jpaRepository.save(mapper.customerToCustomerEntity(customer))
        );
    }
}
