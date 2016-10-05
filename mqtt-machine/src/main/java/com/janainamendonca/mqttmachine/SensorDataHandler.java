package com.janainamendonca.mqttmachine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class SensorDataHandler {

	private MessageSender messageSender = new MessageSender();

	// ler os dados a cada 300 ms

	public void start() {

		try {
			File dir = new File("c:/dados");
			File[] files = dir.listFiles();

			for (File file : files) {

				List<String> allLines = Files.readAllLines(file.toPath());

				allLines.remove(0); // remove a linha do cabeçalho

				for (String line : allLines) {
					messageSender.sendMessage(line);
					Thread.sleep(300);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
