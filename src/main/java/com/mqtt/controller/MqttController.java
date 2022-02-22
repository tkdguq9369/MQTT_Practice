package com.mqtt.controller;

import com.mqtt.model.PubClient;
import com.mqtt.model.SubClient;
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
        model.addAttribute("pubClient", pubClient);
        return "mqttClient/pub/publish";
    }


    @GetMapping("/subClient")
    public String getSubClientInfo() {

        System.out.println("getSub");
        return "mqttClient/sub/subscribeForm";
    }

    @PostMapping("/subClient")
    public String subClient(String ip, String topic, HttpSession session, Model model) {
        System.out.println("postSub");
        System.out.println(ip + "  " + topic);

        subClient = (SubClient) session.getAttribute("subClient");

        if(subClient == null){

            subClient = new SubClient();
            subClient.init(ip, topic);

            session.setAttribute("subClient", subClient);
        }

        System.out.println(subClient.toString());

        model.addAttribute("topic", topic);
        model.addAttribute("subClient", subClient);
        return "mqttClient/sub/subscribe";
    }


    @GetMapping("/close_pub")
    public String close_pub(HttpSession session) {

        pubClient = (PubClient) session.getAttribute("pubClient");

        pubClient.close();
        pubClient = null;
        session.removeAttribute("pubClient");

        return "redirect:/";

    }

    @GetMapping("/close_sub")
    public String close_sub(HttpSession session) {

        subClient = (SubClient) session.getAttribute("subClient");
        subClient.close();
        subClient = null;
        session.removeAttribute("subClient");

        return "redirect:/";

    }
}
