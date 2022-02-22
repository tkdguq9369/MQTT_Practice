package com.mqtt;

public abstract class MqttConfig {

    protected String ip = "127.0.0.1";
    protected int qos = 0;
    protected boolean hasSSL = false;
    protected Integer port = 1883;
    protected String username = "";
    protected String password = "";
    protected String TCP = "tcp://";
    protected String SSL = "ssl://";

    protected abstract void config(String ip, Integer port, Boolean ssl, Boolean withUserNamePass);

    protected abstract void config();

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public boolean isHasSSL() {
        return hasSSL;
    }

    public void setHasSSL(boolean hasSSL) {
        this.hasSSL = hasSSL;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTCP() {
        return TCP;
    }

    public void setTCP(String TCP) {
        this.TCP = TCP;
    }

    public String getSSL() {
        return SSL;
    }

    public void setSSL(String SSL) {
        this.SSL = SSL;
    }
}
