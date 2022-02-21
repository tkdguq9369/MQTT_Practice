package com.mqtt.controller;

import com.mqtt.model.PubClient;
import com.mqtt.model.SubClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    public String pubClient(String ip, String topic, HttpSession session, Model model) {
        System.out.println("postPub");
        System.out.println(ip + "  " + topic);

        pubClient = (PubClient) session.getAttribute("pubClient");

        if(pubClient == null){
            pubClient = new PubClient(ip, topic);
            session.setAttribute("pubClient", pubClient);
        }

        System.out.println(pubClient.toString());

        model.addAttribute("topic", topic);

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


    @GetMapping("/close")
    public String close(HttpSession session) {

        pubClient = (PubClient) session.getAttribute("pubClient");

        pubClient.close();
        pubClient = null;
        session.removeAttribute("pubClient");

        return "redirect:/";

    }
}
