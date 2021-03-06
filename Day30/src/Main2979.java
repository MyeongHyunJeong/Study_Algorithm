import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2979 {
	
	public static int a;
	public static int b;
	public static int c;
	public static int[] pay;
	public static Car[] cars;
	public static int result = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2979.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		cars = new Car[3];
		pay = new int[101];
		int arrive=0, leave=0;
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			arrive = Integer.parseInt(st.nextToken());
			leave = Integer.parseInt(st.nextToken());
			for(int j=arrive+1; j<=leave; j++) {
				if(pay[j]==0) {
					pay[j] = a;
				}else if(pay[j]==a) {
					pay[j] = b;
				}else if(pay[j]==b) {
					pay[j] = c;
				}
			}
		}
		
		for(int i=0; i<101; i++) {
			if(pay[i]==a) result += pay[i];
			else if(pay[i]==b) result += pay[i]*2;
			else if(pay[i]==c) result += pay[i]*3;
		}
		
		System.out.println(result);
		
		br.close();
	}
	
	public static class Car {
		int arrive;
		int leave;
		public Car(int arrive, int leave) {
			this.arrive = arrive;
			this.leave = leave;
		}
	}

}
