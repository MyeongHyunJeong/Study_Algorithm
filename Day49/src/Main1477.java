import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1477 {

	public static int n;
	public static int m;
	public static int l;
	public static int[] highway;
	public static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1477.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		highway = new int[n+2];
		highway[n+1] = l;
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			highway[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(highway);
		System.out.println(Arrays.toString(highway));
		
		pq = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.dis>o2.dis ? -1:1;
			}
		});
		for(int i=0; i<=n; i++) {
			pq.add(new Node(highway[i], highway[i+1]-highway[i]));
		}
		Iterator<Node> it = pq.iterator();
		System.out.println("=====전====");
		Node temp;
		while(it.hasNext()) {
			temp = it.next();
			System.out.println(temp.x + " " + temp.dis);
		}
		
		build();
		System.out.println("=====후=====");
		while(!pq.isEmpty()) {
			temp = pq.poll();
			System.out.println(temp.x + " " + temp.dis);
		}

		br.close();
	}
	
	public static void build() {
		Node poll;
		int buildp = 0;
		System.out.println("빌드");
		for(int i=0; i<m; i++) {
			poll = pq.poll();
			System.out.println(poll.x + " " + poll.dis);
			buildp = poll.x + (poll.dis/2);
			pq.add(new Node(poll.x, buildp-poll.x));
			pq.add(new Node(buildp, poll.dis-(poll.dis/2)));
		}
	}
	
	public static class Node {
		int x;
		int dis;
		public Node(int x, int dis) {
			this.x = x;
			this.dis = dis;
		}
	}


}
