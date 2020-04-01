import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Line03 {
	
	public static int n;
	public static Node[] link;
	public static boolean[] chk;
	public static int group;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input03.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		link = new Node[n];
		int x,y;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			link[i] = new Node(x,y);
		}
		
		chk = new boolean[n];
		for(int i=0; i<n; i++) {
			if(!chk[i]) {
				connect(i);
				group++;
			}
		}	//O(N^2)
		
		System.out.println(group);
		
		
		br.close();
	}
	
	public static void connect(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(link[i].x);
		q.add(link[i].y);
		chk[i] = true;
		
		int poll;
		while(!q.isEmpty()) {
			poll = q.poll();
			for(int j=0; j<n; j++) {
				if(!chk[j]) {
					if(link[j].x==poll) {
						chk[j] = true;
						q.add(link[j].y);
					}else if(link[j].y==poll) {
						chk[j] = true;
						q.add(link[j].x);
					}
				}
			}
		}
		
	}
	
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
