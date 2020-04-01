import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main5719 {

	public static int n;
	public static int m;
	public static int start;
	public static int target;
	public static int[][] map;
	public static boolean[] chk;
	public static int[] distance;
	public static HashSet<Integer>[] hs;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5719.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		while(true) {
			System.out.println("START!!!!!!!!!!!!!!!!!");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if(n==0 && m==0) {
				break;
			}
			
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());
			
			distance = new int[n];
			Arrays.fill(distance, Integer.MAX_VALUE);
			chk = new boolean[n];
			map = new int[n][n];
			hs = new HashSet[n];
			for(int i=0; i<n; i++) {
				hs[i] = new HashSet<Integer>();
			}

			int x = 0;
			int y = 0;
			int value = 0;
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				value = Integer.parseInt(st.nextToken());
				map[x][y] = value;
				if(x==start) {
					distance[y] = value;
					hs[y].add(start);
				}
			}
			
			//dijkstra
			chk[start] = true;
			distance[start] = 0;
			hs[start].add(start);
			dijkstra();
			
			distance = new int[n];
			Arrays.fill(distance, Integer.MAX_VALUE);
			chk = new boolean[n];
			Iterator<Integer> it = hs[target].iterator();
			int next = 0;
			System.out.print("next : ");
			while(it.hasNext()) {
				next = it.next();
				System.out.print(next + " ");
				if(next!=start && next!=target) {
					chk[next] = true;
				}
			}System.out.println();
			chk[start] = true;
			distance[start] = 0;
			for(int i=0; i<n; i++) {
				if(map[start][i]!=0) {
					distance[i] = map[start][i];
				}
			}
			
			dijkstra();
			System.out.println("result Array : " + Arrays.toString(distance));
			System.out.println("result : " + distance[target]);
		}
	}
	
	public static void dijkstra() {
		int min = 0;
		int min_v = -1;
		
		for(int i=0; i<n; i++) {
			min = Integer.MAX_VALUE;
			for(int j=0; j<n; j++) {
				if(distance[j]<min && !chk[j]) {
					min = distance[j];
					min_v = j;
				}
			}
			
			if(min_v==-1) {
				continue;
			}
			chk[min_v] = true;
			hs[min_v].add(min_v);
			for(int j=0; j<n; j++) {
				if(!chk[j] && map[min_v][j]!=0 && distance[min_v]+map[min_v][j]<=distance[j]) {
					if(distance[min_v]+map[min_v][j]<distance[j]) {
						hs[j] = (HashSet<Integer>) hs[min_v].clone();
					}else {
						hs[j].addAll(hs[min_v]);
					}
					distance[j] = distance[min_v]+map[min_v][j];
				}
			}
		}
	}
	

}
