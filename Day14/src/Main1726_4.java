import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1726_4 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static Node start;
	public static Node target;
	public static int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
	public static int[][][] chk;
	public static int max = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1726.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		chk = new int[n][m][4];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) {
					for(int k=0; k<4; k++) {
						chk[i][j][k] = Integer.MAX_VALUE;
					}
				}else {
					for(int k=0; k<4; k++) {
						chk[i][j][k] = -1;
					}
				}
			}
		}
		int x,y,dir;
		for(int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			dir = Integer.parseInt(st.nextToken())-1;
			if(i==0) start = new Node(x,y,dir,0,0);
			else target = new Node(x,y,dir,0,0);
		}
//		System.out.println("================ " + "Temp : 0 =====================");
//		printChk();
		move();
		System.out.println(max);
		
		br.close();
	}
	
	public static void move() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(start);
		chk[start.x][start.y][start.dir] = 0;
		
		Node poll;
		int x,y,dir,count,go,nx,ny;
		int temp = 1;
		while(!q.isEmpty()) {
//			System.out.println("================ " + "Temp : " + temp + " =====================");
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			dir = poll.dir;
			count = poll.count;
			go = poll.go;
			
			if(x==target.x && y==target.y) {
				int result = chk[x][y][dir] + calDirection(dir,target.dir);
				if(result<max) {
					max = result;
				}
				return;
			}
			
			for(int k=0; k<4; k++) {
				if(k==dir) chk[x][y][k] = count;
				else chk[x][y][k] = chk[x][y][dir] + calDirection(dir, k); 
			}
			
			for(int k=0; k<4; k++) {
				nx = x + direction[k][0];
				ny = y + direction[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]!=1) {
					if(dir==k) {
						if(go%3==0) {
							if(chk[nx][ny][k]>chk[x][y][k]+1) {
								chk[nx][ny][k] = chk[x][y][k]+1;
								q.add(new Node(nx,ny,k,chk[x][y][k]+1,1));
							}
						}else {
							if(chk[nx][ny][k]>chk[x][y][k]) {
								chk[nx][ny][k] = chk[x][y][k];
								q.add(new Node(nx,ny,k,chk[x][y][k],go+1));
							}
						}
					}else {
						if(chk[nx][ny][k]>chk[x][y][k]+1) {
							chk[nx][ny][k] = chk[x][y][k]+1;
							q.add(new Node(nx,ny,k,chk[x][y][k]+1,1));
						}
					}
				}
			}
			
//			printChk();
			temp++;
		}
	}
	
	public static int calDirection(int dir, int k) {
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
	
	public static void printChk() {
		for(int k=0; k<4; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					System.out.print(chk[i][j][k] + "\t");
				}System.out.println();
			}System.out.println();System.out.println();
		}
	}
	
	public static class Node {
		int x;
		int y;
		int count;
		int go;
		int dir;
		public Node(int x, int y, int dir, int count, int go) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.dir = dir;
			this.go = go;
		}
	}

}
