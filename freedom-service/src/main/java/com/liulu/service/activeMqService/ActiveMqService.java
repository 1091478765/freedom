package com.liulu.service.activeMqService;

import javax.jms.Destination;

/**
 * Created by 刘璐 on 2018/9/11.
 */
public interface ActiveMqService {

    public void sendMessage(String queueName,String message);

    public void receiveQueue(String text ) throws Exception;
}
