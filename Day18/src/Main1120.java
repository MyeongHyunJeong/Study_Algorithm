import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1120 {

	public static String first;
	public static String second;
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1120.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		first = st.nextToken();
		second = st.nextToken();
//		System.out.println(first + " " + second);
		
		int len = second.length() - first.length();
		
		int count;
		if(first.length()==second.length()) {
			count = 0;
			for(int i=0; i<first.length(); i++) {
				if(first.charAt(i)!=second.charAt(i)) {
					count++;
				}
			}
			if(count<result) {
				result = count;
			}
		}else {
			String temp = first;
			for(int i=0; i<len+1; i++) {
				temp = first;
				temp = second.substring(0, i) + temp;
				temp += second.substring(second.length()-(len-i), second.length());
//				System.out.print(temp  + " : ");
				count = 0;
				for(int j=0; j<temp.length(); j++) {
					if(temp.charAt(j)!=second.charAt(j)) {
						count++;
					}
				}
//				System.out.println(count);
				if(count<result) {
					result = count;
				}
			}
			
		}
		
		System.out.println(result);
		
		br.close();
	}

}
