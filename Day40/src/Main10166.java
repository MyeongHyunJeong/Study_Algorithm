import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main10166 {

	public static int d1;
	public static int d2;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input10166.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		d1 = Integer.parseInt(st.nextToken());
		d2 = Integer.parseInt(st.nextToken());
		
		float cal;
		ArrayList<Float> arr = new ArrayList<Float>();
		boolean flag = false;
		for(int i=d1; i<=d2; i++) {
			for(int j=1; j<i; j++) {
				flag = false;
				cal = (float)360 / i * j;
				for(Float f : arr) {
					if(f==cal) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					arr.add(cal);
				}
			}
		}
		
		System.out.println(arr.size()+1);
		
		
		br.close();
	}

}
