import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2630 {
	
	public static int n;
	public static int[][] map;
	public static boolean[][] chk;
	public static int countW;
	public static int countB;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2630.txt"));
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
		
		chk = new boolean[n][n];
		countW = 0;
		countB = 0;
		for(int i=n; i>=1; i=i/2) {
//			System.out.println(i);
			count(i);
		}
		
		System.out.println(countW);
		System.out.println(countB);
		
		br.close();
	}
	
	public static void count(int size) {
		for(int k=0; k<n; k+=size) {
			for(int r=0; r<n; r+=size) {
				if(!chk[k][r])	chkColor(k,r,k+size,r+size);
			}
		}
	}
	
	public static void chkColor(int x, int y, int n, int m) {
		int color = map[x][y];
		boolean flag = true;
		for(int i=x; i<n; i++) {
			for(int j=y; j<m; j++) {
				if(map[i][j]!=color) {
					flag = false;
					break;
				}
//				System.out.print(map[i][j] + " ");
			}
			if(!flag) break;
		}
		
		if(flag) {
			for(int i=x; i<n; i++) {
				for(int j=y; j<m; j++) {
					chk[i][j] = true;
				}
			}
			if(color==1) countB++;
			else countW++;
		}
	}

}
