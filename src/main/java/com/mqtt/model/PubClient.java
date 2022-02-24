package com.mqtt.model;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;


public class PubClient{

    private MqttClient pubClient;

    private String broker;
    private String topic;

    public PubClient(String broker, String topic) {

            try {
                if (pubClient == null) {
                    this.topic = topic;
                    this.broker = "tcp://" + broker + ":1883";

                    pubClient = new MqttClient(getBroker(), getTopic());
                }
                pubClient.connect();
                System.out.println(this.broker);
            } catch (MqttException e) {
                e.printStackTrace();
            }
    }

    public boolean send(String topic, String msg) {

        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(msg.getBytes());
            pubClient.publish(topic, message);
            System.out.println(topic+ "   " + msg);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void close() {

        try {
            if (pubClient != null) {
                pubClient.disconnect();
                pubClient.close();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
