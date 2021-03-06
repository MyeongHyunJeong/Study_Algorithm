import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1938 {

	public static int n;
	public static char[][] map;
	public static Node start;
	public static Node end;
	
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
		System.out.println("START - X : " + start.x + " Y: " + start.y + " Direct : " + start.d);
		System.out.println("END - X : " + end.x + " Y: " + end.y + " Direct : " + end.d);
		
		go(start.px, start.py, start.x, start.y, start.nx, start.ny, start.d, 'S');
	
	}
	
	public static void go(int px, int py, int x, int y, int nx, int ny, char d, char m) {
		if(nx>-1 && px>-1 &&  ny>-1 && py>-1 && nx<n && ny<n && px<n && py<n && map[px][py]!='1' && map[x][y]!='1' && map[nx][ny]!='1') {
			if(d=='G') {
				if(m=='T') {
					
				}
				go(px-1, py, x-1, y, nx-1, y, d, 'U');
				go(px+1, py, x+1, y, nx+1, y, d, 'D');
				go(px, py-1, x, y-1, nx, ny-1, d, 'L');
				go(px, py+1, x, y+1, nx, ny+1, d, 'R');
				go(px, py, x, y, nx, ny, d, 'T');
			}else {
				
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
					start = new Node(px, py,i, j, nx, ny, 'S');
				}
			}else {
				if(map[px][py]=='E' && map[nx][ny]=='E') {
					end = new Node(px, py,i, j, nx, ny, 'S');
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
					start = new Node(px, py,i, j, nx, ny, 'G');
				}
			}else {
				if(map[px][py]=='E' && map[nx][ny]=='E') {
					end = new Node(px, py,i, j, nx, ny, 'G');
				}
			}
		}
		
	}
	
	public static class Node {
		int px;
		int py;
		int x;
		int y;
		int nx;
		int ny;
		char d;
		public Node(int px, int py, int x, int y, int nx, int ny, char d) {
			this.px = px;
			this.py = py;
			this.x = x;
			this.y = y;
			this.nx = nx;
			this.ny = ny;
			this.d = d;
		}
	}

}
