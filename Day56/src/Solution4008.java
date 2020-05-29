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

		for(int t=1; t<=testCase; t++) {
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
			temp = new int[n-1];
			Arrays.fill(temp, -1);
			chk = new boolean[n-1];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			selectPlus(n-1, cal[0], 0, 0);
			System.out.println("#" + t + " " + (max-min));
		}


	}
	
	public static void getResult() {
		int result = num[0];
		for(int i=1; i<n; i++) {
			result = cal(result, num[i], temp[i-1]);
		}
		if(result>max) max = result;
		if(result<min) min = result;
	}
	
	public static int cal(int a, int b, int c) {
		if(c==0) {
			return a + b;
		}else if(c==1) {
			return a - b;
		}else if(c==2) {
			return a * b;
		}else {
			return a / b;
		}
	}

	public static void selectPlus(int n, int r, int idx, int depth) {
		if(depth==r) {
//			System.out.println("================");
//			System.out.println(Arrays.toString(temp));
			selectSub(n,cal[1],0,0);
			return;
		}
		if(idx==n) return;
		
		temp[idx] = 0;
		chk[idx] = true;
		selectPlus(n,r,idx+1,depth+1);
		temp[idx] = -1;
		chk[idx] = false;
		selectPlus(n,r,idx+1,depth);

	}

	public static void selectSub(int n, int r, int idx, int depth) {
		if(depth==r) {
			selectMul(n,cal[2],0,0);
//			System.out.println(Arrays.toString(temp));
			return;
		}
		if(idx==n) return;
		
		if(chk[idx]) selectSub(n,r,idx+1,depth);
		else {
			temp[idx] = 1;
			chk[idx] = true;
			selectSub(n,r,idx+1,depth+1);
			temp[idx] = -1;
			chk[idx] = false;
			selectSub(n,r,idx+1,depth);
		}
	}

	public static void selectMul(int n, int r, int idx, int depth) {
		if(depth==r) {
			selectDiv(n,cal[3],0,0);
//			System.out.println(Arrays.toString(temp));
			return;
		}
		if(idx==n) return;
		
		if(chk[idx]) selectMul(n,r,idx+1,depth);
		else {
			temp[idx] = 2;
			chk[idx] = true;
			selectMul(n,r,idx+1,depth+1);
			temp[idx] = -1;
			chk[idx] = false;
			selectMul(n,r,idx+1,depth);
		}
	}

	public static void selectDiv(int n, int r, int idx, int depth) {
		if(depth==r) {
//			System.out.println(Arrays.toString(temp));
			getResult();
			return;
		}
		if(idx==n) return;
		
		if(chk[idx]) selectDiv(n,r,idx+1,depth);
		else {
			temp[idx] = 3;
			chk[idx] = true;
			selectDiv(n,r,idx+1,depth+1);
			temp[idx] = -1;
			chk[idx] = false;
			selectDiv(n,r,idx+1,depth);
		}
	}


}
