import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main17837 {
	
	public static int n;
	public static int k;
	public static int[][] map;
	public static Mal[] mal;
	public static int[][] direction = {{0,1},{0,-1},{-1,0},{1,0}};
	public static Deque<Mal>[][] state;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input17837.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		printMap();
		
		state = new Deque[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				state[i][j] = new LinkedList<Mal>();
			}
		}
		mal = new Mal[k];
		int x,y,dir;
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			dir = Integer.parseInt(st.nextToken())-1;
			mal[i] = new Mal(x,y,dir,i);
			state[x][y].add(mal[i]);
		}
		
		move();
		
		
		br.close();
	}
	
	public static void move() {
		int count = 0;
		boolean flag = false;
		
		Mal curmal;
		int x, y, dir, nx, ny, ndir;
		while(true) {
			
			
			for(int i=0; i<k; i++) {	//움직이기
				curmal = mal[i];
				x = curmal.x;
				y = curmal.y;
				dir = curmal.dir;
				if(state[x][y].size()==4) {
					flag = true;
					break;
				}else {
					nx = x + direction[dir][0];
					ny = y + direction[dir][1];
				}
			}
			
			
			
			
			if(flag) break;
			if(count>1000) break;
			count++;
		}
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j] + " " );
			}System.out.println();
		}System.out.println();
	}
	
	public static class Mal {
		int x;
		int y;
		int dir;
		int num;
		public Mal(int x, int y, int dir, int num) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.num = num;
		}
	}

}
