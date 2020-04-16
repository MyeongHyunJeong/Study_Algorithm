import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main10799 {
	
	public static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input10799.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		String temp = br.readLine();
		char past = temp.charAt(0);
		int count = 1;
		result = 0;
		for(int i=1; i<temp.length(); i++) {
			if(temp.charAt(i)=='(') {
				count++;
				past = '(';
			}else if(temp.charAt(i)==')') {
				count--;
				if(past=='(') result += count;
				else if(past==')') result += 1;
				past = ')';
			}
		}
		
		System.out.println(result);
		
		br.close();
	}

}
