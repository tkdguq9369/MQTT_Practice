package com.mqtt.controller;

import com.mqtt.model.PubClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/mqtt")
public class RestMqttController {

    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

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

    @CrossOrigin
    @RequestMapping(value="/subscribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        emitters.add(sseEmitter);

        return sseEmitter;
    }

    @PostMapping(value = "/dispatchEvent")
    public void dispatchEventToClients(@RequestParam String test) {
        for(SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().data(test));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }

}
