import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main5373 {

	public static int n;
	public static char[][][] cube;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5373.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		cube = new char[6][3][3];
		reset();
		n = Integer.parseInt(br.readLine());

		
		
		for(int k=0; k<6; k++) {
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					System.out.print(cube[k][i][j] + " ");
				}System.out.println();
			}System.out.println();
		}
		
		int turn = 0;
		String ins = "";
		for(int k=0; k<n; k++) {
			turn = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<turn; j++) {
				ins = st.nextToken();
				turnCube(ins.substring(0, 1), ins.substring(1,2));
			}
			System.out.println("===========" + k + " 번째==============");
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					System.out.print(cube[0][i][j] + " ");
				}System.out.println();
			}System.out.println();
			reset();
		}

		br.close();
	}

	public static void turnCube(String a, String b) {
		//상:0	 하:1	 	좌:2		우:3 	앞:4 	뒤:5
		char[][] copy = new char[3][3];
		char[] back = new char[3];
		char[] front = new char[3];
		char[] right = new char[3];
		char[] left = new char[3];
		int idx = 0;
		int up=0, down=1, le=2, ri=3, fr=4, ba=5;
		
		if(a.equals("L")) {
			if(b.equals("+")) {
				idx = 2;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[le][i][j];
					}
					idx--;
					front[i] = cube[fr][2-i][0];
					back[i] = cube[ba][2-i][2];
					left[i] = cube[down][i][2];
					right[i] = cube[up][i][0];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[le][i][j] = copy[i][j];
					}
					cube[down][i][2] = front[i];
					cube[up][i][0] = back[i];
					cube[ba][i][2] = left[i];
					cube[fr][i][0] = right[i];
				}
			}else {
				idx = 0;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[le][i][j];
					}
					idx++;
					front[i] = cube[ba][i][2];
					back[i] = cube[fr][i][0];
					left[i] = cube[up][2-i][0];
					right[i] = cube[down][2-i][2];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[le][i][j] = copy[i][j];
					}
					cube[down][i][2] = front[i];
					cube[up][i][0] = back[i];
					cube[ba][i][2] = left[i];
					cube[fr][i][0] = right[i];
				}
			}
		}else if(a.equals("R")) {
			if(b.equals("+")) {
				idx = 2;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[ri][i][j];
					}
					idx--;
					front[i] = cube[ba][i][0];
					back[i] = cube[fr][i][2];
					left[i] = cube[down][2-i][0];
					right[i] = cube[up][2-i][2];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[ri][i][j] = copy[i][j];
					}
					cube[down][i][0] = front[i];
					cube[up][i][2] = back[i];
					cube[fr][i][2] = left[i];
					cube[ba][i][0] = right[i];
				}
			}else {
				idx = 0;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[ri][i][j];
					}
					idx++;
					front[i] = cube[fr][2-i][2];
					back[i] = cube[ba][2-i][0];
					left[i] = cube[up][i][2];
					right[i] = cube[down][i][0];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[ri][i][j] = copy[i][j];
					}
					cube[down][i][0] = front[i];
					cube[up][i][2] = back[i];
					cube[fr][i][2] = left[i];
					cube[ba][i][0] = right[i];
				}
			}
		}else if(a.equals("F")) {
			if(b.equals("+")) {
				idx = 2;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[fr][i][j];
					}
					idx--;
					front[i] = cube[ri][i][0];
					back[i] = cube[le][2-i][2];
					left[i] = cube[down][2][2-i];
					right[i] = cube[up][2][i];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[fr][i][j] = copy[i][j];
					}
					cube[down][2][i] = front[i];
					cube[up][2][i] = back[i];
					cube[le][i][2] = left[i];
					cube[ri][i][0] = right[i];
				}
			}else {
				idx = 0;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[fr][i][j];
					}
					idx++;
					front[i] = cube[le][2-i][2];
					back[i] = cube[ri][i][0];
					left[i] = cube[up][2][2-i];
					right[i] = cube[down][2][i];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[fr][i][j] = copy[i][j];
					}
					cube[down][2][i] = front[i];
					cube[up][2][i] = back[i];
					cube[le][i][2] = left[i];
					cube[ri][i][0] = right[i];
				}
			}
		}else if(a.equals("B")) {
			if(b.equals("+")) {
				idx = 2;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[ba][i][j];
					}
					idx--;
					front[i] = cube[le][2-i][0];
					back[i] = cube[ri][i][2];
					left[i] = cube[down][0][i];
					right[i] = cube[up][0][2-i];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[ba][i][j] = copy[i][j];
					}
					cube[down][0][i] = front[i];
					cube[up][0][i] = back[i];
					cube[ri][i][2] = left[i];
					cube[le][i][0] = right[i];
				}
			}else {
				idx = 0;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[ba][i][j];
					}
					idx++;
					front[i] = cube[ri][i][2];
					back[i] = cube[le][2-i][2];
					left[i] = cube[up][0][i];
					right[i] = cube[down][0][2-i];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[ba][i][j] = copy[i][j];
					}
					cube[down][0][i] = front[i];
					cube[up][0][i] = back[i];
					cube[ri][i][2] = left[i];
					cube[le][i][0] = right[i];
				}
			}
		}else if(a.equals("U")) {
			if(b.equals("+")) {
				idx = 2;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[up][i][j];
					}
					idx--;
					front[i] = cube[ri][0][i];
					back[i] = cube[le][0][i];
					left[i] = cube[fr][0][i];
					right[i] = cube[ba][0][i];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[up][i][j] = copy[i][j];
					}
					cube[fr][0][i] = front[i];
					cube[ba][0][i] = back[i];
					cube[le][0][i] = left[i];
					cube[ri][0][i] = right[i];
				}
			}else {
				idx = 0;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[up][i][j];
					}
					idx++;
					front[i] = cube[le][0][i];
					back[i] = cube[ri][0][i];
					left[i] = cube[ba][0][i];
					right[i] = cube[fr][0][i];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[up][i][j] = copy[i][j];
					}
					cube[fr][0][i] = front[i];
					cube[ba][0][i] = back[i];
					cube[le][0][i] = left[i];
					cube[ri][0][i] = right[i];
				}
			}
		}else if(a.equals("D")) {
			if(b.equals("+")) {
				idx = 2;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[down][i][j];
					}
					idx--;
					front[i] = cube[le][2][i];
					back[i] = cube[ri][2][i];
					left[i] = cube[ba][2][i];
					right[i] = cube[fr][2][i];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[down][i][j] = copy[i][j];
					}
					cube[fr][2][i] = front[i];
					cube[ba][2][i] = back[i];
					cube[ri][2][i] = left[i];
					cube[le][2][i] = right[i];
				}
			}else {
				idx = 0;
				for(int i=0; i<3; i++) {	//회전값 copy하기
					for(int j=0; j<3; j++) {
						copy[j][idx] = cube[down][i][j];
					}
					idx++;
					front[i] = cube[ri][2][i];
					back[i] = cube[le][2][i];
					left[i] = cube[fr][2][i];
					right[i] = cube[ba][2][i];
				}
				
				for(int i=0; i<3; i++) {	//copy한 값 적용
					for(int j=0; j<3; j++) {
						cube[down][i][j] = copy[i][j];
					}
					cube[fr][2][i] = front[i];
					cube[ba][2][i] = back[i];
					cube[ri][2][i] = left[i];
					cube[le][2][i] = right[i];
				}
			}
		}
	}
	
	public static void reset() {
		for(int k=0; k<6; k++) {
			for(int i=0; i<3; i++) {
				if(k==0) {
					Arrays.fill(cube[k][i], 'w');
				}else if(k==1) {
					Arrays.fill(cube[k][i], 'y');
				}else if(k==2) {
					Arrays.fill(cube[k][i], 'g');
				}else if(k==3) {
					Arrays.fill(cube[k][i], 'b');
				}else if(k==4) {
					Arrays.fill(cube[k][i], 'r');
				}else if(k==5) {
					Arrays.fill(cube[k][i], 'o');
				}
			}
		}
	}

}
