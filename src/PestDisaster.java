import java.util.ArrayList;
import java.util.regex.*;

public class PestDisaster {
	
	private long pestDisasterID;
	private String type;
	private String pestName;
	private String alias;
	private String crop;
	private String impactedArea;
	private String color;
	private ArrayList<Solution> solutions;
	
	/**
	 * Constructor 
	 */
	
	public PestDisaster (long pestDisasterID, long initialSolutionID, ArrayList<String> lines) {
		this.pestDisasterID = pestDisasterID;
		this.solutions = new ArrayList<Solution>();
		// read in a block of lines and process
		
		// from the first line, get crop and pestName
		String title[] = lines.get(0).split(">>");
		this.crop = title[1].trim();
		this.pestName = title[2].trim();
		
		// from the third line, get alias
		String yiming[] = lines.get(2).split(":", 2);
		this.alias = yiming[1].trim();
		
		// read in the line for 形态特征, and check if it is 虫害 or 病害, if 虫害, set color = 形态特征
		String xttz[] = lines.get(lines.size()-4).split(":", 2);
		String xttzContent = xttz[1].trim();
		if (!xttzContent.equals("")) { // 形态特征非空，代表是虫害
			this.type = "虫害";
			this.color = xttzContent; //摘入形态特征的内容放入 color 栏下
		}
		else {	// 形态特征若空，代表是病害
			this.type = "病害";
			// 病害的话 read in the line for 危害特征，摘入危害特征的内容放入 color 栏下
			String whtz[] = lines.get(lines.size()-8).split(":", 2);
			String whtzContent = whtz[1].trim();
			this.color = whtzContent;
		}
		
		if (xttzContent.length()>7000) { // 处理异常情况，见 草莓灰斑病， 目测有2项
			impactedArea = "";
			color = "";
			System.out.println(1);
		}
		else {
			// read in the line for 发生原因，摘入发生原因的内容放入 impactedArea 栏下
			String fsyy[] = lines.get(lines.size()-5).split(":", 2);
			String fsyyContent = fsyy[1].trim();
			this.impactedArea = fsyyContent;
			
			// read in the line for 防治方法 and construct solutions
			String fzff[] = lines.get(lines.size()-2).split(":",2);
			String fzffContent = fzff[1].trim();
			// 去除开头可能有的 [防治]
			String regex = "\\[防治\\]";
			Pattern p = Pattern.compile(regex);
			String items[] = p.split(fzffContent);
			fzffContent = items[items.length-1].trim();
			// 用(1),(2),(3)来split字段；作为solution将用来Construct solutions ArrayList
			regex = "\\(\\d\\)";
			String solutions[] = fzffContent.replaceFirst("^"+regex, "").split(regex) ;
			
			for (int i = 0; i < solutions.length; i++) {
				this.solutions.add(new Solution(initialSolutionID+i, this, solutions[i])); //创建 solutionsl类
			}
		}
		
	}
	
	public ArrayList<Solution> getSolutions() {
		return solutions;
	}
	
	public void setSolutions(ArrayList<Solution> solutions) {
		this.solutions = solutions;
	}
	public long getPestDisasterID() {
		return pestDisasterID;
	}
	public void setPestDisasterID(long pestDisasterID) {
		this.pestDisasterID = pestDisasterID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPestName() {
		return pestName;
	}
	public void setPestName(String pestName) {
		this.pestName = pestName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getCrop() {
		return crop;
	}
	public void setCrop(String crop) {
		this.crop = crop;
	}
	public String getImpactedArea() {
		return impactedArea;
	}
	public void setImpactedArea(String impactedArea) {
		this.impactedArea = impactedArea;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
