import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10998 {

	public static int a;
	public static int b;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input10998.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		System.out.println(a*b);
		
		br.close();
	}

}
