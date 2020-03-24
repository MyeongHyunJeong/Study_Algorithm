import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13460_3 {

	public static int n;
	public static int m;
	public static char[][] map;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static Node exit;
	public static Node red;
	public static Node blue;
	public static int result = Integer.MAX_VALUE;
	public static int nrx,nry,nbx,nby;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input13460.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(map[i][j]=='O') {
					exit = new Node(i,j,0);
				}else if(map[i][j]=='R') {
					red = new Node(i,j,0,new boolean[n][m][4]);
					map[i][j] = '.';
				}else if(map[i][j]=='B') {
					blue = new Node(i,j,0);
					map[i][j] = '.';
				}
			}
		}
		printMap();

		moveGu();
		if(result==Integer.MAX_VALUE) result = -1;
		System.out.println(result);

		br.close();
	}

	public static void moveGu() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(blue);
		q.add(red);

		Node r,b;
		int rx,ry,bx,by,count;
		boolean[][][] chk;
		boolean[][][] tempChk;
		while(!q.isEmpty()) {
			b = q.poll();
			r = q.poll();
			rx = r.x;
			ry = r.y;
			bx = b.x;
			by = b.y;
			count = r.count;
			chk = r.chk;
			if(count>10) {
				continue;
			}
			
			if(bx==exit.x && by==exit.y) {
				continue;
			}

			if(rx==exit.x && ry==exit.y) {
				if(result>r.count) {
					result = r.count;
				}
				break;
			}

			for(int k=0; k<4; k++) {
				tempChk = new boolean[n][m][4];
				for(int i=0; i<n; i++) {
					for(int j=0; j<m; j++) {
						for(int c=0; c<4; c++) {
							tempChk[i][j][c]=chk[i][j][c];
						}
					}
				}
				if(k==0 && !chk[rx][ry][0]) {
					up(rx,ry,bx,by,tempChk);
					q.add(new Node(nbx,nby,count+1));
					q.add(new Node(nrx,nry,count+1,tempChk));
				}else if(k==1 && !chk[rx][ry][1]) {
					down(rx,ry,bx,by,tempChk);
					q.add(new Node(nbx,nby,count+1));
					q.add(new Node(nrx,nry,count+1,tempChk));
				}else if(k==2 && !chk[rx][ry][2]) {
					left(rx,ry,bx,by,tempChk);
					q.add(new Node(nbx,nby,count+1));
					q.add(new Node(nrx,nry,count+1,tempChk));
				}else if(k==3 && !chk[rx][ry][3]) {
					right(rx,ry,bx,by,tempChk);
					q.add(new Node(nbx,nby,count+1));
					q.add(new Node(nrx,nry,count+1,tempChk));
				}
			}
		}

	}

	public static void up(int rx, int ry, int bx, int by, boolean[][][] chk) {
		nrx = rx;
		nry = ry;
		nbx = bx;
		nby = by;
		if(ry==by) {
			if(rx<bx) {
				for(int i=rx; i>0; i--) {
					if(map[i][ry]=='.') {
						nrx = i;
						nry = ry;
					}else if(map[i][ry]=='O') {
						nrx = i;
						nry = ry;
						break;
					}else if(map[i][ry]=='#') {
						break;
					}
					chk[i][ry][0] = true;
				}
				for(int i=bx; i>nrx; i--) {
					if(map[i][by]=='.') {
						nbx = i;
						nby = by;
					}else if(map[i][by]=='O') {
						nbx = i;
						nby = by;
						break;
					}
				}
				if(map[nbx-1][nby]=='O') {
					nbx = nbx-1;
				}
			}else {
				for(int i=bx; i>0; i--) {
					if(map[i][by]=='.') {
						nbx = i;
						nby = by;
					}else if(map[i][by]=='O') {
						nbx = i;
						nby = by;
						break;
					}else if(map[i][by]=='#') {
						break;
					}
				}
				for(int i=rx; i>nbx; i--) {
					if(map[i][ry]=='.') {
						nrx = i;
						nry = ry;
					}else if(map[i][ry]=='O') {
						nrx = i;
						nry = ry;
						break;
					}else if(map[i][ry]=='#') {
						break;
					}
					chk[i][ry][0] = true;
				}
				if(map[nrx-1][nry]=='O') {
					nrx = nrx-1;
				}
			}
		}else {
			for(int i=rx; i>0; i--) {
				if(map[i][ry]=='.') {
					nrx = i;
					nry = ry;
				}else if(map[i][ry]=='O') {
					nrx = i;
					nry = ry;
					break;
				}else if(map[i][ry]=='#') {
					break;
				}
				chk[i][ry][0] = true;
			}
			for(int i=bx; i>0; i--) {
				if(map[i][by]=='.') {
					nbx = i;
					nby = by;
				}else if(map[i][by]=='O') {
					nbx = i;
					nby = by;
					break;
				}else if(map[i][by]=='#') {
					break;
				}
			}
		}
	}

	public static void down(int rx, int ry, int bx, int by, boolean[][][] chk) {
		nrx = rx;
		nry = ry;
		nbx = bx;
		nby = by;
		if(ry==by) {
			if(rx>bx) {
				for(int i=rx; i<n; i++) {
					if(map[i][ry]=='.') {
						nrx = i;
						nry = ry;
					}else if(map[i][ry]=='O') {
						nrx = i;
						nry = ry;
						break;
					}else if(map[i][ry]=='#') {
						break;
					}
					chk[i][ry][1] = true;
				}
				for(int i=bx; i<nrx; i++) {
					if(map[i][by]=='.') {
						nbx = i;
						nby = by;
					}else if(map[i][by]=='O') {
						nbx = i;
						nby = by;
						break;
					}
				}
				if(map[nbx+1][nby]=='O') {
					nbx = nbx+1;
				}
			}else {
				for(int i=bx; i<n; i++) {
					if(map[i][by]=='.') {
						nbx = i;
						nby = by;
					}else if(map[i][by]=='O') {
						nbx = i;
						nby = by;
						break;
					}else if(map[i][by]=='#') {
						break;
					}
				}
				for(int i=rx; i<nbx; i++) {
					if(map[i][ry]=='.') {
						nrx = i;
						nry = ry;
					}else if(map[i][ry]=='O') {
						nrx = i;
						nry = ry;
						break;
					}else if(map[i][ry]=='#') {
						break;
					}
					chk[i][ry][0] = true;
				}
				if(map[nrx+1][nry]=='O') {
					nrx = nrx+1;
				}
			}
		}else {
			for(int i=rx; i<n; i++) {
				if(map[i][ry]=='.') {
					nrx = i;
					nry = ry;
				}else if(map[i][ry]=='O') {
					nrx = i;
					nry = ry;
					break;
				}else if(map[i][ry]=='#') {
					break;
				}
				chk[i][ry][0] = true;
			}
			for(int i=bx; i<n; i++) {
				if(map[i][by]=='.') {
					nbx = i;
					nby = by;
				}else if(map[i][by]=='O') {
					nbx = i;
					nby = by;
					break;
				}else if(map[i][by]=='#') {
					break;
				}
			}
		}
	}

	public static void right(int rx, int ry, int bx, int by, boolean[][][] chk) {
		nrx = rx;
		nry = ry;
		nbx = bx;
		nby = by;
		if(rx==bx) {
			if(ry>by) {
				for(int i=ry; i<m; i++) {
					if(map[rx][i]=='.') {
						nrx = rx;
						nry = i;
					}else if(map[rx][i]=='O') {
						nrx = rx;
						nry = i;
						break;
					}else if(map[rx][i]=='#') {
						break;
					}
					chk[rx][i][3] = true;
				}
				for(int i=by; i<nry; i++) {
					if(map[bx][i]=='.') {
						nbx = bx;
						nby = i;
					}else if(map[bx][i]=='O') {
						nbx = bx;
						nby = i;
						break;
					}
				}
				if(map[nbx][nby+1]=='O') {
					nby = nby+1;
				}
			}else {
				for(int i=by; i<m; i++) {
					if(map[bx][i]=='.') {
						nbx = bx;
						nby = i;
					}else if(map[bx][i]=='O') {
						nbx = bx;
						nby = i;
						break;
					}else if(map[bx][i]=='#') {
						break;
					}
				}
				for(int i=ry; i<nby; i++) {
					if(map[rx][i]=='.') {
						nrx = rx;
						nry = i;
					}else if(map[rx][i]=='O') {
						nrx = rx;
						nry = i;
						break;
					}else if(map[rx][i]=='#') {
						break;
					}
					chk[rx][i][3] = true;
				}
				if(map[nrx][nry+1]=='O') {
					nry = nry+1;
				}
			}
		}else {
			for(int i=ry; i<m; i++) {
				if(map[rx][i]=='.') {
					nrx = rx;
					nry = i;
				}else if(map[rx][i]=='O') {
					nrx = rx;
					nry = i;
					break;
				}else if(map[rx][i]=='#') {
					break;
				}
				chk[rx][i][3] = true;
			}
			for(int i=by; i<m; i++) {
				if(map[bx][i]=='.') {
					nbx = bx;
					nby = i;
				}else if(map[bx][i]=='O') {
					nbx = bx;
					nby = i;
					break;
				}else if(map[bx][i]=='#') {
					break;
				}
			}
		}
	}

	public static void left(int rx, int ry, int bx, int by, boolean[][][] chk) {
		nrx = rx;
		nry = ry;
		nbx = bx;
		nby = by;
		if(rx==bx) {
			if(ry<by) {
				for(int i=ry; i>0; i--) {
					if(map[rx][i]=='.') {
						nrx = rx;
						nry = i;
					}else if(map[rx][i]=='O') {
						nrx = rx;
						nry = i;
						break;
					}else if(map[rx][i]=='#') {
						break;
					}
					chk[rx][i][2] = true;
				}
				for(int i=by; i>nry; i--) {
					if(map[bx][i]=='.') {
						nbx = bx;
						nby = i;
					}else if(map[bx][i]=='O') {
						nbx = bx;
						nby = i;
						break;
					}else if(map[bx][i]=='#') {
						break;
					}
				}
				if(map[nbx][nby-1]=='O') {
					nby = nby-1;
				}
			}else {
				for(int i=by; i>0; i--) {
					if(map[bx][i]=='.') {
						nbx = bx;
						nby = i;
					}else if(map[bx][i]=='O') {
						nbx = bx;
						nby = i;
						break;
					}else if(map[bx][i]=='#') {
						break;
					}
				}
				for(int i=ry; i>nby; i--) {
					if(map[rx][i]=='.') {
						nrx = rx;
						nry = i;
					}else if(map[rx][i]=='O') {
						nrx = rx;
						nry = i;
						break;
					}else if(map[rx][i]=='#') {
						break;
					}
					chk[rx][i][2] = true;
				}
				if(map[nrx][nry-1]=='O') {
					nry = nry-1;
				}
			}
		}else {
			for(int i=ry; i>0; i--) {
				if(map[rx][i]=='.') {
					nrx = rx;
					nry = i;
				}else if(map[rx][i]=='O') {
					nrx = rx;
					nry = i;
					break;
				}else if(map[rx][i]=='#') {
					break;
				}
				chk[rx][i][2] = true;
			}
			for(int i=by; i>0; i--) {
				if(map[bx][i]=='.') {
					nbx = bx;
					nby = i;
				}else if(map[bx][i]=='O') {
					nbx = bx;
					nby = i;
					break;
				}else if(map[bx][i]=='#') {
					break;
				}
			}
		}
	}

	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}

	public static class Node {
		int x;
		int y;
		int count;
		boolean[][][] chk;
		public Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		public Node(int x, int y, int count, boolean[][][] chk) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.chk = chk;
		}
	}

}
