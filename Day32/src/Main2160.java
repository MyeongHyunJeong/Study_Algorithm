import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2160 {
	
	public static int n;
	public static char[][][] map;
	public static int[] temp;
	public static String result;
	public static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2160.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][5][7];
		for(int k=0; k<n; k++) {
			for(int i=0; i<5; i++) {
				map[k][i] = br.readLine().toCharArray();
			}
		}
//		printMap();
		
		temp = new int[2];
		result = "";
		select(n,2,0,0);
		System.out.println(result);
		
		br.close();
	}
	
	public static void select(int n, int r, int idx, int depth) {
		if(idx==r) {
//			System.out.println(Arrays.toString(temp));
			compare(temp[0], temp[1]);
			return;
		}
		if(depth==n) return;
		temp[idx] = depth;
		select(n,r,idx+1,depth+1);
		select(n,r,idx,depth+1);
	}
	
	public static void compare(int a, int b) {
		int count = 0;
		for(int i=0; i<5; i++) {
			for(int j=0; j<7; j++) {
				if(map[a][i][j]==map[b][i][j]) {
					count++;
				}
			}
		}
		
		if(count>max) {
			max = count;
			result = (a+1) + " " + (b+1);
		}
	}
	
	public static void printMap() {
		for(int k=0; k<n; k++) {
			for(int i=0; i<5; i++) {
				for(int j=0; j<7; j++) {
					System.out.print(map[k][i][j] + " ");
				}System.out.println();
			}System.out.println();System.out.println();
		}
	}

}
