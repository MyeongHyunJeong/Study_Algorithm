import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Line04_2 {
	
	public static int testCase;
	public static int n;
	public static int k;
	public static int m;
	public static int r;
	public static int[] temp;
	public static int countA;
	public static int countB;
	public static int count;
	public static double result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input04.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		testCase = Integer.parseInt(br.readLine());
		
		for(int t=0; t<testCase; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			count = 0;
			result = 0;
			if(k-m==0) {
				System.out.println(1);
				continue;
			}else {
				r = k-m;
				temp = new int[r];
				getResult(0,0);
				countA = count;
				count = 0;
				r = m;
				getResult(0,0);
				countB = count;
			}
			result = countA * countB % 100300;
			System.out.println((int)result);
		}
		
		br.close();
	}
	
	public static void getResult(int idx, int depth) {
		if(idx==r) {
			count++;
			if(count==1003001) count = 0;
			return;
		}
		if(depth==n) return;
		getResult(idx+1, depth+1);
		getResult(idx, depth+1);
		
	}

}
