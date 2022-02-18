package com.mqtt.controller;

import com.mqtt.model.PubClient;
import com.mqtt.model.SubClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/mqtt")
public class MqttController {



    private PubClient pubClient;
    private SubClient subClient;

    @GetMapping("/pubClient")
    public String getPubClientInfo() {

        System.out.println("getPub");
        return "mqttClient/pub/publishForm";
    }

    @PostMapping("/pubClient")
    public String pubClient(String ip, String topic, Model model) {
        System.out.println("postPub");
        System.out.println(ip + "  " + topic);

        pubClient = new PubClient(ip, topic);

        System.out.println(pubClient.toString());

        model.addAttribute("pubClient", pubClient);


        return "mqttClient/pub/publish";
    }
    @GetMapping("/subClient")
    public String getSubClientInfo() {

        System.out.println("getSub");
        return "mqttClient/sub/subscribeForm";
    }

    @PostMapping("/subClient")
    public String subClient(String ip, String topic) {
        System.out.println("postSub");
        System.out.println(ip + "  " + topic);
        return "mqttClient/sub/subscribe";
    }
}
