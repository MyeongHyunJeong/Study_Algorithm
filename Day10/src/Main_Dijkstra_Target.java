
public class Main_Dijkstra_Target {
	
	public static int n;
	public static int[][] map;
	public static int start;
	public static int target;
	public static int[] distance;
	public static boolean[] check;

	public static void main(String[] args) {
		n = 8;
		start = 0;
		target = 7;
		map = new int[n][n];
		check = new boolean[n];
		
		//map �ʱ�ȭ
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
		
		//start ���� ����� �������� �Ÿ����� distance�� ����
		for(int i=0; i<n; i++) {
			distance[i] = map[start][i];
		}
		//start ��� �湮 üũ
		check[start] = true;
		
		Dijkstra();
		
		
		
	}
	
	public static void Dijkstra() {
		
	}
	
	public static void makeMap(int i, int j, int d) {
		map[i][j] = d;
	}

}
