import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16918 {
	
	public static int n;
	public static int m;
	public static int time;
	public static char[][] map;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static Queue<Boom> booms;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input16918.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		booms = new LinkedList<Boom>();
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(map[i][j]=='O') {
					booms.add(new Boom(i, j, 2));
				}
			}
		}
		
		
		solve();
		printMap();
		
		br.close();
	}
	
	public static void solve() {
		int qSize = 0;
		Boom poll;
		int x, y, nx, ny, t, nt;
		for(int c=1; c<time; c++) {
//			System.out.println((c+1) + "ÃÊ!!");
//			System.out.println("ÆøÅº ¼³Ä¡");
			//ÆøÅº ¼³Ä¡
			makeBooms();
//			printMap();
			
//			System.out.println("ÆøÅº ÅÍÆ®¸®±â");
			//ÆøÅº ÅÍÆ®¸®±â
			qSize = booms.size();
			for(int i=0; i<qSize; i++) {
				poll = booms.poll();
				x = poll.x;
				y = poll.y;
				t = poll.t;
				if(t==3) {
					map[x][y] = '.';
					for(int k=0; k<4; k++) {
						nx = x + dir[k][0];
						ny = y + dir[k][1];
						if(nx>-1 && ny>-1 && nx<n && ny<m) {
							map[nx][ny] = '.';
						}
					}
				}else {
					booms.add(new Boom(x,y,t+1));
				}
			}
//			printMap();
			
			//ÆøÅº Àç°ËÅä
//			System.out.println("ÆøÅº Àç°ËÅä");
			qSize = booms.size();
			for(int i=0; i<qSize; i++) {
				poll = booms.poll();
				x = poll.x;
				y = poll.y;
				t = poll.t;
				if(map[x][y]=='O') {
					booms.add(new Boom(x,y,t));
				}
			}
		}
	}
	
	public static void makeBooms() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]=='.') {
					map[i][j] = 'O';
					booms.add(new Boom(i,j,0));
				}
			}
		}
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]);
			}System.out.println();
		}System.out.println();
	}
	
	public static class Boom {
		int x;
		int y;
		int t;
		public Boom(int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}

}
