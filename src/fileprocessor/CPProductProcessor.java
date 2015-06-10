package fileprocessor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CPProductProcessor {

	public static void main(String[] args) {
		
		Map<String, String> productModelMap = new HashMap<String, String>();
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader("sourcefiles/model.csv"),',', '\"',1);
			 List myEntries = reader.readAll();
			 
			for (int i = 0; i < myEntries.size(); i++) {
				String items[] = (String[]) myEntries.get(i);
				productModelMap.put(items[0].trim(), items[1].trim());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// finished constructing the product model map
		ArrayList<String[]> outputList = new ArrayList<String[]>();
	    try {
			reader = new CSVReader(new FileReader("sourcefiles/CPProduct.csv"),',', '\"',1);
			List myEntries = reader.readAll();
			for (int i = 0; i < myEntries.size(); i++) {
				String items[] = (String[]) myEntries.get(i);
				List<String> itemsList = Arrays.asList(items);
				itemsList = new ArrayList<String>(itemsList);
				if (productModelMap.keySet().contains(items[0])) {
					itemsList.add(productModelMap.get(items[0]));
				}
				else {
					itemsList.add("");
				}
				String[] output = itemsList.toArray(new String[itemsList.size()]);
				outputList.add(output);
			}
			reader.close();
			CSVWriter writer = new CSVWriter(new FileWriter("sourcefiles/CPProduct_modified.csv"));
			writer.writeAll(outputList);
			writer.flush();
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
//	    CSVWriter writer = new CSVWriter(new FileWriter(""));
		
	}

}
