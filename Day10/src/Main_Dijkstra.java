import java.util.Arrays;

public class Main_Dijkstra {

	public static int n;
	public static int[][] map;
	public static int[] distance;
	public static boolean[] check;
	public static int start;
	
	public static void main(String[] args) {
		n = 8;
		map = new int[n][n];
		
		//map 초기화
		makeMap(0,1,3);
		makeMap(0,4,4);
		makeMap(0,3,4);
		makeMap(1,2,2);
		makeMap(2,3,1);
		makeMap(3,4,2);
		makeMap(4,5,4);
		makeMap(3,6,6);
		makeMap(6,5,3);
		makeMap(2,7,3);
		makeMap(5,7,2);
		
		//distance 초기화
		distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		//check 초기화
		check = new boolean[n];
		
		//시작점 등록
		start = 0;
		distance[start] = 0;
		check[start] = true;
		
		//다익스트라
		dijkstra();
	}
	
	public static void dijkstra() {
		//시작점에 연결된 노드값 초기화
		for(int i=1; i<n; i++) {
			if(map[start][i]!=0 && !check[i]) {
				distance[i] = map[start][i];
			}
		}
		System.out.println(Arrays.toString(distance));
		
		//각 노드를 돌면서 최단거리 저장 및 노드 방문 체크
		for(int i=1; i<n-1; i++) {
			for(int j=i; j<n; j++) {
				if(map[i][j]!=0 && !check[j]) {
					if(distance[i]+map[i][j]<distance[j]) {
						distance[j] = distance[i]+map[i][j];
					}
				}
			}
			check[i] = true;
		}
		
		//결과 출력
		System.out.println(Arrays.toString(distance));
	}
	
	public static void makeMap(int i, int j, int d) {
		map[i][j] = d;
	}
	
}
