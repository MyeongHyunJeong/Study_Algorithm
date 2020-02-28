import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main13460 {
	
	public static int n;
	public static int m;
	public static char[][] map;
	public static boolean[][][] chkR;
	public static boolean[][][] chkB;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static Node red;
	public static Node blue;
	public static Node target;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input13460.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//초기화
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(map[i][j]=='R') {
					red = new Node(i,j);
				}else if(map[i][j]=='B') {
					blue = new Node(i,j);
				}else if(map[i][j]=='O') {
					target = new Node(i,j);
				}
			}
		}
		chkR = new boolean[n][m][4];
		chkB = new boolean[n][m][4];
		start(red.x, red.y, blue.x, blue.y , -1, 0);
		
		
	}
	
	//1:상 2:하 3:좌 4:우
	public static void start(int rx, int ry, int bx, int by, int d, int count) {
		char first='R';
		char second='B';
		
		if(d==0) {
			if((rx-1 == bx) && (ry==by)) {
				first = 'B';
				second = 'R';
			}else if((bx-1==rx) && (by==ry)) {
				first = 'R';
				second = 'B';
			}
		}else if(d==1) {
			if((rx+1 == bx) && (ry==by)) {
				first = 'B';
				second = 'R';
			}else if((bx+1==rx) && (by==ry)) {
				first = 'R';
				second = 'B';
			}
		}else if(d==2) {
			if((rx == bx) && (ry-1==by)) {
				first = 'B';
				second = 'R';
			}else if((bx==rx) && (by-1==ry)) {
				first = 'R';
				second = 'B';
			}
		}else if(d==3) {
			if((rx == bx) && (ry+1==by)) {
				first = 'B';
				second = 'R';
			}else if((bx==rx) && (by+1==ry)) {
				first = 'R';
				second = 'B';
			}
		}
		
		int nrx, nry, nbx, nby;
		if(d!=-1) {
			if(first=='R') {
				if(d==0) {
					while(true) {
						nrx = rx - 1;
						nry = ry;
						nbx = bx - 1;
						nby = by;
						if(map[nrx][nry]!='#') {
							
						}
					}
				}
			}else {
				
			}
		}
		
		start(rx, ry, bx, by, 0, count+1);
		start(rx, ry, bx, by, 1, count+1);
		start(rx, ry, bx, by, 2, count+1);
		start(rx, ry, bx, by, 3, count+1);
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
