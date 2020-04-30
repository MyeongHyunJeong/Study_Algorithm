import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main18808 {

	public static int n;
	public static int m;
	public static int k;
	public static int[][] notebook;
	public static ArrayList<int[][]> stickers;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input18808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		notebook = new int[n][m];
		stickers = new ArrayList<int[][]>();
		int nn,mm;
		int[][] temp;
		for(int kk=0; kk<k; kk++) {
			st = new StringTokenizer(br.readLine());
			nn = Integer.parseInt(st.nextToken());
			mm = Integer.parseInt(st.nextToken());
			temp = new int[nn][mm];
			for(int i=0; i<nn; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<mm; j++) {
					temp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			stickers.add(temp);
		}

		getResult();

		int result = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(notebook[i][j] == 1) {
					result++;
				}
			}
		}
		System.out.println(result);


		br.close();
	}

	public static void getResult() {
		int[][] sticker;
		int[][] temp;
		int nn;
		int mm;
		int count;
		boolean flag = false;
		boolean stick = false;
		for(int kk=0; kk<k; kk++) {
			sticker = stickers.get(kk);
			count = 0;
			stick = false;
			while(true) {
//				for(int i=0; i<sticker.length; i++) {
//					for(int j=0; j<sticker[i].length; j++) {
//						System.out.print(sticker[i][j] + " ");
//					}System.out.println();
//				}System.out.println();
				nn = sticker.length;
				mm = sticker[0].length;
//				System.out.println("nn : " + nn + " mm : " + mm);
				for(int i=0; i<n; i++) {
					for(int j=0; j<m; j++) {
						flag = false;
//						System.out.println("i : " + i  + " j : " + j);
//						System.out.println("i+nn : " + (i+nn) + " j+mm : " + (j+mm));
						if(i+nn<=n && j+mm<=m) {
//							System.out.println(i + " " + j);
							for(int r=i; r<i+nn; r++) {
								for(int l=j; l<j+mm; l++) {
									if(sticker[r-i][l-j]==1 && notebook[r][l]==1) {
										flag = true;
										break;
									}
								}
								if(flag) break;
							}
							if(!flag) {
								for(int r=i; r<i+nn; r++) {
									for(int l=j; l<j+mm; l++) {
										if(sticker[r-i][l-j]==1) {
											notebook[r][l] = sticker[r-i][l-j];
										}
									}
								}
								stick = true;
							}
						}
						if(stick) break;
					}
					if(stick) break;
				}

				if(count==3) {
					break;
				}
				if(!stick) {
					temp = new int[mm][nn];
					int x = nn-1;
					for(int i=0; i<nn; i++) {
						for(int j=0; j<mm; j++) {
							temp[j][x] = sticker[i][j];

						}
						x--;
					}
					sticker = temp;
					count++;
				}else break;
			}




		}
	}
}

