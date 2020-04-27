import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17141 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static ArrayList<Point> virus;
	public static int[] temp;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input17141.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		virus = new ArrayList<Point>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					map[i][j] = 0;
					virus.add(new Point(i, j));
				}
			}
		}
		
		temp = new int[m];
		putVirus(virus.size(), m, 0, 0);
		if(min==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
		
		br.close();
	}
	
	public static void spreadVirus() {
		int max = Integer.MIN_VALUE;
		boolean[][] chk = new boolean[n][n];
		int[][] copyMap = new int[n][n];
		Queue<Node> q = new LinkedList<Node>();
		int x,y;
		for(int i=0; i<m; i++) {
			x = virus.get(temp[i]).x;
			y = virus.get(temp[i]).y;
			chk[x][y] = true;
			q.add(new Node(x,y,0));
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		Node poll;
		int nx, ny, depth;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			depth = poll.depth;
			if(depth>max) {
				max = depth;
			}
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && !chk[nx][ny] && copyMap[nx][ny]!=1) {
					chk[nx][ny] = true;
					copyMap[nx][ny] = depth+1;
					q.add(new Node(nx, ny, depth+1));
				}
			}
		}
		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(copyMap[i][j] + " ");
//			}System.out.println();
//		}System.out.println();
//		System.out.println(max);
		int count = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(copyMap[i][j]==0) count++;
			}
		}
//		System.out.println(count);
		if(count==m && min>max) {
			min = max;
		}
	}
	
	public static void putVirus(int n, int r, int idx, int depth) {
		if(r==idx) {
//			System.out.println(Arrays.toString(temp));
			spreadVirus();
			return;
		}
		if(depth==n) return;
		
		temp[idx] = depth;
		putVirus(n,r,idx+1,depth+1);
		putVirus(n,r,idx,depth+1);
		
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
