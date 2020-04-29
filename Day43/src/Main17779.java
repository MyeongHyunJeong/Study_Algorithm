import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17779 {
	
	public static int n;
	public static int[][] map;
	public static int[][] sun;
	public static int min;
	public static int max;
	public static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input17779.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int x=0; x<n; x++) {
			for(int y=0; y<n; y++) {
				for(int d1=1; d1<n; d1++) {
					for(int d2=1; d2<n; d2++) {
						if(x+d1+d2<n && y-d1>-1 && y+d2<n) {
							sun = new int[n][n];
							min = Integer.MAX_VALUE;
							max = Integer.MIN_VALUE;
							makeMap(x,y,d1,d2);
						}
					}
				}
			}
		}
		
		System.out.println(result);
		
		
		br.close();
	}
	
	public static void makeMap(int x, int y, int d1, int d2) {
		int peo1=0, peo2=0, peo3=0, peo4=0, peo5 = 0;
		
		//5번 선거구
		int c1=y, c2=y;
		for(int i=x; i<=x+d1+d2; i++) {
			for(int j=c1; j<=c2; j++) {
				sun[i][j] = 5;
				peo5+=map[i][j];
			}
			if(i<x+d1) c1--;
			else c1++;
			if(i<x+d2) c2++;
			else c2--;
		}
		
		if(peo5<min) min = peo5;
		if(peo5>max) max = peo5;
		
		//1번 선거구
		for(int i=0; i<x+d1; i++) {
			for(int j=0; j<=y; j++) {
				if(sun[i][j]==5) break;
				sun[i][j] = 1;
				peo1 += map[i][j];
			}
		}
		if(peo1<min) min = peo1;
		if(peo1>max) max = peo1;
		
		//2번 선거구
		for(int i=0; i<=x+d2; i++) {
			for(int j=y+1; j<n; j++) {
				if(sun[i][j]==5) continue;
				sun[i][j] = 2;
				peo2 += map[i][j];
			}
		}
		if(peo2<min) min = peo2;
		if(peo2>max) max = peo2;
		
		//3번 선거구
		for(int i=x+d1; i<n; i++) {
			for(int j=0; j<y-d1+d2; j++) {
				if(sun[i][j]==5) break;
				sun[i][j] = 3;
				peo3 += map[i][j];
			}
		}
		if(peo3<min) min = peo3;
		if(peo3>max) max = peo3;
		
		//4번 선거구
		for(int i=x+d2+1; i<n; i++) {
			for(int j=y-d1+d2; j<n; j++) {
				if(sun[i][j]==5) continue;
				sun[i][j] = 4;
				peo4 += map[i][j];
			}
		}
		if(peo4<min) min = peo4;
		if(peo4>max) max = peo4;
		
		if(result>max-min) {
			result = max-min;
//			System.out.println(x+" " +y + " " + d1 + " " + d2);
//			System.out.println(result);
//			printMap();
		}
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(sun[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}

}
