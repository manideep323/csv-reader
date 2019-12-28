package com.mani.csv_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class App 
{
	static String appPath = new File("files").getAbsolutePath();
	static File inputFile = new File(appPath+"\\inputFile.txt");
	static File outputFile = new File(appPath+"\\outputFile.txt");
	static String[] header = { "adding", "headder", "here" }; 
	
    public static void main( String[] args ) {
    	readFile(inputFile,outputFile);
    }
    private static void readFile(File inputFile, File outputFileName) {
    	 CSVReader reader = null;
         try {
             FileWriter outputfile = new FileWriter(outputFileName); 
             CSVWriter writer = new CSVWriter(outputfile, ',', 
                     CSVWriter.NO_QUOTE_CHARACTER, 
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                     CSVWriter.DEFAULT_LINE_END);
             writer.writeNext(header); 
             reader = new CSVReader(new FileReader(inputFile));
             String[] line;
             reader.skip(1);
             while ((line = reader.readNext()) != null) {
                 System.out.println("date =" + line[0] + "location = " + line[1] + " psa=" + line[2] + line[3] );
                 
                 
                 String[] data1 = {line[0],line[0]}; 
                 writer.writeNext(data1); 
             }
             writer.close(); 
         } catch (IOException e) {
             e.printStackTrace();
         }

    }
}
