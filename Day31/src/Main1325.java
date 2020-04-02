import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main1325 {

	public static int n;
	public static int m;
	public static int[] dp;
	public static boolean[] chk;
	public static HashSet<Integer>[] map;
	public static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1325.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new HashSet[n];
		for(int i=0; i<n; i++) {
			map[i] = new HashSet<Integer>();
		}
		int x,y;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			map[y].add(x);
		}

		dp = new int[n];
		chk = new boolean[n];
		for(int i=0; i<n; i++) {
//			System.out.println(map[i].size());
			if(!chk[i] && map[i].size()!=0) getResult(i);
		}
		
		for(int i=0; i<n; i++) {
			if(max==dp[i]) System.out.print((i+1) + " ");
		}
//		System.out.println(Arrays.toString(dp));
		br.close();
	}
	
	public static void getResult(int idx) {
		if(chk[idx]) return;
		if(map[idx].size()==0) {
			chk[idx] = true;
			return;
		}
		chk[idx] = true;
		Iterator<Integer> it = map[idx].iterator();
		int next;
		while(it.hasNext()) {
			next = it.next();
			getResult(next);
			if(map[next].size()!=0) map[idx].addAll(map[next]);
		}
		dp[idx] = map[idx].size();
		if(dp[idx]>max) max = dp[idx];
	}

}
