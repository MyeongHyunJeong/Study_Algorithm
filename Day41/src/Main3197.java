import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3197 {

	public static int n;
	public static int m;
	public static char[][] map;
	public static Point start;
	public static Point target;
	public static boolean[][] chk;
	public static boolean meet;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static Queue<Point> ice;
	public static int[][] temp;
	public static int num;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3197.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		ice = new LinkedList<Point>();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		start = new Point(-1, -1);
		target = new Point();
		int nx, ny;
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(map[i][j]=='L') {
					if(start.x==-1) {
						start.x = i;
						start.y = j;
						map[i][j] = '.';
					}else {
						target.x = i;
						target.y = j;
						map[i][j] = '.';
					}
				}
			}
		}

		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]=='L' || map[i][j]=='.') {
					for(int k=0; k<4; k++) {
						nx = dir[k][0] + i;
						ny = dir[k][1] + j;
						if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]=='X') {
							if(!ice.contains(new Point(nx,ny))) {
								ice.add(new Point(nx,ny));
							}
						}
					}
				}
			}
		}

//		System.out.println(start.x + " " + start.y);
//		System.out.println(target.x + " " + target.y);

		meet = false;
		int day = 0;
		while(true) {
//			System.out.println("DAY : " + day);
//			printMap();
			chk = new boolean[n][m];
			temp = new int[n][m];
			num = 1;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j]=='.' && !chk[i][j]) {
						numbering(i,j);
						num++;
					}
				}
			}
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<m; j++) {
//					System.out.print(temp[i][j] + " ");
//				}System.out.println();
//			}System.out.println();
			meltIce();
			if(temp[start.x][start.y]==temp[target.x][target.y]) {
				break;
			}
			day++;
		}
		System.out.println(day);
		br.close();
	}
	
	public static void numbering(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(i,j));
		chk[i][j] = true;
		temp[i][j] = num;
		
		Point poll;
		int x, y, nx, ny;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]=='.' && !chk[nx][ny]) {
					temp[nx][ny] = num;
					chk[nx][ny] = true;
					q.add(new Point(nx,ny));
				}
			}
		}
	}

	public static void meltIce() {
		int size = ice.size();
		Point poll;
		int nx,ny,x,y;
		for(int i=0; i<size; i++) {
			poll = ice.poll();
			x = poll.x;
			y = poll.y;
			map[x][y] = '.';
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]=='X') {
					if(!ice.contains(new Point(nx,ny))) {
						ice.add(new Point(nx,ny));
					}
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

}
