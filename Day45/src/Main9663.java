import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main9663 {
	
	public static int n;
	public static int result;
	public static ArrayList<int[][]> maps;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input9663.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		maps = new ArrayList<int[][]>();
		
		result = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				maps.add(new int[n][n]);
			}
			putQueen(0,i,0);
			maps.clear();
		}
		System.out.println(result);
		
		br.close();
	}
	
	public static void putQueen(int x, int y, int idx) {
		if(idx==n-1) {
			for(int i=0; i<n; i++) {
				if(maps.get(n-1)[n-1][i]==0) {
					result++;
				}
			}
			return;
		}
		
		for(int i=0; i<n; i++) {
			maps.get(idx)[x][i] = 1;
			maps.get(idx)[i][y] = 1;
		}
		maps.get(idx)[x][y] = 2;
		int yy=y;
		int yyy = y;
		for(int i=x-1; i>=0; i--) {
			if(yy-1>-1) {
				maps.get(idx)[i][--yy] = 1;
			}
			if(yyy+1<n) {
				maps.get(idx)[i][++yyy] = 1;
			}
		}
		yy = y;
		yyy = y;
		for(int i=x+1; i<n; i++) {
			if(yy-1>-1) {
				maps.get(idx)[i][--yy] = 1;
			}
			if(yyy+1<n) {
				maps.get(idx)[i][++yyy] = 1;
			}
		}
		
		for(int i=0; i<n; i++) {
			if(idx<n-1 && maps.get(idx)[idx+1][i]==0) {
				for(int j=0; j<n; j++) {
					for(int k=0; k<n; k++) {
						maps.get(idx+1)[j][k] = maps.get(idx)[j][k];
					}
				}
				putQueen(idx+1, i, idx+1);
			}
		}
		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(maps.get(n-1)[i][j] + " ");
//			}System.out.println();
//		}System.out.println();
	}

}
