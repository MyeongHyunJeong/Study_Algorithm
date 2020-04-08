import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2588 {

	public static int first;
	public static int second;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2588.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		first = Integer.parseInt(br.readLine());
		second = Integer.parseInt(br.readLine());
		for(int i=2; i>=0; i--) {
			System.out.println(first * Integer.parseInt((""+second).substring(i,i+1)));
		}
		System.out.println(first*second);
		
		br.close();
	}

}
