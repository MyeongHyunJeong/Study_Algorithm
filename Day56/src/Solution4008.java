import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution4008 {

	public static int testCase;
	public static int n;
	public static int[] cal;
	public static int[] num;
	public static int[] temp;
	public static boolean[] chk;
	public static int max;
	public static int min;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input4008.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());

		for(int t=1; t<=1; t++) {
			n = Integer.parseInt(br.readLine());
			cal = new int[4];
			num = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				cal[i] = Integer.parseInt(st.nextToken());
			}	//0:+	 1:-	 2:*	 3:/

			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
		}

		temp = new int[n-1];
		Arrays.fill(temp, -1);
		chk = new boolean[n-1];
		for(int i=0; i<n-cal[0]; i++) {
			selectPlus(n-1, cal[0], i, 0);
		}

	}

	public static void selectPlus(int n, int r, int idx, int depth) {
		if(r==depth) {
			System.out.println(Arrays.toString(temp));
			return;
		}
		if(n==depth) return;

		temp[idx] = 0;
		chk[idx] = true;
		selectPlus(n,r,idx+1,depth+1);
		temp[idx] = -1;
		chk[idx] = false;
		selectPlus(n,r,idx+1,depth+1);

	}

	public static void selectSub(int n, int r, int idx, int depth) {

	}

	public static void selectMul(int n, int r, int idx, int depth) {

	}

	public static void selectDiv(int n, int r, int idx, int depth) {

	}


}
