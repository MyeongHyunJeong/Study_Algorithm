import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Line05 {
	public static int n;
	public static Work[] works;
	public static boolean[] chkDay;
	public static boolean[] doneWork;
	public static boolean[] resultDoneWork;
	public static int result = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input05.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		works = new Work[n];
		
		int m,d,i;
		for(int j=0; j<n; j++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			works[j] = new Work(m,d,i);
		}
		
		resultDoneWork = new boolean[n];
		for(int j=0; j<n; j++) {
			int k=0;
			chkDay = new boolean[201];
			doneWork = new boolean[n];
			doneWork[j] = true;
			Arrays.fill(chkDay, works[j].m, works[j].m+works[j].d, true);
			startWork(j, k-1, works[j].i);
		}
		
		System.out.println(result);
		for(int j=0; j<n; j++) {
			if(resultDoneWork[j]) {
				System.out.println(works[j].m + " " + works[j].d);
			}
		}
		br.close();
	}
	
	public static void startWork(int idx, int day, int incentive) {
		for(int j=idx+1; j<n; j++) {
			if(!chkDay[works[j].m]) {
				doneWork[j] = true;
				Arrays.fill(chkDay, works[j].m, works[j].m+works[j].d, true);
				startWork(j, day+works[j].d-1, incentive+works[j].i);
				
				doneWork[j] = false;
				Arrays.fill(chkDay, works[j].m, works[j].m+works[j].d, false);
				startWork(j, day, incentive);
			}
		}
		if(result<incentive) {
			result = incentive;
			for(int j=0; j<n; j++) {
				resultDoneWork[j] = doneWork[j];
			}
		}
	}
	
	public static class Work {
		int m;
		int d;
		int i;
		public Work(int m, int d, int i) {
			this.m = m;
			this.d = d;
			this.i = i;
		}
	}

}
