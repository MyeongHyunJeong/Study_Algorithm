import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main15686 {

	public static int n;
	public static int m;
	public static int[][] map;
	public static LinkedList<Node> qshops;
	public static LinkedList<Node> qhomes;
	public static boolean[] visited;
	public static int result = Integer.MAX_VALUE;
	public static Node[] shops;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input15686.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		qshops = new LinkedList<Node>();
		qhomes = new LinkedList<Node>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					qhomes.add(new Node(i,j));
				}else if(map[i][j]==2) {
					qshops.add(new Node(i,j));
				}
			}
		}
		visited = new boolean[qshops.size()];
		
		select(0, m);
		System.out.println(result);
		
		br.close();
	}
	
	public static void select(int depth, int r) {
		if(r==0) {
			shops = new Node[m];
			int idx = 0;
			int calsChiRoads = 0;
			//선택된 가게 저장
			for(int i=0; i<qshops.size(); i++) {
				if(visited[i]) {
					shops[idx++] = qshops.get(i);
				}
			}
			
			//돌면서 치킨거리 계산
			int road = 0;
			int cal = 0;
			for(int i=0; i<qhomes.size(); i++) {
				road = Integer.MAX_VALUE;
				for(int j=0; j<m; j++) {
					cal = calc(i, j);
					if(cal < road) {
						road = cal;
					}
				}
				calsChiRoads += road;
			}
			
			//치킨거리가 최소값이면 result에 반영
			if(calsChiRoads < result) {
				result = calsChiRoads;
			}
		}else if(depth==qshops.size()) {
			return;
		}else {
			visited[depth] = true;
			select(depth+1, r-1);
			
			visited[depth] = false;
			select(depth+1, r);
		}
		

	}
	
	public static int calc(int a, int b) {
		return Math.abs(qhomes.get(a).x-shops[b].x) + Math.abs(qhomes.get(a).y-shops[b].y) ;
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
