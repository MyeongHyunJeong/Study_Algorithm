import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main3197_2 {

	public static int n;
	public static int m;
	public static char[][] map;
	public static Point start;
	public static Point target;
	public static int[][] num;
	public static boolean[][] chk;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static Set<Point> virus;
	public static int count;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3197.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		start = new Point(-1,-1);
		target = new Point(-1,-1);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(map[i][j]=='L') {
					if(start.x==-1) {
						start.x = i;
						start.y = j;
					}else {
						target.x = i;
						target.y = j;
					}
					map[i][j] = '.';
				}
			}
		}
//		System.out.println(start.x + " " + start.y);
//		System.out.println(target.x + " " + target.y);
		
		int day = 0;
		virus = new HashSet<Point>();
		chk = new boolean[n][m];
		while(true) {
			num = new int[n][m];
			count = 1;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j]=='.' && !chk[i][j]) {
						numbering(i,j);
						count++;
					}
				}
			}
			printMap();
			printNumbering();
			if(num[start.x][start.y]==num[target.x][target.y]) break;
			else {
				meltIce();
			}
			day++;
		}
		System.out.println(day);
		
		br.close();
	}
	
	public static void meltIce() {
		int x,y;
		for(Point p : virus) {
			x = p.x;
			y = p.y;
			map[x][y] = '.';
		}
		virus.clear();
	}
	
	public static void numbering(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(i,j));
		chk[i][j] = true;
		
		Point poll;
		int x,y,nx,ny;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			num[x][y] = count;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && !chk[nx][ny]) {
					if(map[nx][ny]=='X') {
						virus.add(new Point(nx,ny));
					}else if(map[nx][ny]=='.') {
						q.add(new Point(nx,ny));
						chk[nx][ny] = true;
					}
				}
			}
		}
		
	}
	
	public static void printNumbering() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(num[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}

}
