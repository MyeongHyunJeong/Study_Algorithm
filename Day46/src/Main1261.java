import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1261 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int[][] chk;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input1261.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		String temp = "";
		for(int i=0; i<n; i++) {
			temp = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(temp.substring(j,j+1));
			}
		}
		if(n==1 && m==1) {
			System.out.println(0);
			return;
		}
		goToTarget();
		
		System.out.println(chk[n-1][m-1]);
		
		br.close();
	}
	
	public static void goToTarget() {
		Queue<Node> q = new LinkedList<Node>();
		chk = new int[n][m];
		q.add(new Node(0,0,0));
		chk[0][0] = 0;
		
		for(int i=0; i<n; i++) {
			Arrays.fill(chk[i], Integer.MAX_VALUE);
		}
		Node poll;
		int x,y,nx,ny,broke,nbroke;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			broke = poll.broke;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m) {
					nbroke = broke + map[nx][ny];
					if(nbroke<chk[nx][ny]) {
						chk[nx][ny] = nbroke;
						q.add(new Node(nx,ny,nbroke));
					}
				}
			}
		}
	}
	
	public static class Node {
		int x;
		int y;
		int broke;
		public Node(int x, int y, int broke) {
			super();
			this.x = x;
			this.y = y;
			this.broke = broke;
		}
	}

}
