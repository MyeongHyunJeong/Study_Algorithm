import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Line01 {

	public static int n;
	public static String temp;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input01.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		char currentC;
		int count;
		String result;
		for(int i=0; i<n; i++) {
			temp = br.readLine();
			currentC = temp.charAt(0);
			count = 1;
			result = "";
			for(int j=1; j<temp.length(); j++) {
				if(currentC==temp.charAt(j)) {
					count++;
				}else {
					result += count + "" + currentC;
					currentC = temp.charAt(j);
					count = 1;
				}
			}
			result += count + "" + currentC;		
			System.out.println(result);		//O(N)
		}
		
		br.close();
	}

}
