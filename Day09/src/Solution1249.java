import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1249 {

	public static int testCase;
	public static int n;
	public static char[][] map;
	public static int[][] result;
	public static int[][] dir = {{-1,0,},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		
		for(int t=0; t<testCase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new char[n][n];
			result = new int[n][n];
			for(int i=0; i<n; i++) {
				map[i] = br.readLine().toCharArray();
			}
			for(int i=0; i<n; i++) {
				Arrays.fill(result[i], Integer.MAX_VALUE);
			}
			go();
			System.out.println("#" + (t+1) + " " + result[n-1][n-1]);
			
		}
	}
	
	public static void go() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(0,0,0));
		
		result[0][0] = 0;
		Node poll;
		int x;
		int y;
		int c;
		int nx;
		int ny;
		int nc;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			c = poll.c;
			
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n) {
					nc = c + (map[nx][ny]-'0');
					if(nc<result[nx][ny]) {
						result[nx][ny] = nc;
						q.add(new Node(nx, ny, nc));
					}else {
						continue;
					}
				}
			}
		}
	}
	
	public static class Node {
		int x;
		int y;
		int c;
		public Node(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

}
