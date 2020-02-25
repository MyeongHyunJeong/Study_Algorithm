import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13460_2 {

	public static int n;
	public static int m;
	public static char[][] map;
	public static Node red;
	public static Node blue;
	public static Node target;
	public static boolean[][][] chkRR;
	public static boolean[][][] chkBB;
	public static boolean chkR;
	public static boolean chkB;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input13460.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		System.out.println("n : " + n + " m : " + m);
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(map[i][j]=='R') {
					red = new Node(i, j, 'R');
				}else if(map[i][j]=='B') {
					blue = new Node(i, j, 'B');
				}else if(map[i][j]=='O') {
					target = new Node(i, j, 'T');
				}
			}
		}
		chkRR = new boolean[n][m][4];
		chkBB = new boolean[n][m][4];
		
		move(red.x, red.y, blue.x, blue.y, -1, 0);
		
		br.close();
	}
	
	public static void move(int rx, int ry, int bx, int by, int d, int count) {
		if(count>10) {
			return;
		}
		if(map[bx][by]=='O') {
			return;
		}else if(map[rx][ry]=='O') {
			if(count<result) {
				result = count;
			}
		}
		
		if(d!=-1) {
			int nrx, nry, nbx, nby;
 			boolean r=false, b=false;
			while(true) {
				if(!r) {
					nrx = rx + dir[d][0];
					nry = ry + dir[d][1];
					if(nrx>-1 && nry>-1 && nrx<n && nry<m && !chkRR[nrx][nry][d]) {
						if(nrx==bx && nry==by) {
							if(b) {
								r = true;
							}
						}else if(map[nrx][nry]=='#') {
							r = true;
						}else {
							rx = nrx;
							ry = nry;
						}
						chkRR[rx][ry][d] = true;
					}
				}
				
				if(!b) {
					nbx = bx + dir[d][0];
					nby = by + dir[d][1];
					if(nbx>-1 && nby>-1 && nbx<n && nby<m && !chkBB[nbx][nby][d]) {
						if(nbx==rx && nby==ry) {
							if(r) {
								b = true;
							}
						}else if(map[nbx][nby]=='#') {
							b = true;
						}else {
							bx = nbx;
							by = nby;
						}
						chkBB[bx][by][d] = true;
					}
				}
				
				if(r && b) {
					break;
				}
			}
		}
		
		move(rx, ry, bx, by, 0, count+1);
		move(rx, ry, bx, by, 1, count+1);
		move(rx, ry, bx, by, 2, count+1);
		move(rx, ry, bx, by, 3, count+1);
	}
	
	public static void moveR() {
		
	}
	
	public static void moveB() {
		
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
	}

}
