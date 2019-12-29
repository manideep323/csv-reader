package com.mani.csv_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class App {
	static String appPath = new File("files").getAbsolutePath();
	static File inputFile = new File(appPath + "\\inputFile.txt");
	static File outputFile = new File(appPath + "\\outputFile.txt");
	static String[] header = { "B", "12/25/2019", "03:50", "", "", "" };
	static Map<String, String> hm = new HashMap<String, String>();
	public static final String NEW_LINE_SEPARATOR = "\r\n";
	public static final String COMMA_DELIMITER = ",";
	public static final int DATE_IDX = 0;
	public static final int LOCATION_IDX = 1;
	public static final int ITEM_TYPE_IDX = 2;
	public static final int TOTALSALES_IDX = 3;
	public static final int INPUT_TOKEN_LENGTH = 4;

	public static final String CONSTANT_ZERO = "0";
	public static final String CONSTANT_ONE = "1";
	public static final String CONSTANT_BLANK = "";
	public static final String CONSTANT_LAREDO_TACO = "LaredoTaco$";

	public static final String CONSTANT_BATCH_HEADER = "B";
	public static final String CONSTANT_PAPERWRK_HEADER = "PWK";
	public static final String CONSTANT_FORMS_DATA = "DID";
	public static final String CONSTANT_PAPERWRK_TRAILER = "PWKZ";
	public static final String CONSTANT_BATCH_TRAILER = "Z";

	public static void main(String[] args) {
		hm.put("45", "Breakfast$");
		hm.put("43", "LaredoTaco$");
		hm.put("46", "Roost$");
		readFile(inputFile, outputFile);
	}

	private static void readFile(File inputFile, File outputFileName) {
		CSVReader reader = null;
		try {
			FileWriter outputfile = new FileWriter(outputFileName);
			CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			writer.writeNext(header);
			writer.writeNext(new String[] {});
			writer.writeNext(new String[] {});

			reader = new CSVReader(new FileReader(inputFile));
			String[] line;
			reader.skip(1);
			ArrayList<String[]> inputData = new ArrayList<String[]>();
			TreeSet<String> location = new TreeSet<String>();
			
			while ((line = reader.readNext()) != null) {
				inputData.add(new String[] { line[0], line[1], line[2], line[3] });
				location.add(line[1]);
			}
			for (String distnicValues : location) {
				int index = 0;
				for (String[] data : inputData) {
					if(index==0) {
					String[] prefix = { CONSTANT_PAPERWRK_HEADER, distnicValues, data[0]};
					writer.writeNext(prefix);
					}
					index++;
					if (distnicValues.equals(data[1])) {
						writer.writeNext(new String[] {CONSTANT_FORMS_DATA,CONSTANT_ZERO,CONSTANT_BLANK,hm.get(data[2]),data[3]});
					}
					if(index==inputData.size()) {
						writer.writeNext(new String[]{ CONSTANT_PAPERWRK_TRAILER, distnicValues, data[0],CONSTANT_ZERO,CONSTANT_ZERO,CONSTANT_ZERO,CONSTANT_ZERO,CONSTANT_ZERO,CONSTANT_ZERO,CONSTANT_ZERO,CONSTANT_ZERO,CONSTANT_ONE,CONSTANT_ZERO});
						writer.writeNext(new String[] {});
						writer.writeNext(new String[] {});
					}
					
				}
			}
			writer.writeNext(new String[] {"Z"});
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
