package com.rokin.csv;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CsvWriteTask implements Runnable {

	private SynchronizedPrinter printer;

	public CsvWriteTask(SynchronizedPrinter printer) {
		this.printer = printer;
	}

	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				List<String> record = Arrays.asList(UUID.randomUUID().toString(), randomString());
				printer.printRecord(record);
			}
			System.out.println("Invdividual CSV writing thread complete");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String randomString() {
		String[] names = { "Rokin", "Human", "Animals", "Birds", "Ukulele", "Chair", "Desk", "Charger" };
		return names[new Random().nextInt(names.length)];

	}

}
