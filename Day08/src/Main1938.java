import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1938 {

	public static int n;
	public static char[][] map;
	public static boolean[][] chk;
	public static int result = Integer.MAX_VALUE;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1938.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		chk = new boolean[n][n];
		
		//find middle of wood
		int nx = 0;
		int ny = 0;
		int mx = 0;
		int my = 0;
		char d = ' ';
		boolean flag = false;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]=='B') {
					nx = i+1;
					ny = j+1;
					if(nx<n && map[nx][j]=='B') {
						mx = nx;
						my = j;
						d = 'S';
					}else if(ny<n && map[i][ny]=='B') {
						mx = i;
						my = ny;
						d = 'G';
					}
					flag = true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
		System.out.println(mx + " " + my + " " + d);
		
		move(mx, my, d, 'O', 0);
	
	}
	
	public static void move(int x, int y, char d, char m, int count) {
		int nx = 0;
		int ny = 0;
		int px = 0;
		int py = 0;
		if(d=='S') {
			nx = x+1;
			px = x-1;
			ny = y;
			py = y;
		}
		else if(d=='G') {
			ny = y+1;
			py = y-1;
			nx = x;
			px = x;
		}
		if(nx<0 || nx>=n || px<0 || px>=n || ny<0 || ny>=n || py<0 || py>=n || x<0 || x>=n || y<0 || y>=n) return;	//범위 넘어가면 return
		if(map[nx][ny]=='1' || map[x][y]=='1' || map[px][py]=='1') return;	//빈공간 아니면 return;
		if(m!='T' && chk[x][y]) return;
		if(m=='T') {
			int tempx = 0;
			int tempy = 0;
			for(int k=0; k<dir.length; k++) {
				tempx = x + dir[k][0];
				tempy = y + dir[k][1];
				if(tempx<0 || tempy<0 || tempx>=n || tempy>=n) return;
				else if(map[tempx][tempy]=='1') return;
			}
		}
		else chk[x][y] = true;
		if(map[nx][ny]=='E' && map[x][y]=='E' && map[px][py]=='E') {
			if(result>count) {
				result = count;
			}
		}
		
		
		
		move(x-1, y, d, 'U', count+1);
		move(x+1, y, d, 'D', count+1);
		move(x, y-1, d, 'L', count+1);
		move(x, y+1, d, 'R', count+1);
		if(d=='S') {
			move(x, y, 'G', 'T', count+1);
		}
		if(d=='G') {
			move(x, y, 'S', 'T', count+1);
		}
		
	}

}
