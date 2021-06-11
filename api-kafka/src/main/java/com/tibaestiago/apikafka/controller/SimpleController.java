package com.tibaestiago.apikafka.controller;

import com.google.gson.Gson;
import com.tibaestiago.apikafka.model.AbstractModel;
import com.tibaestiago.apikafka.model.FieldModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/first/kafka")
public class SimpleController {

    public static final String TOPIC_01 = "myTopic";
    public static final String TOPIC_02 = "myTopic2";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Gson jsonConverter;


    @PostMapping
    public void post(@RequestBody FieldModel fieldModel){
        log.info("Send (Producer) message of topic 1: " + fieldModel.toString());
        kafkaTemplate.send(TOPIC_01, jsonConverter.toJson(fieldModel));
    }

    @KafkaListener(topics = TOPIC_01)
    public void getMessageInTopicOfKafka(String fieldModel){
        FieldModel model = (FieldModel) jsonConverter.fromJson(fieldModel, FieldModel.class);
        log.info("Consumer message of topic 1: " + model.toString());
    }


    @PostMapping("/topic2")
    public void post(@RequestBody AbstractModel abstractModel){
        log.info("Send (Producer) message of topic 2: " + abstractModel.toString());
        kafkaTemplate.send(TOPIC_02, jsonConverter.toJson(abstractModel));
    }

    @KafkaListener(topics = TOPIC_02)
    public void getMessageInTopicOfKafka2(String abstractModel){
        AbstractModel abstractModel1 = (AbstractModel) jsonConverter.fromJson(abstractModel, AbstractModel.class);
        log.info("Consumer message of topic 2: " + abstractModel1.toString());
    }

}