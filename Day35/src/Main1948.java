import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1948 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static int start;
	public static int target;
	public static boolean[] chk;
	public static int maxtime = Integer.MIN_VALUE;
	public static int maxtown = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1948.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		int x,y,distance;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			distance = Integer.parseInt(st.nextToken());
			map[x][y] = distance;
		}
//		print();
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		chk = new boolean[n+1];
		chk[start] = true;
		for(int i=1; i<n+1; i++) {
			if(map[start][i]!=0) {
				go(i);
			}
		}
//		System.out.println("result!!");
		System.out.println(maxtime);
		System.out.println(maxtown);
		
		br.close();
	}
	
	public static void go(int next) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(next, map[start][next], 2));
		chk[next] = true;
		
		
		Node poll;
		int idx, time, town;
		while(!q.isEmpty()) {
			poll = q.poll();
			idx = poll.idx;
			time = poll.time;
			town = poll.town;
			
			if(idx==target) {
//				System.out.println("=========================");
//				System.out.println(time);
//				System.out.println(town);
				if(time>maxtime) maxtime = time;
				if(town>maxtown) maxtown = town;
			}
			
			for(int i=1; i<n+1; i++) {
				if(map[idx][i]!=0 && !chk[i]) {
					q.add(new Node(i, time+map[idx][i], town+1));
				}
			}
		}
		
		
	}

	public static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}
	
	public static class Node {
		int idx;
		int time;
		int town;
		public Node(int idx, int time, int town) {
			this.idx = idx;
			this.time = time;
			this.town = town;
		}
	}
	
}
