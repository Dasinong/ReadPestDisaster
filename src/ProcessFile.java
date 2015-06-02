import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ProcessFile {

	public static void main(String[] args) {
		File currentDirectory = new File("");
		File fireFolder = new File(currentDirectory.getAbsoluteFile(), "/sourcefiles");
		
		String fileNameOld = "bingchonghai2.txt";
		File fileOld = new File(fireFolder, fileNameOld);
		String fileNameNew = "bingchonghai3.txt";
		File fileNew = new File(fireFolder, fileNameNew);
		
		String separator = "--------------------";
		FileReader fr; FileWriter fw;
		try {
			fr = new FileReader(fileOld); fw = new FileWriter(fileNew);
			BufferedReader br = new BufferedReader(fr); BufferedWriter bw = new BufferedWriter(fw);
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			while ( (line = br.readLine()) != null ) {
				lines.add(line);
				if (line.equals(separator)) { // Process a block of text, separated by the separator "--------------------"
					// check if it is 草害
					// 从第二行得到, type
					String ssfl[] = lines.get(1).split(":", 2);
					String type = ssfl[1].trim();
					if (type.equals("草害")) {
						for (int i = 0; i < lines.size(); i++) {
							bw.write(lines.get(i)+"\n");
						}
					}
					
					lines.clear();
				}
			}
			bw.flush();
			bw.close();
			fw.close();
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
