import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1726_5 {

	public static int n;
	public static int m;
	public static int[][] map;
	public static boolean[][][] chk;
	public static int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
	public static Node start;
	public static Node target;


	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1726.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int x,y,dir;
		for(int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			dir = Integer.parseInt(st.nextToken())-1;
			if(i==0) start = new Node(x,y,dir,0);
			else target = new Node(x,y,dir,0);
		}

		chk = new boolean[n][m][4];
		move();


		br.close();
	}

	public static void move() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(start);
		chk[start.x][start.y][start.dir] = true; 

		Node poll;
		int x,y,dir,count,nx,ny;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			dir = poll.dir;
			count = poll.count;

			if(x==target.x && y==target.y && dir==target.dir) {
				System.out.println(count);
				return;
			}

			for(int i=1; i<=3; i++) {
				nx = x + direction[dir][0]*i;
				ny = y + direction[dir][1]*i;
				if(nx>-1 && ny>-1 && nx<n && ny<m) {
					if(map[nx][ny]==0) {
						if(!chk[nx][ny][dir]) {
							chk[nx][ny][dir] = true;
							q.add(new Node(nx,ny,dir,count+1));
						}
					}else break;
				}
			}

			for(int k=0; k<4; k++) {
				if(k==dir) continue;
				if(!chk[x][y][k]) {
					chk[x][y][k] = true;
					q.add(new Node(x,y,k,count+calTurn(dir,k)));
				}
			}
		}
	}

	public static int calTurn(int dir, int k) {
		if(dir==k) return 0;
		if(dir==0) {
			if(k==1) return 2;
			else return 1;
		}else if(dir==1) {
			if(k==0) return 2;
			else return 1;
		}else if(dir==2) {
			if(k==3) return 2;
			else return 1;
		}else {
			if(k==2) return 2;
			else return 1;
		}
	}

	public static class Node {
		int x;
		int y;
		int dir;
		int count;
		public Node(int x, int y, int dir, int count) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}
	}

}
