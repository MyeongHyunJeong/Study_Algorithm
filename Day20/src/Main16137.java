import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16137 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static boolean[][] makeMap;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int numbering = 21;
	public static int startNum = 21;
	public static int targetNum;
	public static boolean se;
	public static boolean ga;
	public static boolean[][] chk;
	public static int result = Integer.MAX_VALUE;
	public static int[][] cdepth;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input16137.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		int x, y;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		printMap();
		
		makeMap = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]==1) {
					makeMap(i,j);
				}
			}
		}
//		printMap();
		
		chk = new boolean[n][n];
		cdepth = new int[n][n];
		go();
//		printMap();
//		printChk();
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(cdepth[i][j] + "\t");
//			}System.out.println();
//		}System.out.println();
		System.out.println(result);
		
		br.close();
	}
	
	public static void go() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(0,0,0));
		chk[0][0] = true;
		
		Node poll;
		int x,y,depth,nx,ny;
		int nnx, nny;
		boolean chkjul = false;
		while(!q.isEmpty()){
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			depth = poll.depth;
			cdepth[x][y] = depth;
			if(x==n-1 && y==n-1) {
				if(depth<result) {
					result = depth;
				}
				return;
			}
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && !chk[nx][ny]) {
					if(map[nx][ny]<21) {		//다음이 절벽일 경우
						nnx = nx + dir[k][0];
						nny = ny + dir[k][1];
						if(map[nx][ny]==0) {	
							if(nnx>-1 && nny>-1 && nnx<n && nny<n && map[nnx][nny]>20 && map[nnx][nny]!=map[x][y]) {
								chkjul = true;
								map[nx][ny] = m;
							}else {
								continue;
							}
						}
						if((depth+1)%map[nx][ny]==0) {
							q.add(new Node(nx,ny,depth+1));
							chk[nx][ny] = true;
							chkjul = false;
						}
					}else {
						q.add(new Node(nx,ny,depth+1));
						chk[nx][ny] = true;
					}
				}
			}
			if(chkjul) {	//주변에 절벽이 있을때 기다림 추가
				q.add(new Node(x,y,depth+1));
				chkjul = false;
			}
		}
	}
	
	public static void makeMap(int i, int j) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(i,j,0));
		makeMap[i][j] = true;
		map[i][j] = numbering;
		
		Node poll;
		int x,y,nx,ny;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<n && map[nx][ny]==1 && !makeMap[nx][ny]) {
					q.add(new Node(nx,ny,0));
					map[nx][ny] = numbering;
				}
			}
		}
		numbering++;
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

	public static class Node{
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


