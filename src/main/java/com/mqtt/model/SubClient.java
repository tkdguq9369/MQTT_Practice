package com.mqtt.model;


import org.eclipse.paho.client.mqttv3.*;

public class SubClient implements MqttCallback {

    private MqttClient subClient;
    private MqttConnectOptions options;


    public SubClient init(String broker, String clientId) {

        try {

            options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setKeepAliveInterval(30);

            subClient = new MqttClient(broker, clientId);

            subClient.setCallback(this);
            subClient.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return this;
    }


    //연결 끊김
    @Override
    public void connectionLost(Throwable cause) {

    }

    //메시지 전송 완료 후
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println(" ====== 메시지 도착 ====== ");
        System.out.println(message);
        System.out.println("topic : " + topic + ", id : " + message.getId() + ", payload : " + new String(message.getPayload()));
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
}
