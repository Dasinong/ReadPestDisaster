package filereader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Crop;
import model.Variety;

import com.mysql.fabric.xmlrpc.base.Array;

public class VarietyReader {
	public final static String SEPARATOR = "--------------------";
	public final static File CURRENTFOLDER = new File("").getAbsoluteFile();
	public final static File FILE = new File(CURRENTFOLDER, "sourcefiles/种子库.txt");
	public final static File SAMPLE_FILE = new File(CURRENTFOLDER, "sources/种子库_sample.txt");
	
	public static void main(String[] args) {
		VarietyReader vr = new VarietyReader();
		vr.testPiece();
	}
	
	public void testPiece(){
		generateSampleFile();
	}
	
	public void readFile(File file){
		ArrayList<ArrayList<String>> blocks = generateBlocks(file);
		for (int i = 0; i < blocks.size(); i++) {
			ArrayList<String> block = blocks.get(i);
		}
	}
	
	public void readFile(){
		File currentFolder = new File("").getAbsoluteFile();
		File file = new File(currentFolder, "sourcefiles/种子库.txt");
		readFile(file);
	}
	
	public Variety processBlock(ArrayList<String> block){
		Variety variety = new Variety();
// LINE 1: cropName and generate crop
		String lineSegments[] = block.get(0).split("", 2);
		String cropName = lineSegments[1].trim();
		variety.setCropName(cropName);
		Crop crop = new Crop(cropName);
		
		return variety;
	}
	
	public void printBlock(ArrayList<String> lines){
		for (int i = 0; i < lines.size(); i++) {
			System.out.println(lines.get(i));
		}
	}
	
	public ArrayList<ArrayList<String>> generateBlocks(File file){
		ArrayList<ArrayList<String>> blocks = new ArrayList<ArrayList<String>>();
		try {
			FileReader fr = new FileReader(FILE);
			BufferedReader br = new BufferedReader(fr);
			ArrayList<String> block = new ArrayList<String>();
			String line;
			while ((line=br.readLine())!=null) {
				block.add(line);
				if (line.equals(SEPARATOR)) {
					blocks.add(block);
					block = new ArrayList<String>();
				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return blocks;
	}
	
	public void generateSampleFile(){
		try {
			FileReader fr = new FileReader(FILE);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(SAMPLE_FILE);
			BufferedWriter bw = new BufferedWriter(fw);
			int blockCount = 0;
			int sampleSize = 1;
			
			String line;
			while ( (line = br.readLine() )!=null) {
				bw.write(line+"\n");
				if (line.equals(SEPARATOR)) {
					blockCount++;
					if (blockCount == sampleSize) {
						break;
					}
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
