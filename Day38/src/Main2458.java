import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2458 {
	
	public static int n;
	public static int m;
	public static int[][] low;
	public static int[][] high;
	public static int count;
	public static int result;
	public static boolean[] chk;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		low = new int[n][n];
		high = new int[n][n];
		
		int a,b;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			high[a][b] = 1;
			low[b][a] = 1;
		}
		
		result = 0;
		for(int i=0; i<n; i++) {
			count = 0;
			chk = new boolean[n];
			getHighPeople(i);
			getLowPeople(i);
			if(count==n-1) result++;
		}
		System.out.println(result);
		
		br.close();
	}
	
	public static void getHighPeople(int a) {
		for(int i=0; i<n; i++) {
			if(i==a) continue;
			if(high[a][i]==1 && !chk[i]) {
				count++;
				chk[i] = true;
				getHighPeople(i);
			}
		}
	}
	
	public static void getLowPeople(int a) {
		for(int i=0; i<n; i++) {
			if(i==a) continue;
			if(low[a][i]==1 && !chk[i]) {
				count++;
				chk[i] = true;
				getLowPeople(i);
			}
		}
	}

}
