import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3055 {
	
	public static int n;
	public static int m;
	public static char[][] map;
	public static boolean flag = true;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int result = 0;
	public static boolean[][] chkW;
	public static boolean[][] chkG;
	public static int count = 0;
	public static Queue<Node> qw = new LinkedList<Node>();
	public static Queue<Node> qg = new LinkedList<Node>();

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3055.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		
		chkG = new boolean[n][m];
		chkW = new boolean[n][m];
		while(flag) {
			count = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j]=='S' && !chkG[i][j]) {
						qg.add(new Node(i, j));
						count++;
					}
				}
			}
			moveGo();
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//			System.out.println("----------------------move------------------------------");
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<m; j++) {
//					System.out.print(map[i][j] + " ");
//				}System.out.println();
//			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j]=='*' && !chkW[i][j]) {
						qw.add(new Node(i, j));
					}
				}
			}
			moveWater();
//			System.out.println("----------------------water------------------------------");
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<m; j++) {
//					System.out.print(map[i][j] + " ");
//				}System.out.println();
//			}
			if(count==0) {
				result = 0;
				break;
			}
			result++;
		}
		if(result==0) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}
	}
	
	public static void moveWater() {
		Node poll;
		int x;
		int y;
		int nx;
		int ny;
		while(!qw.isEmpty()) {
			poll = qw.poll();
			x = poll.x;
			y = poll.y;
			chkW[x][y] = true;
			for(int i=0; i<4; i++) {
				nx = x + dir[i][0];
				ny = y + dir[i][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]!='D' && map[nx][ny]!='X') {
					map[nx][ny] = '*';
				}
			}
		}
	}

	public static void moveGo() {
		Node poll;
		int x;
		int y;
		int nx;
		int ny;
		while(!qg.isEmpty()) {
			poll = qg.poll();
			x = poll.x;
			y = poll.y;
			chkG[x][y] = true;
			for(int i=0; i<4; i++) {
				nx = x + dir[i][0];
				ny = y + dir[i][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]!='*' && map[nx][ny]!='X') {
					if(map[nx][ny]=='D') {
						flag = false;
					}else {
						map[nx][ny] = 'S';
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
