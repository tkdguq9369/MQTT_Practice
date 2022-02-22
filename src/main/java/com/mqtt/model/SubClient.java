package com.mqtt.model;

import com.google.gson.JsonObject;
import org.eclipse.paho.client.mqttv3.*;

public class SubClient implements MqttCallback {

    private MqttClient subClient;
    private MqttConnectOptions options;

    private String server;
    private String topic;
    private String msg;

    public SubClient init(String server, String topic) {

        try {

            if (subClient == null) {
                this.server  = "tcp://" + server + ":1883";
                this.topic = topic;
                System.out.println(getServer());
                options = new MqttConnectOptions();
                options.setCleanSession(true);
                options.setKeepAliveInterval(30);

                subClient = new MqttClient(getServer(), getTopic());
                subClient.setCallback(this);
                subClient.connect(options);
            } else {
                subClient.connect();
            }

            subscribe(topic);

        } catch (MqttException e) {
            e.printStackTrace();
        }

        return this;
    }


    //연결 끊김
    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("연결이 끊김");
    }

    //메시지 전송 완료 후
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println(" ====== 메시지 도착 ====== ");
        System.out.println(message);
        System.out.println("topic : " + topic + ", id : " + message.getId() + ", payload : " + new String(message.getPayload()));
        System.out.println("{topic:" + topic + ", id:" + message.getId() + ", payload:" + new String(message.getPayload()) +"}");
    }

    public boolean subscribe(String topic) {

        boolean result = true;

        try {
            if (topic != null) {
                subClient.subscribe(topic, 0);
            }
        } catch (MqttException e) {
            e.printStackTrace();
            result = false;
        }

        return result;

    }

    //메시지 도착시 메서드
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    public void close() {

        try {
            if (subClient != null) {
                subClient.disconnect();
                subClient.close();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
