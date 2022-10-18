package binarymash.cloud.shopper.customer.messaging.publisher.kafka;

import binarymash.cloud.shopper.customer.domain.config.CustomerServiceConfigData;
import binarymash.cloud.shopper.customer.domain.event.CustomerCreatedEvent;
import binarymash.cloud.shopper.customer.domain.ports.output.message.publisher.CustomerMessagePublisher;
import binarymash.cloud.shopper.customer.messaging.mapper.CustomerMessagingDataMapper;
import binarymash.cloud.shopper.kafka.avro.model.CustomerAvroModel;
import binarymash.cloud.shopper.kafka.producer.service.KafkaProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerCreatedEventKafkaPublisher implements CustomerMessagePublisher {

    private final CustomerMessagingDataMapper mapper;
    private final KafkaProducer<String, CustomerAvroModel> kafkaProducer;
    private final CustomerServiceConfigData configData;


    @Override
    public void publish(CustomerCreatedEvent customerCreatedEvent) {
        log.info("Received CustomerCreatedEvent for customer id: {}",
                customerCreatedEvent.getCustomer().getId().getValue());
        try {
            var customerAvroModel = mapper.customerCreatedEventToAvroModel(customerCreatedEvent);
            kafkaProducer.send(configData.getCustomerTopicName(), customerAvroModel.getId(), customerAvroModel,
                    getCallback(configData.getCustomerTopicName(), customerAvroModel));
        } catch (Exception e) {
            log.error("Error while sending CustomerCreatedEvent to kafka for customer id: {}," +
                    " error: {}", customerCreatedEvent.getCustomer().getId().getValue(), e.getMessage());
        }
    }

    private ListenableFutureCallback<SendResult<String, CustomerAvroModel>> getCallback(String customerTopicName,
                                                                                        CustomerAvroModel customerAvroModel) {
        return new ListenableFutureCallback<SendResult<String, CustomerAvroModel>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending message {} to topic {}", customerAvroModel.toString(), customerTopicName, ex);
            }

            @Override
            public void onSuccess(SendResult<String, CustomerAvroModel> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received new metadata. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            }
        };
    }
}
