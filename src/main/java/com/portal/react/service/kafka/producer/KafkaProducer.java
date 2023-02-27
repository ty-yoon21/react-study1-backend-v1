package com.portal.react.service.kafka.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.react.persistence.dto.system.SystemMenuDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaProducer {

    private static final String TOPIC = "reactPortalTopic";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {this.kafkaTemplate = kafkaTemplate;}



    public void pubSystemMenuInfo(SystemMenuDto dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Message<String> message = MessageBuilder
                .withPayload(objectMapper.writeValueAsString(dto))
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .setHeader(KafkaHeaders.MESSAGE_KEY, "SYSTEM_MENU")
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
                .setHeader("PUBLISHER", "ReactPortalBack")
                .setHeader("SUBSCRIBER", "ALL")
                .build();

        log.info("Sending Msg='{}' to topic='{}'", new ObjectMapper().writeValueAsString(dto), TOPIC);

        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent Message='{}' ", message);
                log.info("offset = '{}' ", result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {

            }
        });
    }

}
