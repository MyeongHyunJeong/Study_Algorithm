import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1325_2 {
	
	public static int n;
	public static int m;
	public static ArrayList<Integer>[] list;
	public static int[] dp;
	public static boolean[] chk;
	public static int max;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1325.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n];
		for(int i=0; i<n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		int a, b;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			list[a].add(b);
		}
		
		dp = new int[n];
		max = Integer.MIN_VALUE;
		for(int i=0; i<n; i++) {
			chk = new boolean[n];
			getResult(i);
			if(dp[i]>max) max = dp[i];
		}
		for(int i=0; i<n; i++) {
			if(dp[i]>max) max = dp[i];
		}
		
		for(int i=0; i<n; i++) {
			if(dp[i]==max) System.out.print((i+1) +" ");
		}
		
//		System.out.println(Arrays.toString(dp));
		
		br.close();
	}
	
	public static void getResult(int cur) {
		chk[cur] = true;
		for(int next : list[cur]) {
			if(chk[next]) continue;
			dp[next]++;
			getResult(next);
		}
	}

}
