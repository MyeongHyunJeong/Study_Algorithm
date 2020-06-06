import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2117 {

	public static int testCase;
	public static int n;
	public static int m;
	public static int[][] map;
	public static int max;
	public static int cost;
	public static boolean[][] chk;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static boolean flag;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2117.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());

		for(int t=1; t<=testCase; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			flag = true;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==0) flag = false;
				}
			}

			if(flag) {
				int k;
				if(n%2==0) {
					k = n+1;
				}else {
					k = n;
				}
				cost = k*k + (k-1)*(k-1);
				if(cost<=m*n) {
					System.out.println("#" + t + " " + (n*n));
					continue;
				}
			}
			if(1<=m) {
				max = 1;
			}else {
				max = 0;
			}
			
			for(int c=2; c<n+2; c++) {
				cost = c*c + (c-1)*(c-1);
				flag = false;
				//					System.out.println(c*c+(c*c-(1+2*(c-1))));
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						chk = new boolean[n][n];
						getResult(c,i,j);
						if(flag) break;
					}
					if(flag) break;
				}
			}
			System.out.println("#" + t + " " + max);



		}


		br.close();
	}

	public static void getResult(int c, int i, int j) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(i,j,1));
		chk[i][j] = true;
		int count = 0;
		if(map[i][j]==1) {
			count++;
		}


		Node poll;
		int x,y,nx,ny;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && !chk[nx][ny] && poll.depth<c) {
					if(poll.depth+1==c) {
						chk[nx][ny] = true;
						if(map[nx][ny]==1) count++;
					}else {
						chk[nx][ny] = true;
						if(map[nx][ny]==1) count++;
						q.add(new Node(nx,ny,poll.depth+1));
					}
				}
			}
		}
		if(count*m-cost>=0) {
			if(count>max) max = count;
		}
		if(count==c*c+(c*c-(1+2*(c-1)))) flag = true;
	}

	public static class Node {
		int x;
		int y;
		int depth;
		public Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}


}
