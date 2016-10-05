package com.janainamendonca.mqttmachine;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MessageSender {

	private MqttClient client;

	public MessageSender() {
		try {
			client = new MqttClient("tcp://192.168.1.5:1883", "pahomqttpublish1");
			client.connect();
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}

		client.setCallback(new MqttCallback() {

			public void messageArrived(String topic, MqttMessage message) throws Exception {
				System.out.println(new String(message.getPayload()));
			}

			public void deliveryComplete(IMqttDeliveryToken arg0) {

			}

			public void connectionLost(Throwable arg0) {

			}
		});

		try {
			client.subscribe("sensor/aviso");
		} catch (MqttException e1) {
			e1.printStackTrace();
		}

	}

	public void sendMessage(String data) {
		try {
			MqttMessage message = new MqttMessage();
			message.setPayload(data.getBytes());
			client.publish("sensor/dados", message);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

}
