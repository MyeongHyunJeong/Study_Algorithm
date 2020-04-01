import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Line04 {
	
	public static int testCase;
	public static int n;	//전체 스티커, 테마 수
	public static int k;	//살 수 있는 갯수
	public static int m;	//산 스티커 갯수
	public static int result;
	
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
			result = 0;
			
			result = calCombination(n,m) * calCombination(n, k-m);	//O(N)
			
			System.out.println(result);
			
		}
		
		br.close();
	}
	
	public static int calCombination(int n, int r) {
		if(n==0 || r==0) {
			return 1;
		}else {
			int temp = 1;
			int tempN = n;
			for(int i=0; i<r; i++) {
				temp *= tempN;
				tempN--;
			}
			return temp / r;
		}
	}

}
