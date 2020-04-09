import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17822_2 {

	public static int n;
	public static int m;
	public static int t;
	public static int[][] pan;
	public static int[][] temp;
	public static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input17822.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		pan = new int[n][m];
		temp = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
				temp[i][j] = pan[i][j];
			}
		}
//		System.out.println("====Origin====");
//		print();

		result = 0;
		int x,d,k;
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			getResult(x,d,k);
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(pan[i][j]!=0) {
					result+=pan[i][j];
				}
			}
		}
		System.out.println(result);

		br.close();
	}

	public static void getResult(int x, int d, int k) {
//		System.out.println("turn and erase");
		for(int i=0; i<n; i++) {
			if((i+1)%x==0) turn(i,d,k);
		}
		copyPan();
//		System.out.println("TURN");
//		print();
		erase();
		copyPan();
//		System.out.println("ERASE");
//		print();
	}

	public static void erase() {
		boolean chk = false;
		int count = 0;;
		int sum = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {	
				if(temp[i][j]==0) continue;
				if(pan[i][j]!=0) {	//같은 숫자 제거하지 않을 경우를 위해 남아있는 숫자와 총 합 계산
					count++;
					sum += pan[i][j];
				}
				//같은 판에서의 같은 숫자 제거
				if(j==0) {
					if(temp[i][j]==temp[i][1]) {
						chk = true;
						pan[i][j] = 0;
						pan[i][1] = 0;
					}
					if(temp[i][j]==temp[i][m-1]) {
						chk = true;
						pan[i][j] = 0;
						pan[i][m-1] = 0;
					}
				}else if(j==m-1) {
					if(temp[i][j]==temp[i][m-2]) {
						chk = true;
						pan[i][j] = 0;
						pan[i][m-2] = 0;
					}
					if(temp[i][j]==temp[i][0]) {
						chk = true;
						pan[i][j] = 0;
						pan[i][0] = 0;
					}
				}else {
					if(temp[i][j]==temp[i][j-1]) {
						chk = true;
						pan[i][j] = 0;
						pan[i][j-1] = 0;
					}
					if(temp[i][j]==temp[i][j+1]) {
						chk = true;
						pan[i][j] = 0;
						pan[i][j+1] = 0;
					}
				}
				
				//다른 판에서의 숫자제거
				if(i==0) {
					if(temp[i][j]==temp[1][j]) {
						chk = true;
						pan[i][j] = 0;
						pan[1][j] = 0;
					}
				}else if(i==n-1) {
					if(temp[i][j]==temp[n-2][j]) {
						chk = true;
						pan[i][j] = 0;
						pan[n-2][j] = 0;
					}
				}else {
					if(temp[i][j]==temp[i-1][j]) {
						chk = true;
						pan[i][j] = 0;
						pan[i-1][j] = 0;
					}
					if(temp[i][j]==temp[i+1][j]) {
						chk = true;
						pan[i][j] = 0;
						pan[i+1][j] = 0;
					}
				}
			}
		}
//		System.out.println(sum);
//		System.out.println(count);
		
		//제거한 숫자가 없을 경우 남아있는 숫자 수정
		if(!chk) {
			float cal = (float)sum / count;
//			System.out.println(cal);
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(pan[i][j]!=0) {
						if(pan[i][j]>cal) pan[i][j]--;
						else if(pan[i][j]<cal) pan[i][j]++;
					}
				}
			}
		}
	}
	
	public static void turn(int x, int d, int k) {
		if(d==0) {	//시계방향
			for(int i=0; i<m; i++) {
				pan[x][(i+k)%m] = temp[x][i];
			}
		}else {		//반시계방향
			for(int i=0; i<m; i++) {
				pan[x][i] = temp[x][(i+k)%m];
			}
		}
	}

	public static void copyPan() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				temp[i][j] = pan[i][j];
			}
		}
	}
	
	public static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(pan[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}

	public static class Instruction {
		int x;
		int d;
		int k;
		public Instruction(int x, int d, int k) {
			this.x = x;
			this.d = d;
			this.k = k;
		}
	}

}
