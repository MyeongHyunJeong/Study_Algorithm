import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17135 {
	
	public static int n;
	public static int m;
	public static int d;
	public static int[][] map;
	public static int[][] temp;
	public static int[] archers;
	public static int[][] dir = {{0,-1},{-1,0},{0,1}};
	public static boolean[][] chk;
	public static int hunt;
	public static int result = Integer.MIN_VALUE;
	public static Queue<Node> hunted;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input17135.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		archers = new int[3];
		putArcher(m,3,0,0);
		
		System.out.println(result);
		
		br.close();
	}
	
	public static void playGame() {
		int size;
		Node poll;
		
		hunt = 0;
		hunted = new LinkedList<Node>();
		copyMap();
//		printMap();
		for(int i=0; i<n; i++) {
			for(int j=0; j<3; j++) {
				attack(archers[j]);
			}
			size = hunted.size();
			for(int j=0; j<size; j++) {
				poll = hunted.poll();
				if(temp[poll.x][poll.y]==1) {
					temp[poll.x][poll.y] = 0;
					hunt++;
				}
			}
//			printMap();
			moveEnemy(i);
		}
//		System.out.println("공격한 적의 수 : " + hunt);
		if(hunt>result) {
			result = hunt;
		}
	}
	
	public static void attack(int a) {
		Queue<Node> q = new LinkedList<Node>();
		chk = new boolean[n][m];
		q.add(new Node(n-1,a,1));
		chk[n-1][a] = true;
		
		Node poll;
		int x,y,nx,ny,depth;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			depth = poll.depth;
			if(depth<=d && temp[x][y]==1) {
				hunted.add(new Node(x,y,0));
				q.clear();
				break;
			}else if(depth>d) {
				continue;
			}
			for(int k=0; k<3; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && !chk[nx][ny]) {
					q.add(new Node(nx,ny,depth+1));
					chk[nx][ny] = true;
				}
			}
		}
		
	}

	public static void putArcher(int n, int r, int idx, int depth){
		if(idx==r) {
//			System.out.println(Arrays.toString(archers));
			playGame();
			return;
		}
		if(depth==n) return;
		archers[idx] = depth;
		putArcher(n,r,idx+1,depth+1);
		putArcher(n,r,idx,depth+1);
	}
	
	public static void moveEnemy(int count) {
		for(int i=n-1; i>count; i--) {
			for(int j=0; j<m; j++) {
				temp[i][j] = temp[i-1][j];
			}
		}
		Arrays.fill(temp[count], 0);
	}
	
	public static void copyMap() {
		temp = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(temp[i][j] + " ");
			}System.out.println();
		}System.out.println();
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
