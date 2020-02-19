import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17144 {
	
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int[][] map;
	public static int[][] pmap;
	public static int n;
	public static int m;
	public static int t;
	public static Gongi g;
	public static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input17144.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		g = new Gongi(-1, -1, -1, -1);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		pmap = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				pmap[i][j] = map[i][j];
				if(map[i][j]==-1 && g.ux==-1) {
					g.ux = i;
					g.uy = j;
					g.dx = i+1;
					g.dy = j;
				}
			}
		}
		
		for(int i=0; i<t; i++) {
			misae();
			wind();
			copyMap();
		}
		
		result = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==-1) {
					continue;
				}
				result += map[i][j];
			}
		}
		System.out.println(result);
		
	}
	
	public static void wind() {
		int x=g.ux;
		int y=g.uy+1;
		int current = map[g.ux][g.uy+1];
		int nx = -1;
		int ny = -1;
		int next = -1;
		String d = "R";
		
		map[g.ux][g.uy+1] = 0;
		
		while(true) {
			if(d.equals("R")) {
				nx = x;
				ny = y + 1;
			}else if(d.equals("U")) {
				nx = x - 1;
				ny = y;
			}else if(d.equals("L")) {
				nx = x;
				ny = y - 1;
			}else if(d.equals("D")) {
				nx = x + 1;
				ny = y;
			}

			if(nx==g.ux && ny==g.uy) {
				break;
			}
			
			if(nx>-1 && ny>-1 && nx<n && ny<m) {
				next = map[nx][ny];
				map[nx][ny] = current;
				current = next;
				x = nx;
				y = ny;
			}else if(ny>=m) {
				d = "U";
			}else if(nx<0) {
				d = "L";
			}else if(ny<0) {
				d = "D";
			}
		}
		
		x=g.dx;
		y=g.dy+1;
		current = map[g.dx][g.dy+1];
		nx = -1;
		ny = -1;
		next = -1;
		d = "R";
		
		map[g.dx][g.dy+1] = 0;
		
		while(true) {
			if(d.equals("R")) {
				nx = x;
				ny = y + 1;
			}else if(d.equals("U")) {
				nx = x - 1;
				ny = y;
			}else if(d.equals("L")) {
				nx = x;
				ny = y - 1;
			}else if(d.equals("D")) {
				nx = x + 1;
				ny = y;
			}

			if(nx==g.dx && ny==g.dy) {
				break;
			}
			
			if(nx>-1 && ny>-1 && nx<n && ny<m) {
				next = map[nx][ny];
				map[nx][ny] = current;
				current = next;
				x = nx;
				y = ny;
			}else if(ny>=m) {
				d = "D";
			}else if(nx>=n) {
				d = "L";
			}else if(ny<0) {
				d = "U";
			}
		}
	}
	
	public static void misae() {
		int x, y, mi, count;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(pmap[i][j]!=0) {
					count = 0;
					mi = pmap[i][j] / 5;
					for(int k=0; k<4; k++) {
						x = i + dir[k][0];
						y = j + dir[k][1];
						if(x>-1 && y>-1 && x<n && y<m && pmap[x][y]!=-1) {
							count++;
							map[x][y] += mi;
						}
					}
					map[i][j] -= mi * count;
					
				}
			}
		}
	}
	
	public static void copyMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				pmap[i][j] = map[i][j];
			}
		}
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}
	
	public static class Gongi {
		int ux;
		int uy;
		int dx;
		int dy;
		public Gongi(int ux, int uy, int dx, int dy) {
			this.ux = ux;
			this.uy = uy;
			this.dx = dx;
			this.dy = dy;
		}
	}

}
