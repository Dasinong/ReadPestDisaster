import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Test {

	public static void main(String[] args) {
		
		String str = "药剂防治。在叶柄基部与假茎相接的“把头”处灌注药液，一般采用80％敌敌畏乳油 800倍液，或25％杀虫双AS 500倍液。";
		String regex = "液";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		
		System.out.println(m.find());
		System.out.println(str.matches(regex));
	
	}
}
