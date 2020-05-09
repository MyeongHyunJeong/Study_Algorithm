import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17825 {

	public static int[] go;
	public static int[][] map = {{0,2,4,6,8,0,12,14,16,18,0,22,24,26,28,0,32,34,36,38,40,0},
									{10,13,16,19,25,30,35,40,0},
									{20,22,24,25,30,35,40,0},
									{30,28,27,26,25,30,35,40,0}};
	public static int max;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input17825.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		go = new int[10];
		for(int i=0; i<10; i++) {
			go[i] = Integer.parseInt(st.nextToken());
		}
		
		max = Integer.MIN_VALUE;
		game(0,0,new Point(0,0), new Point(0,0), new Point(0,0), new Point(0,0));

		
		
		br.close();
	}
	
	public static void game(int count, int sum, Point a, Point b, Point c, Point d) {
		if(count==10) {
			if(sum>max) max = sum;
			return;
		}
		
		
	}

}
