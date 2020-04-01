import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1018 {

	public static int n;
	public static int m;
	public static char[][] map;
	public static char[][] tempMap;
	public static int tempN;
	public static int tempM;
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1018.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		tempMap = new char[8][8];
		tempN = n-7;
		tempM = m-7;
		for(int i=0; i<tempN; i++) {
			for(int j=0; j<tempM; j++) {
				for(int k=0; k<8; k++) {
					tempMap[k] = Arrays.copyOfRange(map[k+i], j, j+8);
//					System.out.println(Arrays.toString(tempMap[k]));
				}
//				System.out.println();
				solve();
			}
		}
		
		System.out.println(result);
		
		br.close();
	}
	
	public static void solve() {
		char firstC = 'W';
		char currentC = 'W';
		int count = 0;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(currentC!=tempMap[i][j]) {
					count++;
				}
				if(currentC=='W') {
					currentC = 'B';
				}else {
					currentC = 'W';
				}
			}
			if(firstC == 'W') {
				firstC = 'B';
				currentC = 'B';
			}else {
				firstC = 'W';
				currentC = 'W';
			}
		}
		if(count<result) {
			result = count;
		}

		
		firstC = 'B';
		currentC = 'B';
		count = 0;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(currentC!=tempMap[i][j]) {
					count++;
				}
				if(currentC=='W') {
					currentC = 'B';
				}else {
					currentC = 'W';
				}
			}
			if(firstC == 'W') {
				firstC = 'B';
				currentC = 'B';
			}else {
				firstC = 'W';
				currentC = 'W';
			}
		}
		if(count<result) {
			result = count;
		}
	}

}
