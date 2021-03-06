import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main18809 {
	
	public static int n;
	public static int m;
	public static int g;
	public static int r;
	public static int[][] map;
	public static int[][] copyMap;
	public static Node[] ground;
	public static int[] select;
	public static int[] green;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int result = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input18809.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		int count = 0;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					count++;
				}
			}
		}
		ground = new Node[count];
		count=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==2) {
					ground[count++] = new Node(i,j);
				}
			}
		}
		
		
		select = new int[g+r];
		selectGround(ground.length, g+r, 0, 0);
		
		System.out.println(result);
		
		br.close();
		
	}
	
	public static void selectGround(int n, int r, int idx, int depth) {
		if(idx==r) {
//			System.out.println("SELECT");
//			System.out.println(Arrays.toString(select));
			green = new int[g];
			selectGreen(r, g, 0, 0);
			return;
		}
		if(depth==n) return;
		select[idx] = depth;
		selectGround(n,r,idx+1,depth+1);
		selectGround(n,r,idx,depth+1);
	}
	
	public static void selectGreen(int n, int r, int idx, int depth) {
		if(idx==r) {
//			System.out.println("GREEN");
//			System.out.println(Arrays.toString(green));
			getResult();
			return;
		}
		if(depth==n) return;
		green[idx] = select[depth];
		selectGreen(n,r,idx+1,depth+1);
		selectGreen(n,r,idx,depth+1);
	}
	
	public static void getResult() {
		Queue<Node> q = new LinkedList<Node>();
		int[][] chk = new int[n][m];
		for(int i=0; i<n; i++) {
			Arrays.fill(chk[i], Integer.MAX_VALUE);
		}
		int x, y;
		copyMap = new int[n][m];
		copy();
		for(int i=0; i<g; i++) {
			x = ground[green[i]].x;
			y = ground[green[i]].y;
			copyMap[x][y] = 7;
			q.add(new Node(x,y,'G',0));
			chk[x][y] = 0;
		}
		for(int i=0; i<select.length; i++) {
			x = ground[select[i]].x;
			y = ground[select[i]].y;
			if(copyMap[x][y]!=7) {
				copyMap[x][y] = 8;
				q.add(new Node(x,y,'R',0));
				chk[x][y] = 0;
//				System.out.print(select[i] + " ");
			}
		}
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<m; j++) {
//				System.out.print(copyMap[i][j] + " ");
//			}System.out.println();
//		}System.out.println();
		
		Node poll;
		int i,j,nx,ny,depth;
		char color;
		while(!q.isEmpty()) {
			poll = q.poll();
			i = poll.x;
			j = poll.y;
			color = poll.color;
			depth = poll.depth;
			if(copyMap[i][j]==9) continue;
			for(int k=0; k<4; k++) {
				nx = i + dir[k][0];
				ny = j + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && (chk[nx][ny]==Integer.MAX_VALUE||chk[nx][ny]==depth+1) && copyMap[nx][ny]!=0) {
					if(color=='G') {
						if(chk[nx][ny]==Integer.MAX_VALUE) {
							q.add(new Node(nx,ny,color,depth+1));
							copyMap[nx][ny] = 7;
							chk[nx][ny] = depth+1;
						}else {
							if(copyMap[nx][ny]==8) {
								copyMap[nx][ny] = 9;
								chk[nx][ny] = depth+1;
							}
						}
					}else {
						if(chk[nx][ny]==Integer.MAX_VALUE) {
							q.add(new Node(nx,ny,color,depth+1));
							copyMap[nx][ny] = 8;
							chk[nx][ny] = depth+1;
						}else {
							if(copyMap[nx][ny]==7) {
								copyMap[nx][ny] = 9;
								chk[nx][ny] = depth+1;
							}
						}
					}
				}
			}
			
		}
		int cou = 0;
		for(int w=0; w<n; w++) {
			for(int e=0; e<m; e++) {
				if(copyMap[w][e]==9) {
					cou++;
				}
			}
		}
		if(cou>result) {
			result = cou;
		}
		
//		for(int ii=0; ii<n; ii++) {
//			for(int jj=0; jj<m; jj++) {
//				System.out.print(copyMap[ii][jj] + " ");
//			}System.out.println();
//		}System.out.println(result);
		
	}
	
	public static void copy() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
	
	public static class Node {
		int x;
		int y;
		char color;
		int depth;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Node(int x, int y, char color, int depth) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.depth = depth;
		}
	}

}
