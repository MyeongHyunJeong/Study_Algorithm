import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1226 {

	public static char[][] map;
	public static int testCase;
	public static boolean[][] chk;
	public static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
	public static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1226.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=0; t<10; t++) {
			map = new char[16][16];
			chk = new boolean[16][16];
			testCase = Integer.parseInt(br.readLine());
			result = 0;
			for(int i=0; i<16; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			go();
			System.out.println("#" + testCase + " " + result);
			
		}
	}
	
	public static void go() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(1,1));
		chk[1][1] = true;
		
		Node poll;
		int x;
		int y;
		int nx; 
		int ny;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<16 && ny<16 && !chk[nx][ny] && map[nx][ny]!='1') {
					if(map[nx][ny]=='3') {
						q.clear();
						result = 1;
					}else {
						q.add(new Node(nx, ny));
						chk[nx][ny] = true;
					}
				}
			}
		}
	}
	
	public static class Node {
		int x; 
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
