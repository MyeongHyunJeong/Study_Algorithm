import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static int m;
	public static String temp;
	public static Long result;
	
	public static void main(String[] args) throws Exception{
		try {
			System.setIn(new FileInputStream("input.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			temp = br.readLine();
			
			result = Long.MIN_VALUE;
			String numTemp = "";
			for(int i=0; i<=n-m; i++) {
				numTemp = temp.substring(i, i+m);
				if(result<Long.parseLong(numTemp)) {
					result = Long.parseLong(numTemp);
				}
//				System.out.println(numTemp);
				numTemp = "";
			}
			
			System.out.println(result);
			
			br.close();
			
		}catch (Exception e) {
			return;
		}
		
	}
}
