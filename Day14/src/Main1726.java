import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1726 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static Node start;
	public static Node target;
	public static boolean[][] chk;
	public static int[][] dir = {{0,-1},{0,1},{1,0},{-1,0}};
	public static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1726.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int x,y,d;
		for(int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			d = Integer.parseInt(st.nextToken())-1;
			if(i==0) {
				start = new Node(x,y,d,0,1);
			}else {
				target = new Node(x,y,d,0,1);
			}
		}
		
//		printMap();
		
		chk = new boolean[n][m];
		go();
		
		System.out.println(min);
		
	}
	
	public static void go() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(start);
		chk[start.x][start.y] = true; 
		
		Node poll;
		int x,y,d,c,t;
		int nx,ny,nd,nc,nt;
		int div = 0;
		int mod = 0;
		boolean turn = false;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			d = poll.d;
			c = poll.count;
			t = poll.turn;
			
			//0:동 1:서 2:남 3:북
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				nc = c;
				nt = t;
				if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]==0 && !chk[nx][ny]) {
					nd = k;	// 다음 방향
					// 회전에 대한 명령어 수행 추가
					if(k==0) {	// 동쪽으로 이동
						if(d==1) {
							nc += 2;
							turn = true;
						}else if(d==2 || d==3) {
							nc += 1;
							turn = true;
						}else {
							turn = false;
						}
					}else if(k==1) {	// 서쪽으로 이동
						if(d==0) {
							nc += 2;
							turn = true;
						}else if(d==2 || d==3) {
							nc += 1;
							turn = true;
						}else {
							turn = false;
						}
					}else if(k==2) {	// 남쪽으로 이동
						if(d==3) {
							nc += 2;
							turn = true;
						}else if(d==0 || d==1) {
							nc += 1;
							turn = true;
						}else {
							turn = false;
						}
					}else {	// 북쪽으로 이동
						if(d==2) {
							nc += 2;
							turn = true;
						}else if(d==0 || d==1) {
							nc += 1;
							turn = true;
						}else {
							turn = false;
						}
					}
					
					// 이동에 대한 명령어 수행 추가
					if(turn) {
						nc++;
						nt = 1;
					}else {
						nt++;
						if(nt/4>0) {
							nc++;
							nt = 1;
						}
					}
					
					//target 도착
					if(nx==target.x && ny==target.y) {
						if(k!=target.d) {
							if(k==0) {
								if(target.d==1) {
									nc+=2;
								}else if(target.d==2 || target.d==3) {
									nc+=1;
								}
							}else if(k==1) {
								if(target.d==0) {
									nc+=2;
								}else if(target.d==2 || target.d==3) {
									nc+=1;
								}
							}else if(k==2) {
								if(target.d==3) {
									nc+=2;
								}else if(target.d==0 || target.d==1) {
									nc+=1;
								}
							}else {
								if(target.d==2) {
									nc+=2;
								}else if(target.d==0 || target.d==1) {
									nc+=1;
								}
							}
						}

						if(nc<min) {
							min = nc;
						}
						continue;
					}
					
					map[nx][ny] = nc;
					q.add(new Node(nx, ny, nd, nc, nt));
					chk[nx][ny] = true;
				}
				
			}
//			printMap();
		}
	}
	
//	public static 
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + "  ");
			}System.out.println();
		}System.out.println();
	}
	
	public static class Node {
		int x;
		int y;
		int d;
		int count;
		int turn;
		public Node(int x, int y, int d, int count, int turn) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.count = count;
			this.turn = turn;
		}
	}

}
