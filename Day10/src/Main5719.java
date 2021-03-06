import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main5719 {
	
	public static int n;
	public static int m;
	public static int s;
	public static int t;
	public static int map[][];
	public static int min;
	public static int secMin;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5719.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		while(n!=0 && m!=0) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			int x;
			int y;
			int p;
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());
				map[x][y] = p;
			}
			System.out.println(Arrays.deepToString(map));
			min = Integer.MAX_VALUE;
			secMin = Integer.MAX_VALUE;
			
			go();
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void go() {
		Queue<Node> q = new LinkedList<Node>();
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0; i<n; i++) {
			if(map[s][i]!=0) {
				if(i==t) {
					if(map[s][i]<min) {
						min = map[s][i];
					}
				}else {
					q.add(new Node(i, map[s][i], ""+s));
				}
			}
		}
		
		Node poll;
		int v;
		int d;
		String sv;
		while(!q.isEmpty()) {
			poll = q.poll();
			v = poll.v;
			d = poll.d;
			sv = poll.sv;
			for(int i=0; i<n; i++) {
				if(map[v][i]!=0 && !sv.contains(""+i)) {
					if(i==t) {
						stack.add(d+map[v][i]);
						d += map[v][i];
						if(d<min) {
							secMin = min;
							min = d;
						}else if(d<secMin) {
							secMin = d;
						}
					}else {
						d += map[v][i];
						sv += i;
						q.add(new Node(i, d, sv));
					}
				}
			}
		}
		System.out.println("min : " + min + " secMin : " + secMin);
		if(secMin == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(secMin);
		}
	}
	
	public static class Node {
		int v;
		int d;
		String sv;
		public Node(int v, int d, String sv) {
			this.v = v;
			this.d = d;
			this.sv = sv;
		}
	}

}
