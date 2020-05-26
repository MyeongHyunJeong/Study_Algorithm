import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5653 {
	
	public static int testCase;
	public static int n;
	public static int m;
	public static int k;
	public static int idx1;
	public static int idx2;
	public static int[][] map;
	public static boolean[][] chk;
	public static Queue<Node> live;
	public static Queue<Node> temp;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input5653.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=testCase; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			chk = new boolean[1000][1000];
			map = new int[1000][1000];
			live = new LinkedList<Node>();
			temp = new LinkedList<Node>();
			idx1 = (1000-n)/2;
			idx2 = (1000-m)/2;
			
			int num = 0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					num = Integer.parseInt(st.nextToken());
					if(num!=0) {
						live.add(new Node(i+idx1, j+idx2, num, 0, false, 0));
						map[i+idx1][j+idx2] = num;
						chk[i+idx1][j+idx2] = true;
					}
				}
			}
			
//			for(Node node : live) {
//				System.out.println(node.x + " " + node.y + " " + node.time);
//			}
			getResult();
			System.out.println("#" + t + " " + live.size());
		}
	}
	
	public static void getResult() {
		int size = 0;
		Node poll;
		int x, y, nx, ny;
		for(int i=0; i<=k; i++) {
			size = live.size();
			for(int j=0; j<size; j++) {
				poll = live.poll();
				x = poll.x;
				y = poll.y;
				if(!poll.flag) {
					if(poll.num!=poll.time) {
						poll.time++;
						live.add(poll);
					}else if(poll.num==poll.time) {
						poll.flag = true;
						poll.flagtime++;
						live.add(poll);
					}
				}else {
					if(poll.flagtime==1) {
						for(int r=0; r<4; r++) {
							nx = x + dir[r][0];
							ny = y + dir[r][1];
							if(nx>-1 && ny>-1 && nx<1000 && ny<1000 && !chk[nx][ny]) {
								if(map[nx][ny]==0) {
									map[nx][ny] = poll.num;
									temp.add(new Node(nx,ny,poll.num,1,false,0));
								}else {
									if(map[nx][ny]<poll.num) {
										map[nx][ny] = poll.num;
										temp.add(new Node(nx,ny,poll.num,1,false,0));
									}
								}
							}
						}
					}
					
					if(poll.flagtime!=poll.num) {
						poll.flagtime++;
						live.add(poll);
					}
				}
			}
			Node tmpPoll;
			int tmpX, tmpY;
			while(!temp.isEmpty()) {
				tmpPoll = temp.poll();
				tmpX = tmpPoll.x;
				tmpY = tmpPoll.y;
				if(map[tmpX][tmpY]==tmpPoll.num) {
					live.add(tmpPoll);
					chk[tmpX][tmpY] = true;
				}
			}
//			System.out.println("T:" + i + " " +live.size());
//			for(Node node : live) {
//				System.out.println(node.x + " " + node.y + " " + node.num + " " + node.time + " " + node.flag + " " + node.flagtime);
//			}
		}
	}
	
	public static class Node {
		int x;
		int y;
		int num;
		int time;
		boolean flag;
		int flagtime;
		public Node(int x, int y, int num, int time, boolean flag, int flagtime) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.time = time;
			this.flag = false;
			this.flagtime = flagtime;
		}
	}

}
