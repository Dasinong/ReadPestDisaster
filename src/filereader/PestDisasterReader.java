package filereader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.PetDisSpec;
import model.PetSolu;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class PestDisasterReader {
	
	public static void readFile(){
		File directory = new File("sourcefiles");
		File file = new File(directory,"bingchonghai_all.txt");
		readFile(file);
	}
	
	public static void readFile(File file){
		String separator = "--------------------";
		LineIterator it = null;
		try {
			it = FileUtils.lineIterator(file, "UTF-8");
			ArrayList<String> lines = new ArrayList<String>();
			while (it.hasNext()) {
				String line = (String) it.next();
				lines.add(line);
				
				if (line.equals(separator)) { // Process a block of text, separated by the separator "--------------------"
					PetDisSpec petDisSpec = createPetDisSpec(lines);
					
					lines.clear();
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			it.close();
		}
	}
	
	public static PetDisSpec createPetDisSpec(ArrayList<String> lines) {
		PetDisSpec petDisSpec = new PetDisSpec();
		boolean isException = false; // 处理异常情况，见 草莓灰斑病， 目测有6项
		
		// 从第一行得到，petDisSpecName 名称; crops 作物; 注意:病害和草害一种disaster对应一种crop
		String title[] = lines.get(0).split(">>");
		petDisSpec.setCropNames(title[1].trim());
		petDisSpec.setPetDisSpecName(title[2].trim());
		
		// 从第三行得到, alias 异名
		String yiming[] = lines.get(2).split(":", 2);
		petDisSpec.setAlias(yiming[1].trim());
		
		// read in the line for 形态特征, and check if it is 虫害 or 病害, if 虫害, set color = 形态特征
		String xttz[] = lines.get(lines.size()-4).split(":", 2);
		String xttzContent = xttz[1].trim();
		if (!xttzContent.equals("")) { // 形态特征非空，代表是虫害
			petDisSpec.setType("虫害");
		}
		else {	// 形态特征若空，代表是病害
			petDisSpec.setType("病害");
		}
		
		// 暂时性的，危害程度，平均减产量，最高减产量，防为主还是治为主，个体还是群体，发生部位，颜色，形状，简介；都设置为空，int类型都设为0
		petDisSpec.setSeverity(0);
		petDisSpec.setCommonImpactonYield(0);
		petDisSpec.setMaxImpactonYield(0);
		petDisSpec.setPreventionorRemedy("");
		petDisSpec.setIndvidualorGroup("");
		petDisSpec.setImpactedArea("");
		petDisSpec.setColor("");
		petDisSpec.setShape("");
		petDisSpec.setDescription("");
		
		// check whether isException, 若异常，下面都为空，若正常，则继续读取数据
		if (xttzContent.length() > 7000 ) {
			isException = true;
			petDisSpec.setType("");
			petDisSpec.setSympton("");
			petDisSpec.setForm("");
			petDisSpec.setHabbits("");
			petDisSpec.setRule("");
		}
		else {
			String whtz[] = lines.get(lines.size()-8).split(":", 2); // 读取"危害症状"
			String whtzContent = whtz[1].trim();
			String qrxh[] = lines.get(lines.size()-6).split(":", 2); // 读取"侵染循环"
			String qrxhContent = qrxh[1].trim();
			String fsyy[] = lines.get(lines.size()-5).split(":", 2); // 读取"发生原因"
			String fsyyContent = fsyy[1].trim();
			String shxx[] = lines.get(lines.size()-3).split(":", 2); // 读取"生活习性"
			String shxxContent = shxx[1].trim();
			String fzff[] = lines.get(lines.size()-2).split(":",2 ); // 读取"防治方法"
			String fzffContent = fzff[1].trim();
			
			petDisSpec.setSympton(whtzContent);
			petDisSpec.setRule(fsyyContent);
			
			// 开始按照 病害/虫害 填表
			if (petDisSpec.getType().equals("病害")) {
				petDisSpec.setForm(qrxhContent);
				petDisSpec.setHabbits(qrxhContent);
			}
			else if (petDisSpec.getType().equals("虫害")) {
				petDisSpec.setForm(xttzContent);
				petDisSpec.setHabbits(shxxContent);
			}
			
			// 最后从"防治方法"中，分离出solutions，构造petSolu集合
			String regex = "\\[防治\\]"; // 去除开头可能有的 [防治]
			Pattern p = Pattern.compile(regex);
			String items[] = p.split(fzffContent);
			fzffContent = items[items.length-1].trim();
			// 用(1),(2),(3)来split字段；作为solution将用来Construct solutions ArrayList
			regex = "\\(\\d\\)";
			String solutions[] = fzffContent.replaceFirst("^"+regex, "").split(regex);
			for (int i = 0; i < solutions.length; i++) {
				PetSolu petSolu = new PetSolu(solutions[i], petDisSpec, true, true);
				petSolu.setIsCPSolu(isCPSolu(petSolu)); // 用isCPSolu方法查看是否和农药相关
				petDisSpec.getPetSolus().add(petSolu); //创建 PetSolu类
			}
		}
		
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
}
