import java.util.Arrays;

public class Main_Dijkstra {

	public static int n;
	public static int[][] map;
	public static int[] distance;
	public static boolean[] check;
	public static int start;
	public static String[] path;
	
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
		
		//path 초기화
		path = new String[n];
		
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
				path[i] = ""+start+" " + i;
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}
		System.out.println(Arrays.toString(distance));
		
		//start 노드를 제외한 각 노드를 다 돌아야하기 때문에 1-n까지 반복
		for(int l=1; l<n; l++) {
			int min = Integer.MAX_VALUE;
			int min_idx = -1;
			
			//start 노드에서 각 노드까지의 거리값중 최소값을 가진 노드를 선택
			for(int i=0; i<n; i++) {
				if(!check[i] && distance[i]<min) {
					min = distance[i];
					min_idx = i;
				}
			}
			
			//최소값을 가진 노드를 방문
			System.out.println("min_idx : " + min_idx);
			check[min_idx] = true;
			
			//최소값을 가진 노드와 연결된 노드까지의 거리값을 최신화 -> distance에 저장
			for(int i=0; i<n; i++) {
				if(!check[i] && map[min_idx][i]!=0 && distance[min_idx]+map[min_idx][i] < distance[i]) {
					distance[i] = distance[min_idx]+map[min_idx][i];
					path[i] = path[min_idx] + " " + i;
				}
			}
			System.out.println(Arrays.toString(distance));
			System.out.println(Arrays.toString(path));
		}
		//결과 출력
		System.out.println(Arrays.toString(distance));
	}
	
	public static void makeMap(int i, int j, int d) {
		map[i][j] = d;
	}
	
}
