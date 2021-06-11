package com.tibaestiago.apikafka.controller;

import com.google.gson.Gson;
import com.tibaestiago.apikafka.model.AbstractModel;
import com.tibaestiago.apikafka.model.FieldModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first/kafka")
public class SimpleController {
    private KafkaTemplate<String, String> kafkaTemplate;
    private Gson jsonConverter;

    @Autowired
    public SimpleController(KafkaTemplate<String, String> kafkaTemplate, Gson jsonConverter){
        this.kafkaTemplate = kafkaTemplate;
        this.jsonConverter = jsonConverter;
    }

    @PostMapping
    public void post(@RequestBody FieldModel fieldModel){
        kafkaTemplate.send("myTopic", jsonConverter.toJson(fieldModel));
    }

    @KafkaListener(topics = "myTopic")
    public void getMessageInTopicOfKafka(String fieldModel){
        FieldModel model = (FieldModel) jsonConverter.fromJson(fieldModel, FieldModel.class);
        System.out.println(model.toString());
    }


    @PostMapping("/topic2")
    public void post(@RequestBody AbstractModel abstractModel){
        kafkaTemplate.send("myTopic2", jsonConverter.toJson(abstractModel));
    }

    @KafkaListener(topics = "myTopic2")
    public void getMessageInTopicOfKafka2(String moreSimpleModel){
        AbstractModel abstractModel1 = (AbstractModel) jsonConverter.fromJson(moreSimpleModel, AbstractModel.class);
        System.out.println(abstractModel1.toString());
    }

}
