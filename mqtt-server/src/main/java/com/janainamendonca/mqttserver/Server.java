package com.janainamendonca.mqttserver;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class Server {

	private MqttClient client;

	private int count;

	public Server() {
		try {
			client = new MqttClient("tcp://localhost:1883", "pahomqttserver");
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
	}

	public void start() {
		try {
			client.connect();
			client.setCallback(new MqttCallback() {

				public void messageArrived(String topic, MqttMessage message) throws Exception {
					count++;
					try {
						MqttTopic mqttTopic = client.getTopic("sensor/aviso");
						MqttMessage newMessage = new MqttMessage();
						newMessage.setPayload(("Aviso " + count).getBytes());
						mqttTopic.publish(newMessage);
					} catch (MqttException e) {
						e.printStackTrace();
					}
				}

				public void deliveryComplete(IMqttDeliveryToken arg0) {

				}

				public void connectionLost(Throwable arg0) {

				}
			});

			client.subscribe("sensor/dados");
		} catch (MqttException e1) {
			e1.printStackTrace();
		}

	}

}
