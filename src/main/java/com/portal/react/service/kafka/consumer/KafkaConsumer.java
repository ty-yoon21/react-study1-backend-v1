package com.portal.react.service.kafka.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.react.persistence.dto.system.SystemMenuDto;
import com.portal.react.persistence.entity.app.system.SystemMenu;
import com.portal.react.persistence.entity.app.system.SystemMenuSub;
import com.portal.react.service.app.system.SystemMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class KafkaConsumer {

    private final SystemMenuService systemMenuService;

    @Autowired
    public KafkaConsumer(SystemMenuService systemMenuService) {
        this.systemMenuService = systemMenuService;
    }

    @KafkaListener(topics= "reactPortalTopic", groupId = "kafkaCousumerTestGroup")
    public void subConsumer(@Headers MessageHeaders headers, @Payload String payload) throws JsonMappingException, JsonProcessingException {

        log.info("TOPIC = '{}'", headers.get("kafka_receivedTopic"));
        log.info("PARTITION_ID = '{}'", headers.get("kafka_receivedPartitionId"));
        log.info("MESSAGE_KEY = '{}'", headers.get("kafka_receivedMessageKey"));
        log.info("OFFSET = '{}'", headers.get("kafka_offset"));
        log.info("GROUP ID = '{}'", headers.get("kafka_groupId"));
        log.info("TIMESTAMP TYPE = '{}'", headers.get("kafka_timestampType"));
        log.info("TIMESTAMP = '{}'", headers.get("kafka_receivedTimestamp"));
        log.info("PUBLISHER = '{}'", headers.get("PUBLISHER"));
        log.info("SUBSCRIBER = '{}'", headers.get("SUBSCRIBER"));
        log.info("Consumer Headers = '{}'", headers.toString());
        log.info("PayLoad = '{}'", payload);

        String topic = String.valueOf(headers.get("kafka_receivedTopic"));
        String offset = String.valueOf(headers.get("kafka_offset"));
        String messageKey = String.valueOf(headers.get("kafka_receivedMessageKey"));

        if(StringUtils.hasText(messageKey) && "SYSTEM_MENU".equals(messageKey)) {
            System.out.println("############### Kafka Consumer : " + messageKey);
            ObjectMapper objectMapper = new ObjectMapper();
            SystemMenuDto systemMenuDto = objectMapper.readValue(payload, SystemMenuDto.class);

            List<SystemMenu> itemsSaved = systemMenuDto.getItemSaved();

            if(!CollectionUtils.isEmpty(systemMenuDto.getItemAdded())){
                if(!CollectionUtils.isEmpty(itemsSaved)){
                    itemsSaved.addAll(systemMenuDto.getItemAdded());
                }else {
                    itemsSaved = systemMenuDto.getItemAdded();
                }
            }

            if(!CollectionUtils.isEmpty(systemMenuDto.getItemEdited())){
                if(!CollectionUtils.isEmpty(itemsSaved)){
                    itemsSaved.addAll(systemMenuDto.getItemEdited());
                }else {
                    itemsSaved = systemMenuDto.getItemEdited();
                }
            }

            //saved
            List<SystemMenuSub> itemSavedSub = new ArrayList<SystemMenuSub>();
            for(SystemMenu source : ListUtils.emptyIfNull(itemsSaved)){
                SystemMenuSub target = new SystemMenuSub();
                BeanUtils.copyProperties(source, target);

                target.setTopic(topic);
                target.setKafkaOffset(offset);
                target.setMessageKey(messageKey);
                itemSavedSub.add(target);
            }
            System.out.println("############### Kafka Consumer SYSTEM_MENU itemSavedSub : " + itemSavedSub);

            //delete
            List<SystemMenu> itemsRemoved =systemMenuDto.getItemRemoved();
            List<SystemMenuSub> itemsRemovedSub = new ArrayList<SystemMenuSub>();
            for(SystemMenu source: ListUtils.emptyIfNull(itemsRemoved)) {
                SystemMenuSub target = new SystemMenuSub();
                BeanUtils.copyProperties(source, target);

                target.setTopic(topic);
                target.setKafkaOffset(offset);
                target.setMessageKey(messageKey);
                itemsRemovedSub.add(target);
            }

            //TODO : pub response

        }//End - if(StringUtils.hasText(messageKey) && "SYSTEM_MENU".equals(messageKey)) {


    }//End - public void subConsumer(@Headers MessageHeaders headers, @Payload String payload) throws JsonMappingException, JsonProcessingException {
}
