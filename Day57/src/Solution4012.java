import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution4012 {
	
	public static int testCase;
	public static int n;
	public static int[][] map;
	public static boolean[] chk;
	public static int min;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=testCase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			chk = new boolean[n];
			min = Integer.MAX_VALUE;
			selectRecipe(n,n/2,0,0);
			System.out.println("#" + t + " " + min);
			
		}
		
	}
	
	public static void cal() {
		int a = 0;
		int b = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==j) continue;
				if(chk[i] && chk[j]) {
					a += map[i][j];
				}else if(!chk[i] && !chk[j]) {
					b += map[i][j];
				}
			}
		}
		
//		System.out.println(a + " " + b);
		if(Math.abs(a-b)<min) min = Math.abs(a-b);
		
	}
	
	public static void selectRecipe(int n, int r, int idx, int depth) {
		if(depth==r) {
//			System.out.println(Arrays.toString(chk));
			cal();
			return;
		}
		if(idx==n) return;
		
		chk[idx] = true;
		selectRecipe(n,r,idx+1,depth+1);
		chk[idx] = false;
		selectRecipe(n,r,idx+1,depth);
	}
	

}
