package com.rokin.csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;

public class SynchronizedCsv {

	private static final String FILE_PATH = "/home/lt88/Desktop/text.csv";

	public static void main(String[] args) throws InterruptedException, IOException {
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH));
		SynchronizedPrinter syncPrinter = new SynchronizedPrinter(writer, CSVFormat.EXCEL.withHeader("ID", "Name"));

		System.out.println("Running CSV writing process in seperate threads");
		for (int i = 0; i < 10; i++) {
			executor.execute(new CsvWriteTask(syncPrinter));
		}

		executor.shutdown();
		while (executor.awaitTermination(60, TimeUnit.SECONDS)) {
			if (Thread.currentThread().getName().equalsIgnoreCase("main") && Thread.activeCount() == 1) {
				break;
			}
		}
		
		System.out.println("CSV writing process complete");
		syncPrinter.close();
	}
}
