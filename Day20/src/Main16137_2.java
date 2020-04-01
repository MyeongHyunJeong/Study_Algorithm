import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16137_2 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static boolean[][] chk;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int numbering = 21;
	public static int result = Integer.MAX_VALUE;
	public static Queue<Node> bridges;

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
//		printMap();
		
		chk = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]==1 && !chk[i][j]) {
					numberingMap(i,j);
					numbering++;
				}
			}
		}
		posBridge();
//		printMap();
		Node poll;
		int size = bridges.size();
		if(size==0) {
			meetJik();
		}else {
			for(int i=0; i<size; i++) {
				poll = bridges.poll();
				chk = new boolean[n][n];
//				System.out.println("======= x: " + poll.x + ", y: " + poll.y + " =======");
				map[poll.x][poll.y] = m;
//				printMap();
				meetJik();
				map[poll.x][poll.y] = 0; 
			}
		}
		
		System.out.println(result);
		
		br.close();
		
	}
	
	public static void meetJik() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(0,0,0));
		chk[0][0] = true;
		
		Node poll;
		int x,y,nx,ny,depth;
		int nnx, nny;
		boolean chkJeol = false;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			depth = poll.depth;
			
			if(x==n-1 && y==n-1) {
				if(result>depth) {
					result = depth;
				}
				continue;
			}
			
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && !chk[nx][ny]) {
					if(map[nx][ny]>20) {	//다음이 그냥 땅일 경우
						q.add(new Node(nx,ny, depth+1));
						chk[nx][ny] = true;
					}else {		//다음이 절벽일 경우
						if(map[x][y]<21) {
							continue;
						}
						if(map[nx][ny]==0) {
							continue;
						}else {
							if((depth+1)%map[nx][ny]==0) {
								q.add(new Node(nx,ny,depth+1));
								chk[nx][ny] = true;
							}else {
								chkJeol = true;
							}
						}
					}
				}
			}
			if(chkJeol) {
				q.add(new Node(x,y,depth+1));
				chkJeol = false;
			}
//			printChk();
		}
	}
	
	
	public static void numberingMap(int i, int j) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(i,j,0));
		chk[i][j] = true;
		map[i][j] = numbering;
		
		int x,y,nx,ny;
		Node poll;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && map[nx][ny]==1 && !chk[nx][ny]) {
					q.add(new Node(nx,ny,0));
					chk[nx][ny] = true;
					map[nx][ny] = numbering;
				}
			}
		}
	}
	
	public static void posBridge() {
		bridges = new LinkedList<Node>();
		int nx,ny,nnx,nny;
		chk = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]>20) {
					for(int k=0; k<4; k++) {
						nx = i + dir[k][0];
						ny = j + dir[k][1];
						nnx = nx + dir[k][0];
						nny = ny + dir[k][1];
						if(nx>-1 && ny>-1 && nx<n && ny<n &&
								nnx>-1 && nny>-1 && nnx<n && nny<n &&
								map[nx][ny]==0 && map[nnx][nny]>20 && map[i][j]!=map[nnx][nny] && !chk[nx][ny]) {
							bridges.add(new Node(nx,ny,0));
							chk[nx][ny] = true;
						}
					}
				}
			}
		}
	}

	public static void printChk() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(chk[i][j] + "\t");
			}System.out.println();
		}System.out.println();
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
		int depth;
		public Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}

}
