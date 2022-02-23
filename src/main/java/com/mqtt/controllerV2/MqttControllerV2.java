package com.mqtt.controllerV2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mqtt/v2")
public class MqttControllerV2 {

    @GetMapping("/pub")
    public String pubForm(){
        return "mqttClientV2/pub/publishForm";
    }

    @PostMapping("/pub")
    public String pubClient(String ip, String topic, Model model) {

        model.addAttribute("ip", ip);
        model.addAttribute("topic", topic);

        return "mqttClientV2/pub/publish";
    }

    @GetMapping("/sub")
    public String subForm(){

        return "mqttClientV2/sub/subscribeForm";
    }

    @PostMapping("/sub")
    public String subClient(String ip, String topic, Model model) {
        model.addAttribute("ip", ip);
        model.addAttribute("topic", topic);
        return "mqttClientV2/sub/subscribe";
    }
}
