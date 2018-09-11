package com.liulu.service.activeMqService.impl;

import com.liulu.service.activeMqService.ActiveMqService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * Created by 刘璐 on 2018/9/11.
 */

@Service
public class ActiveMqServiceImpl implements ActiveMqService {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Override
    public void sendMessage(String queueName,String message){

        Destination destination = new ActiveMQQueue(queueName);

        jmsTemplate.convertAndSend(destination, message);
    }

    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text ) throws Exception{
        Thread.sleep(10000);
        System.out.println("接受到的消息为123："+ text);
    }
}
