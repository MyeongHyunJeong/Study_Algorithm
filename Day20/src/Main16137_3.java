import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16137_3 {

	public static int n;
	public static int m;
	public static int[][] map;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static Queue<Node> bri;
	public static boolean[][] chk;
	public static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input16137.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bri = new LinkedList<Node>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]==0) chkNode(i,j);
			}
		}

		int briSize = bri.size();
		int x,y;
		Node poll;
		if(briSize>0) {
			for(int i=0; i<briSize; i++) {
				poll = bri.poll();
				x = poll.x;
				y = poll.y;
				map[x][y] = m;
				chk = new boolean[n][n];
				//			printMap();
				meetKJ();
				map[x][y] = 0;
			}
		}else {
			chk = new boolean[n][n];
			meetKJ();
		}

		System.out.println(result);


		br.close();
	}

	public static void meetKJ() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(0,0,0,false));
		chk[0][0] = true;

		boolean chkBri;
		boolean useBri;
		int x,y,nx,ny,time,nnx,nny;
		Node poll;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			time = poll.time;
			chkBri = false;
			if(x==n-1 && y==n-1) {
				if(result>time) {
					result = time;
				}
				q.clear();
				return;
			}

			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && !chk[nx][ny]) {
					if(map[nx][ny]==1) {	//¶¥
						q.add(new Node(nx,ny,time+1,false));
						chk[nx][ny] = true;
					}else if(map[nx][ny]>1) {	//Àýº®
						nnx = nx + dir[k][0];
						nny = ny + dir[k][1];
						if(nnx>-1 && nny>-1 && nnx<n && nny<n && !chk[nnx][nny] && map[nnx][nny]==1) {
							if((time+1)%map[nx][ny]==0) {
								if(poll.use)	continue;
								q.add(new Node(nx,ny,time+1,true));
								chk[nx][ny] = true;
							}else {
								chkBri = true;
							}
						}
					}
				}
			}
			if(chkBri) {
				q.add(new Node(x,y,time+1,false));
			}
		}
	}

	public static void chkNode(int x, int y) {
		int countH, countV;
		countH = 0;
		countV = 0;
		int nx, ny;
		for(int k=0; k<2; k++) {
			nx = x + dir[k][0];
			ny = y + dir[k][1];
			if(nx>-1 && ny>-1 && nx<n && ny<n && map[nx][ny]==0) {
				countH++;
			}
		}
		for(int k=2; k<4; k++) {
			nx = x + dir[k][0];
			ny = y + dir[k][1];
			if(nx>-1 && ny>-1 && nx<n && ny<n && map[nx][ny]==0) {
				countV++;
			}
		}
		if(countH>=1 && countV>=1) return;
		else bri.add(new Node(x,y,0,false));
	}

	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j] + "\t");
			}System.out.println();
		}System.out.println();
	}

	public static class Node {
		int x;
		int y;
		int time;
		boolean use;
		public Node(int x, int y, int time, boolean use) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.use = use;
		}
	}

}
