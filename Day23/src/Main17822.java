import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17822 {
	
	public static int n;
	public static int m;
	public static int t;
	public static int[][] map;
	public static int[][] temp;
	public static Queue<Instruction> ins;
	public static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input17822.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0; i<n; i++) {	//�������� �Է�
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ins = new LinkedList<Instruction>();
		int cn,d,turn;
		for(int i=0; i<t; i++) {	//��ɾ� �Է�
			st = new StringTokenizer(br.readLine());
			cn = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			turn = Integer.parseInt(st.nextToken());
			ins.add(new Instruction(cn-1, d, turn));
		}
		
		temp = new int[n][m];
		turnCircles();
		System.out.println(result);
		
		br.close();

	}
	
	public static void turnCircles() {
		int cn, d, turn;
		Instruction poll;
		
		boolean flag;
		int count;
		int calSum;
		double mean;
		
		while(!ins.isEmpty()) { 
			poll = ins.poll();
			cn = poll.circleNum;
			d = poll.direction;
			turn = poll.turn;
			
			copyMap();
//			System.out.println("==============" + cn + " " + d + " " + turn + "==============");
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<m; j++) {
//					System.out.print(map[i][j] + "\t");
//				}System.out.println();
//			}System.out.println();
			
			for(int j=0; j<n; j++) {
				if((j+1)%(cn+1)==0) {
					if(d==0) {	//�ð����
						for(int i=0; i<m; i++) {	//�ð�������� ȸ��
							map[j][(i+turn)%m] = temp[j][i];
						}
					}else {		//�ݽð����
						for(int i=0; i<m; i++) {	//�ݽð�������� ȸ��
							map[j][i] = temp[j][(i+turn)%m];
						}
					}	//ȸ�� ��
				}
			}
			
			//�����Ѱ� ã��
			copyMap();	//temp�� �ش� ���ǹ�ȣ�� ���� �ٽ� ���� -> ���ڰ� �������� ��� �����ϱ� ����(������)
			flag = false;
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<m; j++) {
//					System.out.print(map[i][j] + "\t");
//				}System.out.println();
//			}System.out.println();
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<m; j++) {
//					System.out.print(temp[i][j] + "\t");
//				}System.out.println();
//			}System.out.println();
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j]!=0) {	//���ڰ� ������ ���� �ʴ� ���
						if(j==0) {
							if(temp[i][j]==temp[i][1]) {
								flag = true;
								map[i][j] = 0;
								map[i][1] = 0;
							}
							if(temp[i][j]==temp[i][m-1]) {
								flag = true;
								map[i][j] = 0;
								map[i][m-1] = 0;
							}
						}else if(j==m-1){
							if(temp[i][j]==temp[i][m-2]) {
								flag = true;
								map[i][j] = 0;
								map[i][m-2] = 0;
							}
							if(temp[i][j]==temp[i][0]) {
								flag = true;
								map[i][j] = 0;
								map[i][0] = 0;
							}
						}else {
							if(temp[i][j]==temp[i][j-1]) {
								flag = true;
								map[i][j] = 0;
								map[i][j-1] = 0;
							}
							if(temp[i][j]==temp[i][j+1]) {
								flag = true;
								map[i][j] = 0;
								map[i][j+1] = 0;
							}
						}
						
						if(i>0) {
							if(temp[i-1][j]==temp[i][j]) {
								flag = true;
								map[i-1][j] = 0;
								map[i][j] = 0;
							}
						}
						
					}
				}
			}
			
			if(!flag) {
				count = 0;
				calSum = 0;
				mean = 0;
				for(int i=0; i<n; i++) {
					for(int j=0; j<m; j++) {
						if(map[i][j]!=0) {
							count++;
							calSum += map[i][j];
						}
					}
				}
				if(count>0) {
					mean = (double)calSum / count;
//				System.out.println(mean + " " + calSum + " " + count);
					
					for(int i=0; i<n; i++) {
						for(int j=0; j<m; j++) {
							if(map[i][j]!=0) {
								if(map[i][j]>mean) {
									map[i][j] -= 1;
								}else if(map[i][j]<mean){
									map[i][j] += 1;
								}
							}
						}
					}
					
					
				}
				
			}
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<m; j++) {
//					System.out.print(map[i][j] + "\t");
//				}System.out.println();
//			}System.out.println();
			
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]!=0) {
					result += map[i][j];
				}
			}
		}
	}
	
	public static void copyMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}
	
	public static class Instruction {
		int circleNum;
		int direction;
		int turn;
		public Instruction(int circleNum, int direction, int turn) {
			this.circleNum = circleNum;
			this.direction = direction;
			this.turn = turn;
		}
	}

}
