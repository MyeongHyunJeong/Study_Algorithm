import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2606 {
	
	public static int n;
	public static int m;
	public static int[][] connect;
	public static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2606.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		connect = new int[n][n];
		int a,b;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			connect[a][b] = 1;
			connect[b][a] = 1;
		}
		
		result = 0;
		virus(0);
		System.out.println(result);
		
		br.close();
	}
	
	public static void virus(int a) {
		boolean[] chk = new boolean[n];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(a);
		chk[a] = true;
		
		int poll;
		while(!q.isEmpty()) {
			poll = q.poll();
			for(int i=0; i<n; i++) {
				if(i==poll) continue;
				if(connect[poll][i]==1 && !chk[i]) {
					result++;
					chk[i] = true;
					q.add(i);
				}
			}
		}
		
	}

}
