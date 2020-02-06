import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main11559_2 {

	public static int n = 12;
	public static int m = 6;
	public static char[][] map = new char[n][m];
	public static boolean flag = true;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static boolean[][] chk;
	public static int result = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input11559.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		while(flag) {
			mapSet();
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<m; j++) {
//					System.out.print(map[i][j] + " ");
//				}System.out.println();
//			}
//			System.out.println("=======================================================");
			
			
			boom();
			if(flag) {
				result++;
			}
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<m; j++) {
//					System.out.print(map[i][j] + " ");
//				}System.out.println();
//			}
//			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		System.out.println(result);
	}
	
	public static void boom() {
		flag = false;
		Queue<Node> q = new LinkedList<Node>();
		Queue<Node> temp = new LinkedList<Node>();
		
		Node poll;
		int x;
		int y;
		char c;
		int nx; 
		int ny;
		char nc;
		int count = 0;
		for(int i=n-1; i>=0; i--) {
			for(int j=0; j<m; j++) {
				if(map[i][j]!='.') {
					q.add(new Node(i, j, map[i][j]));
					temp.add(new Node(i, j));
					chk = new boolean[n][m];
					chk[i][j] = true;
					count = 1;
					while(!q.isEmpty()) {
						poll = q.poll();
						x = poll.x;
						y = poll.y;
						c = poll.c;
						for(int k=0; k<4; k++) {
							nx = x + dir[k][0];
							ny = y + dir[k][1];
							if(nx>-1 && ny>-1 && nx<n && ny<m && !chk[nx][ny]) {
								nc = map[nx][ny];
								if(c==nc) {
									q.add(new Node(nx, ny, nc));
									chk[nx][ny] = true;
									temp.add(new Node(nx, ny));
									count++;
								}
							}
						}
					}
					if(count>=4) {
						flag = true;
						while(!temp.isEmpty()) {
							poll = temp.poll();
							x = poll.x;
							y = poll.y;
							map[x][y] = '.';
						}
					}else {
						temp.clear();
					}
					
				}
			}
		}
	}
	
	public static void mapSet() {
		Queue<Node> q = new LinkedList<Node>();
		Node poll;
		for(int j=0; j<m; j++) {
			for(int i=n-1; i>=0; i--) {
				if(map[i][j]=='.') {
					q.add(new Node(i, j));
				}else {
					if(!q.isEmpty()) {
						poll = q.poll();
						map[poll.x][poll.y] = map[i][j];
						map[i][j] = '.';
						q.add(new Node(i, j));
					}
				}
			}
			q.clear();
		}
	}
	
	public static class Node {
		int x;
		int y;
		char c;
		public Node(int x, int y, char c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			this.c = 'N';
		}
	}

}
