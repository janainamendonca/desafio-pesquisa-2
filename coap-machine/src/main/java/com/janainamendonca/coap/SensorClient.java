package com.janainamendonca.coap;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class SensorClient {

	public void sendMessage(String message) {

		CoapClient client = new CoapClient("coap://192.168.1.5/sensor");

		CoapResponse response = client.post(message, MediaTypeRegistry.TEXT_PLAIN);
		if (response != null) {
			System.out.println(response.getResponseText());
		} else {
			System.out.println("Request failed");

		}

	}

}
