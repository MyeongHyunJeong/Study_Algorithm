import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1726_2 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static Node start;
	public static Node target;
	public static int[][] dir = {{0,-1},{0,1},{1,0},{-1,0}};
	public static boolean[][][] chk;
	public static int result = Integer.MAX_VALUE;
	

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1726.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		chk = new boolean[n][m][4];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					for(int k=0; k<4; k++) {
						chk[i][j][k] = true;
					}
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		start = new Node(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,0,0);
		st = new StringTokenizer(br.readLine());
		target = new Node(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,0,0);
	
		
		go();
		System.out.println(result);
	}
	
	public static void go() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(start);
		//start의 모든방향 방문완료
		for(int i=0; i<4; i++) {
			chk[start.x][start.y][i] = true;
		}
		
		Node poll;
		int x, y, d, c, step, nx, ny, nd, nc, nstep;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			d = poll.d;
			c = poll.count;
			step = poll.step;
			if(x==target.x && y==target.y) {
				if(d==target.d) {
					map[x][y] = c;
				}else {
					if(target.d==0 || target.d==1) {
						if(d==0 || d==1) {
							c += 2;
						}else {
							c++;
						}
					}else {
						if(d==0 || d==1) {
							c++;
						}else {
							c+=2;
						}
					}
					map[x][y] = c;
				}
				if(result>c) {
					result = c;
				}
				continue;
			}
			map[x][y] = c;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && !chk[nx][ny][k]) {
					nd = k;
					nc = c;
					nstep = step;
					//회전여부 확인 및 명령어수 계산
					if(d!=k) {	//방향이 다를때 회전 o
						nstep = 0;
						if(d==0 || d==1) {
							if(k==0 || k==1) {
								nc += 2;
							}else {
								nc++;
							}
						}else {
							if(k==0 || k==1) {
								nc++;
							}else {
								nc+=2;
							}
						}
					}
					
					//이동 명령 계산
					nstep++;
					if(nstep>1) {
						if(nstep==4) {
							nc++;
							nstep = 1;
						}
					}else {
						nc++;
					}
					
					//다음 갈 곳의 명령어 수가 0 일경우 그냥 추가
					if(map[nx][ny]==0) {
						q.add(new Node(nx, ny, nd, nc, nstep));
					}else {	//명령어가 0이 아닐 경우 최소 명령어 수를 저장
						if(map[nx][ny]>nc) {
							q.add(new Node(nx, ny, nd, nc, nstep));
						}
					}
					chk[nx][ny][k] = true;
					
				}
			}
//			printMap();
		}
		
	}

	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + "\t");
			}System.out.println();
		}System.out.println();
	}
	
	public static class Node {
		int x;
		int y;
		int d;
		int count;
		int step;
		public Node(int x, int y, int d, int count, int step) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.count = count;
			this.step = step;
		}
	}
}
