package filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class StepReader {
	public final static String SEPARATOR = "||||";
	public final static File CURRENTFOLDER = new File("").getAbsoluteFile();
	public final static File FILE_STAGE = new File(CURRENTFOLDER, "sourcefiles/stage.csv");
	public final static File FILE_STEP = new File(CURRENTFOLDER, "sourcefiles/task_step.csv");

	public static void main(String[] args) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(FILE_STEP), ',', '\"',1);  
		List entries = reader.readAll();
		for (int i = 0; i < entries.size(); i++) {
			String items[] = (String[]) entries.get(i);
			System.out.println(items[7]);
		}
	}

}
