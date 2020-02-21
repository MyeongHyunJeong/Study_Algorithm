import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main14500 {
	
	public static int n;
	public static int m;
	public static int[][] map;
	public static int result = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input14500.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//초기화
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n+6][m+6];
		for(int i=3; i<n+3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=3; j<m+3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		printMap();
		
		int[][] mask;
		int x, y, cal;
		for(int k=0; k<19; k++) {
			mask = selectMask(k);
//			System.out.println(Arrays.deepToString(mask));
			//맴 전체 돌기
			for(int i=0; i<n+3; i++) {
				for(int j=0; j<m+3; j++) {
					//계산 mask 크기 만큼
					cal = 0;
					for(int r=0; r<mask.length; r++) {
						for(int l=0; l<mask[r].length; l++) {
							x = i + r;
							y = j + l;
							if(mask[r][l]==1) {
								cal += map[x][y];
							}
						}
					}
					if(cal>result) {
						result = cal;
					}
				}
			}
		}
		
		System.out.println(result);
		
		br.close();
	}
	
	public static int[][] selectMask(int idx) {
		int[][] mask;
		switch (idx) {
		case 0:
			mask = new int[1][4];
			Arrays.fill(mask[0], 1);
			break;
		case 1:
			mask = new int[4][1];
			for(int i=0; i<4; i++) {
				mask[i][0] = 1;
			}
			break;
		case 2:
			mask = new int[2][2];
			for(int i=0; i<2; i++) {
				Arrays.fill(mask[i], 1);
			}
			break;
		case 3:
			mask = new int[3][2];
			for(int i=0; i<3; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][1] = 0;
			mask[1][1] = 0;
			break;
		case 4:
			mask = new int[3][2];
			for(int i=0; i<3; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[1][0] = 0;
			mask[2][0] = 0;
			break;
		case 5:
			mask = new int[3][2];
			for(int i=0; i<3; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][0] = 0;
			mask[1][0] = 0;
			break;
		case 6:
			mask = new int[3][2];
			for(int i=0; i<3; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[1][1] = 0;
			mask[2][1] = 0;
			break;
		case 7:
			mask = new int[2][3];
			for(int i=0; i<2; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[1][1] = 0;
			mask[1][2] = 0;
			break;
		case 8:
			mask = new int[2][3];
			for(int i=0; i<2; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][0] = 0;
			mask[0][1] = 0;
			break;
		case 9:
			mask = new int[2][3];
			for(int i=0; i<2; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[1][0] = 0;
			mask[1][1] = 0;
			break;
		case 10:
			mask = new int[2][3];
			for(int i=0; i<2; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][1] = 0;
			mask[0][2] = 0;
			break;
		case 11:
			mask = new int[3][2];
			for(int i=0; i<3; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][1] = 0;
			mask[2][0] = 0;
			break;
		case 12:
			mask = new int[3][2];
			for(int i=0; i<3; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][0] = 0;
			mask[2][1] = 0;
			break;
		case 13:
			mask = new int[2][3];
			for(int i=0; i<2; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][0] = 0;
			mask[1][2] = 0;
			break;
		case 14:
			mask = new int[2][3];
			for(int i=0; i<2; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][2] = 0;
			mask[1][0] = 0;
			break;
		case 15:
			mask = new int[2][3];
			for(int i=0; i<2; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[1][0] = 0;
			mask[1][2] = 0;
			break;
		case 16:
			mask = new int[2][3];
			for(int i=0; i<2; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][0] = 0;
			mask[0][2] = 0;
			break;
		case 17:
			mask = new int[3][2];
			for(int i=0; i<3; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][0] = 0;
			mask[2][0] = 0;
			break;
		case 18:
			mask = new int[3][2];
			for(int i=0; i<3; i++) {
				Arrays.fill(mask[i], 1);
			}
			mask[0][1] = 0;
			mask[2][1] = 0;
			break;
		default:
			System.out.println("Mask Error!");
			mask = new int[0][0];
			break;
		}
		
		
		return mask;
	}
	
	public static void printMap() {
		for(int i=0; i<n+6; i++) {
			for(int j=0; j<m+6; j++) {
				System.out.print(map[i][j] + "\t");
			}System.out.println();
		}System.out.println();
	}

}
