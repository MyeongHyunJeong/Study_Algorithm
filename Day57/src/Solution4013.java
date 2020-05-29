import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution4013 {
	
	public static int testCase;
	public static int k;
	public static int[][] magnet;
	public static int[] copy;
	public static int[] turn;
	public static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input4013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=testCase; t++) {
			k = Integer.parseInt(br.readLine());
			magnet = new int[4][8];
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int select = 0;
			int dir = 0;
//			printMagnet();
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				select = Integer.parseInt(st.nextToken())-1;
				dir = Integer.parseInt(st.nextToken());
				turn = new int[4];
				copy = new int[8];
				getResult(select, dir);
//				System.out.println(Arrays.toString(turn));
			}
			
			result = 0;
			for(int i=0; i<4; i++) {
				if(magnet[i][0]==1) {
					if(i==0) {
						result += 1;
					}else if(i==1) {
						result += 2;
					}else if(i==2) {
						result += 4;
					}else {
						result +=8;
					}
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
	
	public static void getResult(int select, int dir) {
		turn[select] = dir;
		int idx = select;
		for(int i=select-1; i>=0; i--) {
			if(magnet[idx][6]!=magnet[i][2]) {
				if(turn[idx]==1) {
					turn[i] = -1;
				}else {
					turn[i] = 1;
				}
			}else {
				turn[i] = 0;
				break;
			}
			idx = i;
		}
		idx = select;
		for(int i=select+1; i<4; i++) {
			if(magnet[idx][2]!=magnet[i][6]) {
				if(turn[idx]==1) {
					turn[i] = -1;
				}else {
					turn[i] = 1;
				}
			}else {
				turn[i] = 0;
				break;
			}
			idx = i;
		}
//		System.out.println(Arrays.toString(turn));
		doTurn();
//		printMagnet();
	}
	
	
	public static void doTurn() {
		int temp = 0;
		for(int k=0; k<4; k++) {
			if(turn[k]==1) {
				copyMagnet(k);
				for(int i=0; i<8; i++) {
					magnet[k][(i+1)%8] = copy[i];
				}
			}else if(turn[k]==-1) {
				copyMagnet(k);
				for(int i=0; i<8; i++) {
					magnet[k][i] = copy[(i+1)%8];
				}
			}
		}
	}
	
	public static void copyMagnet(int a) {
		for(int i=0; i<8; i++) {
			copy[i] = magnet[a][i];
		}
	}
	
	public static void printMagnet() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<8; j++) {
				System.out.print(magnet[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}

}
