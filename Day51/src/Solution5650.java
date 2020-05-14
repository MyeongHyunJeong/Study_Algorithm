import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution5650 {

	public static int testCase;
	public static int n;
	public static int[][] map;
	public static Ball ball;
	public static boolean[][][] chk;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static Point[][] holes;
	public static boolean[] chkHole;
	public static int score;
	public static int maxScore = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5650.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());

		for(int t=0; t<testCase; t++) {
			maxScore = Integer.MIN_VALUE;
			n = Integer.parseInt(br.readLine());
			holes = new Point[5][2];
			chkHole = new boolean[5];
			map = new int[n][n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>5) {
						if(!chkHole[map[i][j]-6]) {
							chkHole[map[i][j]-6] = true;
							holes[map[i][j]-6][0] = new Point(i,j);
						}else {
							holes[map[i][j]-6][1] = new Point(i,j);
						}
					}
				}
			}
			System.out.println(Arrays.deepToString(map));

			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(map[i][j]==0) {
						//0:상,		1:하,	2:좌,	3:우
						chk = new boolean[n][n][4];
						ball = new Ball(i,j,0);
						score = 0;
						move();
						chk = new boolean[n][n][4];
						ball = new Ball(i,j,1);
						score = 0;
						move();
						chk = new boolean[n][n][4];
						ball = new Ball(i,j,2);
						score = 0;
						move();
						chk = new boolean[n][n][4];
						ball = new Ball(i,j,3);
						score = 0;
						move();
					}
				}
			}
			System.out.println(maxScore);

		}

		br.close();
	}

	public static void move() {
		if(map[ball.x][ball.y]==-1) {
//			System.out.println(score);
			if(score>maxScore) {
				maxScore = score;
			}
			return;
		}
		if(chk[ball.x][ball.y][ball.direction]) {
			return;
		}
		
		chk[ball.x][ball.y][ball.direction] = true;
		int x = ball.x + dir[ball.direction][0];
		int y = ball.y + dir[ball.direction][1];
		if(x>-1 && y>-1 && x<n && y<n) {
			if(map[x][y]>0 && map[x][y]<6) {
				score++;
				chk[x][y][ball.direction] = true;
				ball.x = x;	
				ball.y = y;
				selectDirection();
			}else if(map[x][y]>5) {
				for(int i=0; i<2; i++) {
					if(holes[map[x][y]-6][i].x!=x && holes[map[x][y]-6][i].y!=y) {
						chk[x][y][ball.direction] = true;
						ball.x = holes[map[x][y]-6][i].x;
						ball.y = holes[map[x][y]-6][i].y;
						break;
					}
				}
			}else {
				ball.x = x;
				ball.y = y;
			}
		}else {
			score++;
			if(ball.direction==0) {
				ball.direction=1;
			}else if(ball.direction==1) {
				ball.direction=0;
			}else if(ball.direction==2) {
				ball.direction=3;
			}else {
				ball.direction=2;
			}
		}
		
		move();
	}

	public static void selectDirection() {
		if(map[ball.x][ball.y]==1) {
			if(ball.direction==0) {
				ball.direction=1;
			}else if(ball.direction==1) {
				ball.direction=3;
			}else if(ball.direction==2) {
				ball.direction=0;
			}else {
				ball.direction=2;
			}
		}else if(map[ball.x][ball.y]==2) {
			if(ball.direction==0) {
				ball.direction=3;
			}else if(ball.direction==1) {
				ball.direction=0;
			}else if(ball.direction==2) {
				ball.direction=1;
			}else {
				ball.direction=2;
			}
		}else if(map[ball.x][ball.y]==3) {
			if(ball.direction==0) {
				ball.direction=2;
			}else if(ball.direction==1) {
				ball.direction=0;
			}else if(ball.direction==2) {
				ball.direction=3;
			}else {
				ball.direction=1;
			}
		}else if(map[ball.x][ball.y]==4) {
			if(ball.direction==0) {
				ball.direction=1;
			}else if(ball.direction==1) {
				ball.direction=2;
			}else if(ball.direction==2) {
				ball.direction=3;
			}else {
				ball.direction=0;
			}
		}else {
			if(ball.direction==0) {
				ball.direction=1;
			}else if(ball.direction==1) {
				ball.direction=0;
			}else if(ball.direction==2) {
				ball.direction=3;
			}else {
				ball.direction=2;
			}
		}
	}

	public static class Ball {
		int x;
		int y;
		int direction;
		public Ball(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

}
