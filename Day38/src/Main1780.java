import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1780 {
	
	public static int n;
	public static int[][] map;
	public static int countA;
	public static int countB;
	public static int countC;
	public static boolean[][] chk;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1780.txt"));
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
		
		countA = 0;
		countB = 0;
		countC = 0;
		chk = new boolean[n][n];
		for(int i=n; i>=1; i/=3) {
			cutPaper(i);
		}
		
		System.out.println(countA);
		System.out.println(countB);
		System.out.println(countC);
		
		br.close();
	}
	
	public static void cutPaper(int size) {
		for(int i=0; i<n; i+=size) {
			for(int j=0; j<n; j+=size) {
				if(!chk[i][j]) compareNum(i,j, i+size, j+size);
			}
		}
	}
	
	public static void compareNum(int x, int y, int n, int m) {
		int num = map[x][y];
		boolean flag = true;
		for(int i=x; i<n; i++) {
			for(int j=y; j<m; j++) {
				if(map[i][j]!=num) {
					flag = false;
					break;
				}
			}
			if(!flag) break;
		}
		
		if(flag) {
			for(int i=x; i<n; i++) {
				for(int j=y; j<m; j++) {
					chk[i][j] = true;
				}
			}
			if(num==-1) {
				countA++;
			}else if(num==0) {
				countB++;
			}else {
				countC++;
			}
		}
		
	}

}
