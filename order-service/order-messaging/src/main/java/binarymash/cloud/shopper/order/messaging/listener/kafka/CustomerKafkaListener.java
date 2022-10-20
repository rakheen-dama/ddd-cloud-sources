package binarymash.cloud.shopper.order.messaging.listener.kafka;

import binarymash.cloud.shopper.domain.ports.input.message.listener.customer.CustomerMessageListener;
import binarymash.cloud.shopper.kafka.avro.model.CustomerAvroModel;
import binarymash.cloud.shopper.kafka.consumer.KafkaConsumer;
import binarymash.cloud.shopper.order.messaging.mapper.OrderDataMessageMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerKafkaListener implements KafkaConsumer<CustomerAvroModel> {

    private final CustomerMessageListener customerMessageListener;
    private final OrderDataMessageMapper orderDataMessageMapper;

    @Override
    public void receive(@Payload List<CustomerAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.debug("{} number of customer create messages received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(
                customerAvroModel -> customerMessageListener.customerCreated(orderDataMessageMapper.customerAvroModelToCustomerModel(customerAvroModel))
        );
    }
}
