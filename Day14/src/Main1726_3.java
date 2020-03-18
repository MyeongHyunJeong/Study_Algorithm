import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1726_3 {

	public static int n;
	public static int m;
	public static int[][] map;
	public static Node start;
	public static Node target;
	public static int[][][] chk;
	public static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};	//동서남북

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1726.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0; i<n; i++) {	//map초기화
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int x,y,d;
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken())-1;
		y = Integer.parseInt(st.nextToken())-1;
		d = Integer.parseInt(st.nextToken());
		start = new Node(x,y,d,0,0);
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken())-1;
		y = Integer.parseInt(st.nextToken())-1;
		d = Integer.parseInt(st.nextToken());
		target = new Node(x,y,d,Integer.MAX_VALUE,0);

		chk = new int[n][m][4];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				Arrays.fill(chk[i][j], Integer.MAX_VALUE);
			}
		}

		getResult();

		System.out.println(target.depth);

		br.close();
	}

	public static void getResult() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(start);
		chk[start.x][start.y][start.d] = 0;

		Node poll;
		int x,y,d,depth,count,nx,ny,nd,ndepth,ncount;
		int k;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			d = poll.d;
			depth = poll.depth;

			if(x==target.x && y==target.y && d==target.d && depth<target.depth) {
				target.depth=depth;
				continue;
			}

			for(int i=0; i<2; i++) {
				nd = getNextDirect(d, i);
				if(chk[x][y][nd]>depth+1) {
					q.add(new Node(x,y,nd,depth+1,0));
					chk[x][y][nd] = depth+1;
				}
			}

			count = 0;
			nx = x;
			ny = y;
			while(true) {
				nx = nx + dir[d][0];
				ny = ny + dir[d][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]==0 && chk[nx][ny][d]>depth) {
					count++;
					if(count%3==1) {
						depth = depth+1;
					}
					q.add(new Node(nx,ny,d,depth,0));
					chk[nx][ny][d]=depth;
				}else {
					break;
				}
			}
			printChk();
		}
	}

	public static int getNextDirect(int d, int idx) {
		if(d==0) {
			if(idx==0) return 2;
			else if(idx==1) return 3;
		}else if(d==1) {
			if(idx==0) return 2;
			else if(idx==1) return 3;
		}else if(d==2) {
			if(idx==0) return 0;
			else if(idx==1) return 1;
		}else {
			if(idx==0) return 0;
			else if(idx==1) return 1;
		}
		return -1;
	}

	public static void printChk() {
		System.out.println("==================================================");
		for(int k=0; k<4; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					System.out.print(chk[i][j][k] + "\t");
				}System.out.println();
			}System.out.println();
		}System.out.println();
	}

	public static class Node {
		int x;
		int y;
		int d;
		int depth;
		int count;
		public Node(int x, int y, int d, int depth, int count) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.depth = depth;
			this.count = count;
		}
	}

}
