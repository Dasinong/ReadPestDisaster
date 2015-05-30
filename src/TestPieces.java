import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;


public class TestPieces {
	
	public static String[] getSql(PestDisaster pest){

		String sql[] = new String[pest.getSolutions().size()+1]; // sql[0] for pestdisaster; sql[1,] for solution

		sql[0] = "INSERT INTO pestdisaster(pestdisasterid,type,pestname,alias,crop,impactedarea,color)"+
				"VALUES('"+pest.getPestDisasterID()+"','"+pest.getType()+"','"+pest.getPestName()+
				"','"+pest.getAlias()+"','"+pest.getCrop()+"','"+pest.getImpactedArea()+
				"','"+pest.getColor()+"')";

		ArrayList<Solution> solutions = pest.getSolutions();

		for (int i = 0; i < solutions.size(); i++) {
			Solution solution = solutions.get(i);
			
			sql[1+i] = "INSERT INTO solution(solutionid,solution,crop,pestdisasterid,cpproductsolution)"+
					"VALUES('"+solution.getSolutionID()+"','"+solution.getSolution()+"','"+solution.getCrop()+
					"','"+solution.getPestDisasterID()+"',"+solution.isCpProductSolution()+")";
		}
		
		return sql;

	}

	public static void main(String[] args) throws IOException {
		
//		String line = "果树—多年生草本类 >> 香蕉 >> 香蕉冠腐病";
//		String title[] = line.split(">>");
//		String crop = title[1].trim();
//		String pestName = title[2].trim();
//		
//		System.out.println("Crop: "+crop);
//		System.out.println("PestName: "+pestName);
		
			
		
		String separator = "--------------------";
		File file = new File("bingchonghai_all.txt");
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");

		ArrayList<String> lines = new ArrayList<String>();
		int count = 0;
		int testCount = 21;
		long pestDisasterID = 1;
		long initialSolutionID = 1;
		
//		String line = "防治方法: [防治]香蕉采后用枯草芽他杆菌(Bacilums .siabtilis)的B63，B68，B74，B75菌株的菌液和无细菌滤液处理，可有效防治由镰刀菌引起的香蕉冠腐病各处理中，无沦是菌液处理还是无菌滤液处理，均以T368菌株的防效最高，明显高于生产上使用的杀菌剂施保功的防效，防治的效果最好防效随滤液浓度的增加而提高。";
//		String fzff[] = line.split(":",2);
//		String fzffContent = fzff[1].trim();
//		// 去除开头可能有的 [防治]
//		String regex = "\\[防治\\]";
//		Pattern p = Pattern.compile(regex);
//		String items[] = p.split(fzffContent);
//		fzffContent = items[items.length-1].trim();
//		// 用(1),(2),(3)来split字段；作为solution将用来Construct solutions ArrayList
//		regex = "\\(\\d\\)";
//		String solutions[] = fzffContent.replaceFirst("^"+regex, "").split(regex) ;
//		
//		System.out.println(solutions[0]);
////		Solution solution = new Solution(initialSolutionID, "香蕉", pestDisasterID, solutions[0]);
////		System.out.println(solution.getSolution());
		
		
		
		
		try {
			while (it.hasNext()) {
				String line = (String) it.next();
				lines.add(line);

				if (line.equals(separator)) { // process block by block, which is separated by "--------------------"

					count = count+1;
					
						PestDisaster pest = new PestDisaster(pestDisasterID, initialSolutionID, lines);
						pestDisasterID++;
						initialSolutionID += pest.getSolutions().size();
						String sql[] = getSql(pest);
						
						if (count == 242) {
							
							for (int i = 0; i < sql.length; i++) {
								System.out.println(sql[i]);
							}
							
							break;
						}
						
						
					
					
					lines.clear();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			it.close();
		}

		
	}

}
