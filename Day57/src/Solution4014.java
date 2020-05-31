import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4014 {

	public static int testCase;
	public static int n;
	public static int x;
	public static int[][] map;
	public static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());

		for(int t=1; t<=testCase; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			int height = 0;
			int count = 0;
			boolean chk = false;
			boolean upChk = false;
			boolean downChk = false;
			boolean flag = false;
			result = 0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}

				height = map[i][0];
				count = 1;
				chk = false;
				upChk = false;
				downChk = false;
				flag = true;
				for(int j=1; j<n; j++) {
					if(height == map[i][j]) {
						count++;
						if(count==x) {
							if(downChk) {
								downChk = false;
								chk = false;
								count = 0;
							}else {
								chk = true;
								count = 0;
							}
						}
					}else if(map[i][j]-height==1) {	//올라가는거
						height = map[i][j];
						if(chk) {
							chk = false;
							count = 1;
						}else {
							flag = false;
							break;
						}
					}else if(map[i][j]-height==-1) {	//내려가는거
						height = map[i][j];
						if(!downChk) {
							downChk = true;
							count = 1;
							chk = false;
						}else {
							if(chk) {
								downChk = true;
								count = 1;
								chk = false;
							}else {
								flag = false;
								break;
							}
						}
					}else {
						flag = false;
						break;
					}
				}
				if(!flag || (!chk && downChk)) continue;
				else {
					result++;
				}
//				System.out.println("I: " + i + "  " + result);
			}

			for(int j=0; j<n; j++) {
				height = map[0][j];
				count = 1;
				chk = false;
				upChk = false;
				downChk = false;
				flag = true;
				for(int i=1; i<n; i++) {
					if(height == map[i][j]) {
						count++;
						if(count==x) {
							if(downChk) {
								downChk = false;
								chk = false;
								count = 0;
							}else {
								chk = true;
								count = 0;
							}
						}
					}else if(map[i][j]-height==1) {	//올라가는거
						height = map[i][j];
						if(chk) {
							chk = false;
							count = 1;
						}else {
							flag = false;
							break;
						}
					}else if(map[i][j]-height==-1) {	//내려가는거
						height = map[i][j];
						if(!downChk) {
							downChk = true;
							count = 1;
							chk = false;
						}else {
							if(chk) {
								downChk = true;
								count = 1;
								chk = false;
							}else {
								flag = false;
								break;
							}
						}
					}else {
						flag = false;
						break;
					}
				}
				if(!flag || (!chk && downChk)) continue;
				else {
					result++;
				}
//				System.out.println("J: " + j + "  " + result);

			}
			System.out.println("#" + t + " " + result);

		}
	}
}
