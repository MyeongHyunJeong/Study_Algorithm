import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main11559 {
	
	public static int n = 12;
	public static int m = 6;
	public static char[][] map = new char[n][m];
	public static boolean[][] chk;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int result = 0;
	public static boolean flag;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input11559.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]  + " ");
			}System.out.println();
		}
		while(!flag) {
			for(int i=n-1; i>=0; i--) {
				chk = new boolean[n][m];
				for(int j=0; j<m; j++) {
					if(map[i][j]!='.') {
						getResult(i, j);
					}
				}
			}
		}
		System.out.println("==================================");
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]  + " ");
			}System.out.println();
		}
		System.out.println(result);
	}
	
	public static void getResult(int i, int j) {
		Queue<Node> q = new LinkedList<Node>();
		Queue<Node> temp = new LinkedList<Node>();
		q.add(new Node(i, j, map[i][j]));
		temp.add(new Node(i, j, map[i][j]));
		chk[i][j] = true;
		flag = true;
		
		Node poll;
		int x;
		int y;
		char c;
		int nx;
		int ny;
		char nc;
		int count = 1;
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
					if(nc==c) {
						q.add(new Node(nx, ny, nc));
						temp.add(new Node(nx, ny, nc));
						chk[nx][ny] = true;
						count++;
					}
				}
			}
		}
		if(count>=4) {
			flag = false;
			while(!temp.isEmpty()) {
				poll = temp.poll();
				x=poll.x;
				y=poll.y;
				c=poll.c;
				map[x][y] = '.';
				for(int k=x; k>=0; k--) {
					if(map[k][y]!='.' && map[k][y]!=c) {
						map[x][y] = map[k][y];
						map[k][y] = '.';
						break;
					}
				}
			}
			result++;
		}else {
			temp.clear();
		}
	}
	
	public static class Node {
		int x;
		int y;
		char c;
		public Node(int x, int y, char c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

}
