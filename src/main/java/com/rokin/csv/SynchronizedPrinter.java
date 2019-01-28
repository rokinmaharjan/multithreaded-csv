package com.rokin.csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class SynchronizedPrinter {
	private CSVPrinter printer;

	public SynchronizedPrinter(BufferedWriter writer, CSVFormat csvFormat) throws IOException {
		this.printer = new CSVPrinter(writer, csvFormat);
	}

	public void close() throws IOException {
		this.printer.close();
	}

	public synchronized void printRecord(List<String> record) throws IOException {
		this.printer.printRecord(record);
	}
}
