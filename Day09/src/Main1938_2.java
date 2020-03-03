import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main1938_2 {

	public static int n;
	public static char[][] map;
	public static boolean[][][] chk;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int[][] chkDir = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
	public static Node start;
	public static Node end;
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1938.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		//find start, end
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]=='B' || map[i][j]=='E') {
					findSE(i, j);
				}
			}
		}
		
//		System.out.println("START - X : " + start.x + " Y: " + start.y + " Direct : " + start.direction);
//		System.out.println("END - X : " + end.x + " Y: " + end.y + " Direct : " + end.direction);
		
		//세로:0, 가로:1
		chk = new boolean[n][n][2];
		go();
		
		if(result == Integer.MAX_VALUE) {
			result = 0;
		}
		System.out.println(result);
		
	}
	
	public static void go() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(start);
		chk[start.x][start.y][start.direction] = true;
		
		Node poll;
		int x, y, nx, ny, px, py;
		int gx, gy, gnx, gny, gpx, gpy;
		int d, gd;
		int depth;
		boolean flag;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			nx = poll.nx;
			ny = poll.ny;
			px = poll.px;
			py = poll.py;
			d = poll.direction;
			depth = poll.depth;
			
			if(x==end.x && y==end.y && nx==end.nx && ny==end.ny && px==end.px && py==end.py && d==end.direction) {
				if(depth<result) {
					result = depth;
				}
				continue;
			}
			
			//이동(상, 하, 좌, 우)
			for(int k=0; k<4; k++) {
				gx = x + dir[k][0];
				gy = y + dir[k][1];
				gnx = nx + dir[k][0];
				gny = ny + dir[k][1];
				gpx = px + dir[k][0];
				gpy = py + dir[k][1];
				if(gx>-1 && gy>-1 && gx<n && gy<n && map[gx][gy]!='1'
						&& gnx>-1 && gny>-1 && gnx<n && gny<n && map[gnx][gny]!='1'
						&& gpx>-1 && gpy>-1 && gpx<n && gpy<n && map[gpx][gpy]!='1') {
					if(chk[gx][gy][d]) {
						continue;
					}else {
						q.add(new Node(gx, gy, gnx, gny, gpx, gpy, d, depth+1));
						chk[gx][gy][d] = true;
					}
				}
			}
			
			//회전
			flag = false;
			for(int k=0; k<8; k++) {
				nx = x + chkDir[k][0];
				ny = y + chkDir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && map[nx][ny]!='1') {
					flag = true;
				}else {
					flag = false;
					break;
				}
			}
			if(flag) {
				if(d==0 && !chk[x][y][1]) {
					q.add(new Node(x, y, x, y+1, x, y-1, 1, depth+1));
					chk[x][y][1] = true;
				}else if(d==1 && !chk[x][y][0]){
					q.add(new Node(x, y, x+1, y, x-1, y, 0, depth+1));
					chk[x][y][0] = false;
				}
			}
			
		}
	}
	
	public static void findSE(int i, int j) {
		int nx, ny, px, py;
		nx = i+1;
		ny = j;
		px = i-1;
		py = j;
		if(nx>-1 && px>-1 &&  ny>-1 && py>-1 && nx<n && ny<n && px<n && py<n) {
			if(map[i][j]=='B') {
				if(map[px][py]=='B' && map[nx][ny]=='B') {
					start = new Node(i, j, nx, ny, px, py, 0, 0);
				}
			}else {
				if(map[px][py]=='E' && map[nx][ny]=='E') {
					end = new Node(i, j, nx, ny, px, py, 0, 0);
				}
			}
		}
		nx = i;
		ny = j+1;
		px = i;
		py = j-1;
		if(nx>-1 && px>-1 &&  ny>-1 && py>-1 && nx<n && ny<n && px<n && py<n) {
			if(map[i][j]=='B') {
				if(map[px][py]=='B' && map[nx][ny]=='B') {
					start = new Node(i, j, nx, ny, px, py, 1, 0);
				}
			}else {
				if(map[px][py]=='E' && map[nx][ny]=='E') {
					end = new Node(i, j, nx, ny, px, py, 1, 0);
				}
			}
		}
		
	}
	
	public static class Node {
		int x;
		int y;
		int nx;
		int ny;
		int px;
		int py;
		int direction;
		int depth;
		public Node(int x, int y, int nx, int ny, int px, int py, int direction, int depth) {
			this.x = x;
			this.y = y;
			this.nx = nx;
			this.ny = ny;
			this.px = px;
			this.py = py;
			this.direction = direction;
			this.depth = depth;
		}
	}

}
