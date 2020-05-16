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
	public static int startX;
	public static int startY;
	public static int count;

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
//			System.out.println(Arrays.deepToString(map));

			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(map[i][j]==0) {
						startX = i;
						startY = j;
						count = 0;
						//0:상,		1:하,	2:좌,	3:우
						chk = new boolean[n][n][4];
						ball = new Ball(i,j,0);
						score = 0;
						move();
						count = 0;
						chk = new boolean[n][n][4];
						ball = new Ball(i,j,1);
						score = 0;
						move();
						count = 0;
						chk = new boolean[n][n][4];
						ball = new Ball(i,j,2);
						score = 0;
						move();
						count = 0;
						chk = new boolean[n][n][4];
						ball = new Ball(i,j,3);
						score = 0;
						move();
					}
				}
			}
			System.out.println("#" + (t+1) + " " + maxScore);

		}

		br.close();
	}

	public static void move() {
		if(map[ball.x][ball.y]==-1) {
			if(score>maxScore) maxScore = score;
			return;
		}
		if(ball.x==startX && ball.y==startY && count!=0) {
			if(score>maxScore) maxScore = score;
			return;
		}
		if(chk[ball.x][ball.y][ball.direction]) return;
		
		chk[ball.x][ball.y][ball.direction] = true;
		int nx = ball.x + dir[ball.direction][0];
		int ny = ball.y + dir[ball.direction][1];
		
		if(nx>-1 && ny>-1 && nx<n && ny<n) {
			if(map[nx][ny]==-1 || map[nx][ny]==0) {
				ball.x = nx;
				ball.y = ny;
			}else if(map[nx][ny]>0 && map[nx][ny]<6) {
				selectDirection(nx,ny);
				ball.x = nx;
				ball.y = ny;
				score++;
			}else if(map[nx][ny]>5) {
				for(int i=0; i<2; i++) {
					if(holes[map[nx][ny]-6][i].x!=nx && holes[map[nx][ny]-6][i].y!=ny) {
						ball.x = holes[map[nx][ny]-6][i].x;
						ball.y = holes[map[nx][ny]-6][i].y;
					}
				}
			}
		}else {
			score++;
			if(ball.direction==0) {
				ball.direction = 1;
			}else if(ball.direction==1) {
				ball.direction = 0;
			}else if(ball.direction==2) {
				ball.direction = 3;
			}else if(ball.direction==3) {
				ball.direction = 2;
			}
		}
		
		count++;
		move();
	}

	public static void selectDirection(int x, int y) {
		if(map[x][y]==1) {
			if(ball.direction==0) {
				ball.direction=1;
			}else if(ball.direction==1) {
				ball.direction=3;
			}else if(ball.direction==2) {
				ball.direction=0;
			}else {
				ball.direction=2;
			}
		}else if(map[x][y]==2) {
			if(ball.direction==0) {
				ball.direction=3;
			}else if(ball.direction==1) {
				ball.direction=0;
			}else if(ball.direction==2) {
				ball.direction=1;
			}else {
				ball.direction=2;
			}
		}else if(map[x][y]==3) {
			if(ball.direction==0) {
				ball.direction=2;
			}else if(ball.direction==1) {
				ball.direction=0;
			}else if(ball.direction==2) {
				ball.direction=3;
			}else {
				ball.direction=1;
			}
		}else if(map[x][y]==4) {
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
