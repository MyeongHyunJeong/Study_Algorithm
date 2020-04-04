import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Line01_2 {
	
	public static int testCase;
	public static String temp;
	public static char[] tempArr;
	public static String result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input01.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		for(int t=0; t<testCase; t++) {
			temp = br.readLine();
			tempArr = temp.toCharArray();
			result = "";
			int count=1;
			char current = tempArr[0];
			for(int i=1; i<temp.length(); i++) {
				if(current==tempArr[i]) count++;
				else {
					result += count+""+current;
					current = tempArr[i];
					count = 1;
				}
			}
			if(count>=0) result += count+""+current;
			System.out.println(result);
		}
	}	//O(n)

}
