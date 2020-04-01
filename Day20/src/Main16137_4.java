import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16137_4 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static Queue<Node> bridges;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
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
		bridges = new LinkedList<Node>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]==0) {
					posBridge(i,j);
				}
			}
		}
		
		int	numBridges = bridges.size();
		Node poll;
		for(int i=0; i<numBridges; i++) {
			poll = bridges.poll();
			map[poll.x][poll.y] = m;
			meetKJ();
			map[poll.x][poll.y] = 0; 
		}
		
		System.out.println(result);
		
		
		br.close();
	}
	
	public static void meetKJ() {
		Queue<Node> q = new LinkedList<Node>();
		boolean[][] chk = new boolean[n][n];
		q.add(new Node(0,0,0,false));
		chk[0][0] = true;
		
		
		Node poll;
		int x,y,nx,ny,time;
		int nnx,nny;
		boolean cross, posCross;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			time = poll.time;
			cross = poll.cross;
			posCross = false;
			
			if(x==n-1 && y==n-1) {
				if(result>time) {
					result = time;
//					for(int i=0; i<n; i++) {
//						for(int j=0; j<n; j++) {
//							System.out.print(map[i][j] + " ");
//						}System.out.println();
//					}System.out.println(result);
				}
				continue;
			}
			
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && !chk[nx][ny]) {
					if(map[nx][ny]==1) {
						q.add(new Node(nx,ny,time+1,false));
						chk[nx][ny] = true;
					}else if(map[nx][ny]>1){
						for(int kk=0; kk<4; kk++) {
							nnx = nx + dir[kk][0];
							nny = ny + dir[kk][1];
							if(nnx>-1 && nny>-1 && nnx<n && nny<n && !chk[nnx][nny] && map[nnx][nny]==1) {
								if((time+1)%map[nx][ny]==0) {
									q.add(new Node(nx,ny,time+1,false));
									chk[nx][ny] = true;
								}else {
									posCross = true;
								}
							}
						}
					}
				}
			}
			if(posCross) {
				q.add(new Node(x,y,time+1,false));
			}
		}
	}
	
	public static void posBridge(int x, int y) {
		int chkH = 0;
		int chkV = 0;
		int nx, ny;
		for(int k=0; k<2; k++) {
			nx = x + dir[k][0];
			ny = y + dir[k][1];
			if(nx>-1 && ny>-1 && nx<n && ny<n && (map[nx][ny]==0 || map[nx][ny]>1)){
				chkV++;
			}
		}
		for(int k=2; k<4; k++) {
			nx = x + dir[k][0];
			ny = y + dir[k][1];
			if(nx>-1 && ny>-1 && nx<n && ny<n && (map[nx][ny]==0 || map[nx][ny]>1)){
				chkH++;
			}
		}
		
		if(chkV>=1 && chkH>=1) return;
		else bridges.add(new Node(x,y,0,false));
	}
	
	public static class Node {
		int x;
		int y;
		int time;
		boolean cross;
		public Node(int x, int y, int time, boolean cross) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.cross = cross;
		}
	}

}
