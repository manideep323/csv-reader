package com.mani.csv_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class App 
{
	static String appPath = new File("files").getAbsolutePath();
	static File inputFile = new File(appPath+"\\inputFile.txt");
	static File outputFile = new File(appPath+"\\outputFile.txt");
	static String[] header = { "B", "12/25/2019", "03:50","","","" }; 
	static String CONSTANT_PAPERWORK_HEADER = "PWK";
	static Map< String,String> hm =  new HashMap< String,String>();
	
    public static void main( String[] args ) {
    	hm.put("45", "Breakfast$");
    	hm.put("43", "LaredoTaco$");
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
             writer.writeNext(new String[] {});
             writer.writeNext(new String[] {});
             
             reader = new CSVReader(new FileReader(inputFile));
             String[] line;
             reader.skip(1);
             while ((line = reader.readNext()) != null) {
                 Set< Map.Entry< String,String> > st = hm.entrySet();    
                 String[] data = {CONSTANT_PAPERWORK_HEADER,line[1],line[2]}; 
                 writer.writeNext(data);
                 
                 for (Entry<String, String> me:st) 
                 { 
                	 if(line[2].equals(me.getKey())) {
                		 writer.writeNext(new String[] {me.getValue()});
                	 }
                 } 
                 writer.writeNext(new String[] {});
             }
             writer.close(); 
         } catch (IOException e) {
             e.printStackTrace();
         }

    }
}
