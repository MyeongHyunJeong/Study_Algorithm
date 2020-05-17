import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5648 {

	public static int testCase;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static ArrayList<Atom> arr;
	public static int n;
	public static int[][] chk;
	public static int energy;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5648.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());

		for(int t=0; t<testCase; t++) {
//			System.out.println("======================"+t+"===============");
			n = Integer.parseInt(br.readLine());
			energy = 0;
			arr = new ArrayList<Atom>();
			chk = new int[2001][2001];
			int x,y,direction,power;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken())+1000;
				x = 1000-Integer.parseInt(st.nextToken());
				direction = Integer.parseInt(st.nextToken());
				power = Integer.parseInt(st.nextToken());
//				System.out.println(x + " " + y  + " " + direction  +  " " + power);
				arr.add(new Atom(x,y,direction,power));
				chk[x][y] = i+1;
			}

			move();
			System.out.println("#" + (t+1) + " " + energy);
			arr.clear();
		}

		br.close();
	}

	public static void move() {
		int size;
		Atom temp;
		int nx,ny;
		for(int s=0; s<2001; s++) {
			size = arr.size();
			for(int i=0; i<size; i++) {
				temp = arr.get(i);
				if(temp.power==-1) continue;
				if(temp.direction==-1) {
					continue;
				}else {
					nx = temp.x + dir[temp.direction][0];
					ny = temp.y + dir[temp.direction][1];
				}
				if(nx>-1 && ny>-1 && nx<2001 && ny<2001) {
					if(chk[nx][ny]!=0) {
						if(chk[nx][ny]<i+1) {
							if(arr.get(chk[nx][ny]-1).power==-1) {
								energy += temp.power;
							}else {
								energy += temp.power + arr.get(chk[nx][ny]-1).power;
							}
							temp.power = -1;
							arr.get(chk[nx][ny]-1).power = -1;
						}else {
							if(arr.get(chk[nx][ny]-1).direction==-1) {
								if(arr.get(chk[nx][ny]-1).power==-1) {
									energy += temp.power;
								}else {
									energy += temp.power + arr.get(chk[nx][ny]-1).power;
								}
								temp.power = -1;
								arr.get(chk[nx][ny]-1).power = -1;
							}else {
								if(temp.direction==0) {
									if(arr.get(chk[nx][ny]-1).direction==1) {
										if(arr.get(chk[nx][ny]-1).power==-1) {
											energy += temp.power;
										}else {
											energy += temp.power + arr.get(chk[nx][ny]-1).power;
										}
										temp.power = -1;
										arr.get(chk[nx][ny]-1).power = -1;
									}else {
										chk[temp.x][temp.y] = 0;
										chk[nx][ny] = i+1;
										temp.x = nx;
										temp.y = ny;
									}
								}else if(temp.direction==1) {
									if(arr.get(chk[nx][ny]-1).direction==0) {
										if(arr.get(chk[nx][ny]-1).power==-1) {
											energy += temp.power;
										}else {
											energy += temp.power + arr.get(chk[nx][ny]-1).power;
										}
										temp.power = -1;
										arr.get(chk[nx][ny]-1).power = -1;
									}else {
										chk[temp.x][temp.y] = 0;
										chk[nx][ny] = i+1;
										temp.x = nx;
										temp.y = ny;
									}
								}else if(temp.direction==2) {
									if(arr.get(chk[nx][ny]-1).direction==3) {
										if(arr.get(chk[nx][ny]-1).power==-1) {
											energy += temp.power;
										}else {
											energy += temp.power + arr.get(chk[nx][ny]-1).power;
										}
										temp.power = -1;
										arr.get(chk[nx][ny]-1).power = -1;
									}else {
										chk[temp.x][temp.y] = 0;
										chk[nx][ny] = i+1;
										temp.x = nx;
										temp.y = ny;
									}
								}else if(temp.direction==3) {
									if(arr.get(chk[nx][ny]-1).direction==2) {
										if(arr.get(chk[nx][ny]-1).power==-1) {
											energy += temp.power;
										}else {
											energy += temp.power + arr.get(chk[nx][ny]-1).power;
										}
										temp.power = -1;
										arr.get(chk[nx][ny]-1).power = -1;
									}else {
										chk[temp.x][temp.y] = 0;
										chk[nx][ny] = i+1;
										temp.x = nx;
										temp.y = ny;
									}
								}
							}
						}
					}else {
						chk[temp.x][temp.y] = 0;
						chk[nx][ny] = i+1;
						temp.x = nx;
						temp.y = ny;
					}
					chk[temp.x][temp.y]= 0;
					chk[nx][ny] = i+1;
					temp.x = nx;
					temp.y = ny;
				}else {
					temp.direction = -1;
				}
			}
			
			int idx = 1;
			for(int i=0; i<arr.size(); i++) {
				temp = arr.get(i);
				if(temp.power==-1) {
//					arr.remove(i);
					chk[temp.x][temp.y] = 0; 
				}else {
					chk[temp.x][temp.y] = idx;
					idx++;
				}
			}
			
			size = arr.size();
//			for(int i=0; i<size; i++) {
//				temp = arr.get(i);
//				System.out.println(chk[temp.x][temp.y] + " " + temp.x + " " + temp.y);
//			}System.out.println();
		}
	}

	public static class Atom {
		int x;
		int y;
		int direction;
		int power;
		int idx;
		public Atom(int x, int y, int direction, int power) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.power = power;
		}
	}

}
