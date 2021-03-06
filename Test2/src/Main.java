import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static int m;
	public static int[] map;
	public static int[][] family;
	public static Queue<question> q;
	public static int result;
	
	
	public static void main(String[] args) throws Exception{
		try {
//			System.setIn(new FileInputStream("input.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.toString(map));
			q = new LinkedList<question>();
			int first, second;
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				first = Integer.parseInt(st.nextToken());
				second = Integer.parseInt(st.nextToken());
				q.add(new question(first,second));
			}
			
			family = new int[n+1][n+1];
			for(int i=1; i<=n; i++) {
				family[map[i]][i] = 1;
			}
			
			for(int i=0; i<n+1; i++) {
				for(int j=1; j<n+1; j++) {
					if(family[i][j]==1) {
						
					}
				}
			}
			
			question poll;
			boolean[] chk;
			Queue<Integer> temp = new LinkedList<Integer>();
			result = 0;
			while(!q.isEmpty()) {
				poll = q.poll();
				first = poll.first;
				second = poll.second;
				
				temp.add(first);
				chk = new boolean[n+1];
				while(!temp.isEmpty()) {
					int tt = temp.poll();
					for(int i=1; i<n+1; i++) {
						if(family[tt][i]!=0) {
							temp.add(family[tt][i]);
							chk[family[tt][i]] = true;;
						}
					}
				}
//				System.out.println(Arrays.toString(chk));
				
				temp.clear();
				temp.add(second);
				int count = 0;
				while(!temp.isEmpty()) {
					int tt = temp.poll();
					if(chk[tt] && count>0) {
						chk[tt] = false;
					}
					for(int i=1; i<n+1; i++) {
						if(family[tt][i]!=0) {
							temp.add(family[tt][i]);
						}
					}
					count++;
				}
//				System.out.println(Arrays.toString(chk));
				
				for(int i=1; i<n+1; i++) {
					if(chk[i]) {
						result++;
					}
				}
				
				
			}
			System.out.println(result);
			
			br.close();
			
		}catch (Exception e) {
			return;
		}
	}
	
	public static class question{
		int first;
		int second;
		public question(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}
