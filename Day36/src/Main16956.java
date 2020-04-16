import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main16956 {
	
	public static int n;
	public static int m;
	public static char[][] map;
	public static ArrayList<Animal> ani;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input16956.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		ani = new ArrayList<Animal>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
			for(int j=0; j<m; j++) {
				if(map[i][j]=='S') {
					ani.add(new Animal(i,j,'S'));
				}
			}
		}
//		System.out.println(Arrays.deepToString(map));
		getResult();
		System.out.println(result);
		print();
		br.close();
	}
	
	public static void getResult() {
		int x,y,nx,ny;
		for(Animal a : ani) {
			x = a.x;
			y = a.y;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m) {
					if(map[nx][ny]=='W') {
						result = 0;
						return;
					}else if(map[nx][ny]=='.') {
						map[nx][ny] = 'D';
					}
				}
			}
		}
		result = 1;
	}
	
	public static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]);
			}System.out.println();
		}
	}
	
	public static class Animal {
		int x;
		int y;
		char kind;
		public Animal(int x, int y, char kind) {
			this.x = x;
			this.y = y;
			this.kind = kind;
		}
	}

}
