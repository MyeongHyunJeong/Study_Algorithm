import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5656 {
	
	public static int testCase;
	public static int n;
	public static int w;
	public static int h;
	public static int[][] map;
	public static int[][] copy;
	public static int[] select;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=testCase; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
//			printMap();
			
			result = Integer.MAX_VALUE;
			select = new int[n];
			selectDrop(w, n, 0, 0);
			System.out.println("#" + t + " " +result);
		}
		
		br.close();
	}
	
	public static void drop() {
		Queue<Point> q = new LinkedList<Point>();
		for(int d=0; d<n; d++) {
			boolean[][] chk = new boolean[h][w];
			for(int i=0; i<h; i++) {
				if(copy[i][select[d]]!=0) {
					q.add(new Point(i,select[d]));
					chk[i][select[d]] = true;
					break;
				}
			}
			
			Point poll;
			int x, y, num, nx, ny;
			while(!q.isEmpty()) {
				poll = q.poll();
				x = poll.x;
				y = poll.y;
				num = copy[x][y];
				if(num==1) {
					copy[x][y]=0;
				}else {
					for(int k=0; k<4; k++) {
						for(int r=1; r<num; r++) {
							nx = x + dir[k][0]*r;
							ny = y + dir[k][1]*r;
							if(nx>-1 && ny>-1 && nx<h && ny<w && !chk[nx][ny] && map[nx][ny]!=0) {
								if(copy[nx][ny]==1) {
									copy[nx][ny] = 0;
								}else {
									q.add(new Point(nx,ny));
								}
								chk[nx][ny] = true;
							}
						}
					}
				}
				
				copy[x][y] = 0;
				
			}
			reRange();
//			printMap();
			
		}
	}
	
	public static void selectDrop(int n, int r, int idx, int depth) {
		if(idx==r) {
//			System.out.println(Arrays.toString(select));
			copy = new int[h][w];
			copyMap();
			drop();
			int count = countBrick();
			if(result>count) result = count;
			return;
		}
		
		for(int i=0; i<n; i++) {
			select[idx] = i;
			selectDrop(n, r, idx+1, depth);
		}
	}
	
	public static int countBrick() {
		int count = 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(copy[i][j]!=0) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void reRange() {
		int idx = 0;
		boolean flag = false;
		for(int j=0; j<w; j++) {
			idx = 0;
			flag = false;
			for(int i=h-1; i>=0; i--) {
				if(!flag && copy[i][j]==0) {
					flag = true;
					idx = i;
				}
				if(flag && copy[i][j]!=0) {
					copy[idx][j] = copy[i][j];
					copy[i][j] = 0;
					idx--;
				}
			}
		}
	}
	
	public static void copyMap() {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}
	
	public static void printMap() {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				System.out.print(copy[i][j] +  " ");
			}System.out.println();
		}System.out.println();
	}

}
