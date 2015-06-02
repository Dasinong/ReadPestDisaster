package filereader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.PetDisSpec;
import model.PetSolu;


public class WeedDusasterReader {

	public static enum Categories {
		description, sympton, form, habbits, rule, solution  
	}
	
	public static void main(String[] args) {
		WeedDusasterReader wReader = new WeedDusasterReader();
//		wReader.readFile();
		
		// Test on sample
		wReader.testFunctions();
//		wReader.testTemp();
		
//		wReader.generateSample();
//		File currentDirectory = new File("").getAbsoluteFile();
//		File file = new File(currentDirectory,"sourcefiles/sample.txt");
//		wReader.readFile(file);
		
	}
	
	public void testTemp(){
		String line = "学名: Echinochloacrusgalli(L．)Beauv．属禾本科一年生草本植物。别名芒早稗、水田草、水稗草等。广布全国各地。主要为害水稻、小麦、玉米、谷子、大豆、蔬菜、果树等农作物。";
		
		// 提取字段"属xxxx。"
		String regex = "(属([^。]+)。)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(line);
		while (m.find()) {
			System.out.println(m.group(1));
		}
	}
	
	public void testFunctions(){
		boolean onSample = false;
		File currentDirectory = new File("").getAbsoluteFile();
		File file;
		if (onSample) {
			file = new File(currentDirectory,"sourcefiles/sample.txt");
		}
		else {
			file = new File(currentDirectory,"sourcefiles/bingchonghai3.txt");
		}
		ArrayList<ArrayList<String>> blocks = generateTextBlocks(file);
		for (ArrayList<String> block : blocks) {
			PetDisSpec petDisSpec = processBlock(block);
			String subType = analyseSubtype(petDisSpec.getDescription());
			System.out.println(subType);
			System.out.println("--------------------------");
		}
		
	}
	
 	public void generateSample(){
		File currentDirectory = new File("");
		File fireFolder = new File(currentDirectory.getAbsoluteFile(), "/sourcefiles");
		
		String fileNameOld = "bingchonghai3.txt";
		File fileOld = new File(fireFolder, fileNameOld);
		String fileNameNew = "sample.txt";
		File fileNew = new File(fireFolder, fileNameNew);
		
		String separator = "--------------------";
		int count = 0;
		int sampleSize = 1;
		
		FileReader fr; FileWriter fw;
		try {
			fr = new FileReader(fileOld); fw = new FileWriter(fileNew);
			BufferedReader br = new BufferedReader(fr); BufferedWriter bw = new BufferedWriter(fw);
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			while ( (line = br.readLine()) != null ) {
				lines.add(line);
				if (line.equals(separator)) { // Process a block of text, separated by the separator "--------------------"
					count++;
					if (count <= sampleSize) {
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

	public void readFile(){
//		ClassLoader classLoader = getClass().getClassLoader();
//		File fileFolder = new File(classLoader.getResource("sourcefiles").getFile());
//		String fileName = "bingchonghai3.txt";
//		File file = new File(fileFolder, fileName);
		File currentDirectory = new File("");
		File fileFolder = new File(currentDirectory.getAbsoluteFile(), "/sourcefiles");
		
		String fileName = "bingchonghai3.txt";
		File file = new File(fileFolder, fileName);
		readFile(file);
	}
	
	public void readFile(File file){
		String separator = "--------------------";
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			while ( (line = br.readLine()) != null ) {
				lines.add(line);
				if (line.equals(separator)) { // Process a block of text, separated by the separator "--------------------"
					// process block of text and return a PetDisSpec and a set of PetSolu
					PetDisSpec petDisSpec = processBlock(lines);
					System.out.println(petDisSpec.toString());
					lines.clear();
				}
			}
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String analyseSubtype(String line){
		// 提取字段"属xxxx。"
		String subType = "";
		String regex = "(属([^。]+))。";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(line);
		if (m.find()) {
			subType = m.group(1);
		}
		else {
			subType = "";
		}
		// 提取 "生物学特性xxxxx，|。"
		regex = "生物学特性([^，。]+)[，。]";
		p = Pattern.compile(regex);
		m = p.matcher(line);
		if (m.find()) {
			subType = m.group(1);
		}
		
		return subType;
	}
	
	public PetDisSpec processBlock(ArrayList<String> lines){
			PetDisSpec petDisSpec = new PetDisSpec();
			// initiallize
			petDisSpec.setType("");
			petDisSpec.setPetDisSpecName("");
			petDisSpec.setAlias("");
			petDisSpec.setCropNames("");
			petDisSpec.setSeverity(0);
			petDisSpec.setCommonImpactonYield(0);
			petDisSpec.setMaxImpactonYield(0);
			petDisSpec.setPreventionorRemedy("");
			petDisSpec.setIndvidualorGroup("");
			petDisSpec.setImpactedArea("");
			petDisSpec.setColor("");
			petDisSpec.setShape("");
			petDisSpec.setDescription("");
			petDisSpec.setSympton("");
			petDisSpec.setForm("");
			petDisSpec.setHabbits("");
			petDisSpec.setRule("");
			Set<PetSolu> petSolus = new HashSet<PetSolu>();
			
			// Get name from line 1
			petDisSpec.setPetDisSpecName(lines.get(0).trim());
			// Base type is automatic 草害
			petDisSpec.setType("草害");
			// TODO: 抓取属xx科 Y年生...植物
			
			// Get 防治农药 from line 2
			String fzny[] = lines.get(2).split(":",2);
			String fznyContent = fzny[1].trim(); 
			String words[] = fznyContent.split("\\s");
			String cPSolution = "使用含以下成分的农药: ";
			for (String string : words) cPSolution += string+",";
			cPSolution.replaceAll(",$", ""); // get rid of the last ","
			// 之后生成一个solution叫做 使用一下成分农药: fznyContent. 注意 fznyContent可能为空
			petSolus.add(new PetSolu(cPSolution, petDisSpec,true,true));
			
			// For the next block of text, get cropNames
			petDisSpec.setCropNames(processCropNames(lines));
			
			// process the bottomBlock of text
			ArrayList<String> bottomBlock = generateBottomBlock(lines);
			
			for (String line : bottomBlock) {
				Set<Categories> categories = keywordCatgorization(generateSectionWords(line));
				
				for (Categories category : categories) {
					switch (category) {
					case description:
						petDisSpec.setDescription(line);
						break;
					case sympton:
						petDisSpec.setSympton(line);
						break;
					case form:
						petDisSpec.setForm(line);
						break;
					case habbits:
						petDisSpec.setHabbits(line);
						break;
					case rule:
						petDisSpec.setRule(line);
						break;
					case solution:
						// 生成一条或者多条petSolu
						ArrayList<String> solutions = generationSoluDes(line);
						for (int i = 0; i < solutions.size(); i++) {
							petSolus.add(new PetSolu(solutions.get(i), petDisSpec,true,true) );
						}
						break;
					default:
						break;
					}
				}
			}
			
			// check for each solution whether isCP
			for (PetSolu petSolu : petSolus) petSolu.setIsCPSolu(isCPSolu(petSolu));
			
			petDisSpec.setPetSolus(petSolus);
			
			return petDisSpec;
	}
	
	public static boolean isCPSolu(PetSolu petSolu) {
		// 下面来判断PetSolu是不是属于CP范畴，暂时方案：如果PetSolu内容含有关键字"液"，则判定为CP方案
		String petSoluDes = petSolu.getPetSoluDes();
		String regex = "液";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(petSoluDes);
		
		return m.find();
	}
	
	public ArrayList<String> generationSoluDes(String line){
		ArrayList<String> petSoluDes = new ArrayList<String>();
		String keyword = generateSectionWords(line);
		String content = generateSectionContent(line);
		if (keyword.equals("防治方法")) {
			String regex = "\\(\\d\\)|\\d、|（\\d）|\\d\\.[\\D]{1}";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(content);
			ArrayList<String> items = new ArrayList<String>();
			ArrayList<Integer> startingPosi = new ArrayList<Integer>();
			while (m.find()) {
				startingPosi.add(m.start());
			}
			if (startingPosi.size()>0) {
				for (int i = 0; i < startingPosi.size()-1; i++) {
					String string = content.substring(startingPosi.get(i), startingPosi.get(i+1));
					items.add(string.replaceAll("^。", ""));
				}
				items.add(content.substring(startingPosi.get(startingPosi.size()-1)).replaceAll("^。", ""));
				petSoluDes.addAll(items);
			}
			else {  // 如果上面只split出来一条记录，则进一步split
				regex = "。?([^。]+?)：";
				p = Pattern.compile(regex);
				m = p.matcher(content);
				items = new ArrayList<String>();
				startingPosi = new ArrayList<Integer>();
				while (m.find()) {
					startingPosi.add(m.start());
				}
				if (startingPosi.size()>0) {
					for (int i = 0; i < startingPosi.size()-1; i++) {
						String string = content.substring(startingPosi.get(i), startingPosi.get(i+1)+1);
						items.add(string.replaceAll("^。", ""));
					}
					items.add(content.substring(startingPosi.get(startingPosi.size()-1)).replaceAll("^。", ""));
					petSoluDes.addAll(items);
				}
				else {
					petSoluDes.add(line);
				}
				
			}
		}
		else if (keyword.equals("防治措施")) {
			String regex = "\\d、";
			String solutions[] = content.replaceFirst("^"+regex, "").split(regex);
			petSoluDes.addAll(Arrays.asList(solutions));
		}
		else if (keyword.equals("综合治理策略")) {
			String regex = "（.?）";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(content);
			ArrayList<String> items = new ArrayList<String>();
			ArrayList<Integer> startingPosi = new ArrayList<Integer>();
			while (m.find()) {
				startingPosi.add(m.start());
			}
			
			if (startingPosi.size()>0) {
				for (int i = 0; i < startingPosi.size()-1; i++) {
					String string = content.substring(startingPosi.get(i), startingPosi.get(i+1));
					items.add(string.replaceAll("^。", ""));
				}
				items.add(content.substring(startingPosi.get(startingPosi.size()-1)).replaceAll("^。", ""));
				petSoluDes.addAll(items);
			}
			else {
				petSoluDes.add(line);
			}
		}
		else {
			petSoluDes.add(line);
		}
		
		return petSoluDes;
	}
	
	public Set<Categories> keywordCatgorization(String keyword){
		Set<Categories> categories = new HashSet<WeedDusasterReader.Categories>();
		
		String descriptionKeywords[] = {"","学名","病原"};
		Set<String> descriptionSet = new HashSet<String>(Arrays.asList(descriptionKeywords));
		if (descriptionSet.contains(keyword)) {
			categories.add(Categories.description);
		}
		
		String symptonKeywords[] = {"症状","危害","主要危害"};
		Set<String> symptonSet = new HashSet<String>(Arrays.asList(symptonKeywords));
		if (symptonSet.contains(keyword)) {
			categories.add(Categories.sympton);
		}
		
		String ruleKeywords[] = {"生境、危害与分布","形态特征与分布","生态特点","传播途径和发病条件"};
		Set<String> ruleSet = new HashSet<String>(Arrays.asList(ruleKeywords));
		if (ruleSet.contains(keyword)) {
			categories.add(Categories.habbits);
			categories.add(Categories.rule);
		}
		
		String formKeywords[] = {"形态特征与分布","病原","形态特征"};
		Set<String> formSet = new HashSet<String>(Arrays.asList(formKeywords));
		if (formSet.contains(keyword)) {
			categories.add(Categories.form);
		}
		
		// 如果keyword里面含有关键字"治"或"防",则属于防治类
		if (keyword.contains("治")||keyword.contains("防")) {
			categories.add(Categories.solution);
		}
		
		return categories;
	}
	
	public String generateSectionWords(String line){
		String segments[] = line.split(":");
		String keyword = segments[0].trim();
		return keyword;
	}
	
	public String generateSectionContent(String line){
		String segments[] = line.split(":");
		String content = segments[segments.length-1].trim();
		return content;
	}
	
	public String processCropNames(ArrayList<String> lines){
		String cropNames = "";
		for (int i = 3; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.trim().equals("")) {
				break;
			}
			String items[] = line.split("---");
			String words[] = items[1].trim().split("\\s");
			for (String string : words) {
				cropNames += string+",";
			}
		}
		// get rid of the last ","
		return cropNames.replaceAll(",$", "");
	}
	
	public ArrayList<String> generateBottomBlock(ArrayList<String> lines){
		ArrayList<String> bottomBlock = new ArrayList<String>();
		int startingRow = lines.size();
		for (int i = 3; i < lines.size(); i++) {
			String line = lines.get(i);
			
			if (line.trim().equals("")) {
				startingRow = i+1;
				break;
			}
		}
		for (int i = startingRow; i < lines.size() ; i++) {
			String line = lines.get(i);
			if (!line.trim().equals("")) { // get rid of empty lines
				bottomBlock.add(line);
			}
		}
		
		return bottomBlock;
	} 
	
	public ArrayList<ArrayList<String>> generateTextBlocks(File file){
		ArrayList<ArrayList<String>> blocks = new ArrayList<ArrayList<String>>();
		String separator = "--------------------";
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			while ( (line = br.readLine()) != null ) {
				lines.add(line);
				if (line.equals(separator)) { // Process a block of text, separated by the separator "--------------------"
					blocks.add(lines);
					lines = new ArrayList<String>();
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
	
}
	
