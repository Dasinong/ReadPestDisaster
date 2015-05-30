import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Solution {
	private long solutionID;
	private String solution;
	private String crop;
	private long pestDisasterID;
	private boolean cpProductSolution;
	
	/**
	 * Constructor 
	 */
	public Solution(long solutionID, PestDisaster pest, String solution) {
		this.solutionID = solutionID;
		this.solution = solution;
		this.crop = pest.getCrop();
		this.pestDisasterID = pest.getPestDisasterID();
		this.cpProductSolution = false; // 初始化为false
		
		// 下面来判断solution是不是属于CP范畴，暂时方案：如果solution内容含有关键字"液"，则判定为CP方案
		
		String regex = "液";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(solution);
		
		if (m.find()) {
			cpProductSolution = true;
		}
		
		
	}
	
	public long getSolutionID() {
		return solutionID;
	}
	public void setSolutionID(long solutionID) {
		this.solutionID = solutionID;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getCrop() {
		return crop;
	}
	public void setCrop(String crop) {
		this.crop = crop;
	}
	public long getPestDisasterID() {
		return pestDisasterID;
	}
	public void setPestDisasterID(long pestDisasterID) {
		this.pestDisasterID = pestDisasterID;
	}
	public boolean isCpProductSolution() {
		return cpProductSolution;
	}
	public void setCpProductSolution(boolean cpProductSolution) {
		this.cpProductSolution = cpProductSolution;
	}
}
