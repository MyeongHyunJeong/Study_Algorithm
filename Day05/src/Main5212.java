import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main5212 {

	public static int n;
	public static int m;
	public static char[][] map;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int minX = Integer.MAX_VALUE;
	public static int maxX = Integer.MIN_VALUE;
	public static int minY = Integer.MAX_VALUE;
	public static int maxY = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5212.txt"));
		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		m = s.nextInt();
		map = new char[n][m];
		for(int i=0; i<map.length; i++) {
			map[i] = s.next().toCharArray();
		}
		char[][] result = new char[n][m];
		
		int count = 0;
		int nx = 0;
		int ny = 0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				result[i][j] = '.';
				if(map[i][j]=='X') {
					count = 0;
					for(int k=0; k<dir.length; k++) {
						nx = i + dir[k][0];
						ny = j + dir[k][1];
						if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length || map[nx][ny]=='.') {
							count++;
							if(count>=3) {
								result[i][j] = '.';
								break;
							}
						}
					}
					if(count<3) {
						result[i][j] = 'X';
						if(i>maxX) {
							maxX = i;
						}
						if(i<minX) {
							minX = i;
						}
						if(j>maxY) {
							maxY = j;
						}
						if(j<minY) {
							minY = j;
						}
					}
				}
			}
		}
		
		for(int i=minX; i<=maxX; i++) {
			for(int j=minY; j<=maxY; j++) {
				System.out.print(result[i][j]);
			}System.out.println();
		}
		
//		System.out.println(Arrays.deepToString(result));
		
		
	}

}
