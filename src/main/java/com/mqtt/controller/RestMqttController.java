package com.mqtt.controller;

import com.mqtt.model.PubClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/mqtt")
public class RestMqttController {


    @RequestMapping("/send")
    public boolean sendMsg(@RequestParam(value = "topic") String topic, @RequestParam(value = "msg") String msg,
                            HttpSession session) {
        PubClient pubClient = (PubClient) session.getAttribute("pubClient");

        boolean sendResult;

        try{
            sendResult = pubClient.send(topic, msg);

        } catch (Exception e){
            e.printStackTrace();
            sendResult = false;
        }

        return sendResult;
    }


}
