package com.mqtt.model;

import com.mqtt.model.mqttInterface.Client;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;


public class PubClient implements Client {

    private MqttClient pubClient;

    private String broker;
    private String topic;

    public PubClient(String broker, String topic) {


            try {

                if(pubClient != null){
                    pubClient.connect();
                } else {
                    this.broker = broker;
                    this.topic = topic;

                    broker = "tcp://"+broker+":1883";
                    System.out.println(broker);
                    pubClient = new MqttClient(broker, topic);
                    pubClient.connect();
                }

            } catch (MqttException e) {
                e.printStackTrace();
            }
    }

    public boolean send(String topic, String msg) {

        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(msg.getBytes());
            pubClient.publish(topic, message);
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


}
