import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main13545 {
	
	public static int n;
	public static int[] map;
	public static int m;
	public static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input13545.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			getResult(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		}
		
		br.close();
	}
	
	public static void getResult(int a, int b) {
		int countA = 0;
		int countB = 0;
		for(int i=a; i<=b; i++) {
			if(map[i]==1) {
				countA++;
			}else {
				countB++;
			}
		}
		
		if(countA>=countB) {
			result = countB*2;
		}else {
			result = countA*2;
		}
		
		System.out.println(result);
		
	}

}
