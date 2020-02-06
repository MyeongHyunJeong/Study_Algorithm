import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17070 {
	
	public static int n;
	public static int[][] map;
	public static int result;
	public static int[][] dir = {{-1,0},{0,-1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = 0;
		move(0, 1, 'A');	//A:가로, B:세로, C:대각선
		System.out.println(result);
		
	}
	
	public static void move(int x, int y, char d) {
		if(x<0 || y<0 || x==n || y==n) {
			return;
		}
		if(d=='A' || d=='B' || d=='C') {
			if(map[x][y]==1) return;
		}
		if(d=='C') {
			int nx = 0;
			int ny = 0;
			for(int i=0; i<2; i++) {
				nx = x + dir[i][0];
				ny = y + dir[i][1];
				if(nx<0 || ny<0 || nx==n || ny==n) {
					return;
				}
				if(map[nx][ny]==1) {
					return;
				}
			}
		}
		if(x==n-1 && y==n-1) {
			if(map[x][y]==0) {
				result++;
			}
			return;
		}
		
		if(d=='A') {
			move(x, y+1, 'A');
			move(x+1, y+1, 'C');
		}else if(d=='B') {
			move(x+1, y, 'B');
			move(x+1, y+1, 'C');
		}else {
			move(x+1, y, 'B');
			move(x, y+1, 'A');
			move(x+1, y+1, 'C');
		}
	}
	
	public static class Pipe{
		int x;
		int y;
		char d;
		public Pipe(int x, int y, char d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

}
