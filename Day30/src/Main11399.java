import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main11399 {
	
	public static int p;
	public static int[] time;
	public static int[] temp;
	public static boolean[] chk;
	public static int result = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input11399.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		p = Integer.parseInt(br.readLine());
		time = new int[p];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<p; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		temp = new int[p];
		chk = new boolean[p];
		getResult(5,0);
		System.out.println(result);
		br.close();
	}
	
	public static void getResult(int n, int idx) {
		if(idx==n) {
//			System.out.println(Arrays.toString(time));
//			System.out.println(Arrays.toString(temp));
			calTime();
			return;
		}
		for(int i=0; i<n; i++) {	//	순열
			if(!chk[i]) {
				chk[i] = true;
				temp[idx] = i;
				getResult(n,idx+1);
				chk[i] = false;
			}
		}
	}
	
	public static void calTime() {
		int[] count = new int[p];
		count[0] = time[temp[0]];
		int tempResult = count[0];
		for(int i=1; i<p; i++) {
			count[i] = count[i-1] + time[temp[i]];
			tempResult += count[i];
		}
//		System.out.println(Arrays.toString(count));
		if(tempResult<result) {
			result = tempResult;
		}
	}

}
