import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main14503 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static Cleaner cn;
	public static int result;
	public static int cx, cy;
	public static int count;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input14503.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		int x, y, d;
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		cn = new Cleaner(x, y, d);
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		result = 0;
		count = 0;
		clean();
		System.out.println(result);
	}
	
	public static void clean() {
		while(true) {
			if(map[cn.x][cn.y]==0) {
				map[cn.x][cn.y] = 2;
				result++;
			}
			
//			printMap();
			
			next();
			count++;
			if(cx>-1 && cy>-1 && cx<n && cy<m) {
				if(map[cx][cy]==0) {
					cn.x = cx;
					cn.y = cy;
					count = 0;
					continue;
				}else {
					if(count==4) {
						back();
						count = 0;
						if(map[cn.x][cn.y]==1) {
							break;
						}
					}
					continue;
				}
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
	
	public static void back() {
		if(cn.d==0) {
			cn.x += 1;
		}else if(cn.d==1) {
			cn.y -= 1;
		}else if(cn.d==2) {
			cn.x -= 1;
		}else if(cn.d==3) {
			cn.y += 1;
		}
	}
	
	public static void next() {
		if(cn.d==0) {
			cn.d = 3;
			cx = cn.x;
			cy = cn.y - 1;
		}else if(cn.d==1) {
			cn.d = 0;
			cx = cn.x - 1;
			cy = cn.y;
		}else if(cn.d==2) {
			cn.d = 1;
			cx = cn.x;
			cy = cn.y + 1;
		}else if(cn.d==3) {
			cn.d = 2;
			cx = cn.x + 1;
			cy = cn.y;
		}
	}
	
	public static class Cleaner {
		int x;
		int y;
		int d;
		public Cleaner(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

}
