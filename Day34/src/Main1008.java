import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main1008 {
	
	public static double a;
	public static double b;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1008.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		a = Double.parseDouble(st.nextToken());
		b = Double.parseDouble(st.nextToken());
		
		System.out.print( a/b);
		
		br.close();
	}

}
