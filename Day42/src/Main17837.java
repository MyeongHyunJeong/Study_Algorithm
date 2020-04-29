import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main17837 {

	public static int n;
	public static int k;
	public static int[][] map;
	public static Mal[] mal;
	public static int[][] direction = {{0,1},{0,-1},{-1,0},{1,0}};
	public static Stack<Mal>[][] state;

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
//		printMap();

		state = new Stack[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				state[i][j] = new Stack<Mal>();
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
		
//		state[0][0].add(new Mal(3,0,0,1));
//		state[0][0].add(new Mal(3,0,0,2));
//		for(Mal m : state[0][0]) {
//			System.out.println(m.num);
//		}
//		int ad = state[0][0].size();
//		for(int i=0; i<ad; i++) {
//			System.out.println(state[0][0].pop().num);
//		}
		
		move();

		br.close();
	}

	public static void move() {
		int count = 0;
		boolean flag = false;

		Stack<Mal> temp = new Stack<Mal>();
		Mal curmal;
		int x, y, dir, nx, ny, ndir;
		while(true) {


			for(int i=0; i<k; i++) {	//움직이기
				curmal = mal[i];
				x = curmal.x;
				y = curmal.y;
				dir = curmal.dir;
				nx = x + direction[dir][0];
				ny = y + direction[dir][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && map[nx][ny]!=2) {
					if(map[nx][ny]==0) {
						Mal m;
						while(!state[x][y].isEmpty()) {
							m = state[x][y].pop();
							m.x = nx;
							m.y = ny;
							temp.add(m);
							if(m.num==i) break;
						}
						while(!temp.isEmpty()) {
							state[nx][ny].add(temp.pop());
						}
					}else if(map[nx][ny]==1) {
						Mal m;
						while(!state[x][y].isEmpty()) {
							m = state[x][y].pop();
							m.x = nx;
							m.y = ny;
							state[nx][ny].add(m);
							if(m.num==i) break;
						}
					}
				}else {
					if(curmal.dir==0) {
						curmal.dir = 1;
					}else if(curmal.dir==1) {
						curmal.dir = 0;
					}else if(curmal.dir==2) {
						curmal.dir = 3;
					}else {
						curmal.dir = 2;
					}
					nx = x + direction[curmal.dir][0];
					ny = y + direction[curmal.dir][1];
					if(nx>-1 && ny>-1 && nx<n && ny<n && map[nx][ny]!=2) {
						if(map[nx][ny]==0) {
							Mal m;
							while(!state[x][y].isEmpty()) {
								m = state[x][y].pop();
								m.x = nx;
								m.y = ny;
								temp.add(m);
								if(m.num==i) break;
							}
							while(!temp.isEmpty()) {
								state[nx][ny].add(temp.pop());
							}
						}else if(map[nx][ny]==1) {
							Mal m;
							while(!state[x][y].isEmpty()) {
								m = state[x][y].pop();
								m.x = nx;
								m.y = ny;
								state[nx][ny].add(m);
								if(m.num==i) break;
							}
						}
					}else {
						nx = x;
						ny = y;
					}
				}
//				System.out.println("C : " + count + " Num : " + curmal.num + " NX : " + nx + " NY :" + ny);
//				for(Mal m : state[nx][ny]) {
//					System.out.println(m.num + " " + m.x  + " " + m.y  + " " + m.dir);
//				}
				if(state[nx][ny].size()>=4) {
					flag=true;
					break;
				}
			}

			count++;
			if(flag) break;
			if(count>1000) break;
		}
		if(count>1000) System.out.println(-1);
		else System.out.println(count);
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
