package binarymash.cloud.shopper.order.service;

import binarymash.cloud.shopper.order.domain.OrderDomainService;
import binarymash.cloud.shopper.order.domain.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
