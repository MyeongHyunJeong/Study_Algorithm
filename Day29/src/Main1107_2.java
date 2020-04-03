import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1107_2 {
	
	public static int[] wantCH;
	public static int max;
	public static int min;
	public static int currentCH;
	public static String want;
	public static boolean[] err;
	public static int result;
	public static String minCH;
	public static String maxCH;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1107.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		want = br.readLine();
		wantCH = new int[want.length()];
		for(int i=0; i<want.length(); i++) {
			wantCH[i] = Integer.parseInt(""+want.charAt(i));
		}
		currentCH = 100;
		
		int errSize = Integer.parseInt(br.readLine());
		err = new boolean[10];
		String temp = br.readLine().replace(" ", "");
		for(int i=0; i<errSize; i++) {
			err[Integer.parseInt(""+temp.charAt(i))] = true;
		}
		
		max = -1;
		min = -1;
		for(int i=0; i<10; i++) {
			if(!err[i]) {
				min = i;
				break;
			}
		}
		for(int i=9; i>=0; i--) {
			if(!err[i]) {
				max = i;
				break;
			}
		}
//		System.out.println(min + " " + max);
//		System.out.println(Arrays.toString(err));
		
		result = Math.abs(Integer.parseInt(want)-100);
		if(errSize==10) {
			System.out.println(result);
		}else {
			minCH = "";
			maxCH = "";
			click();
			System.out.println(result);
		}
		
		br.close();
	}
	
	public static void click() {
		int i;
		for(i=0; i<wantCH.length; i++) {
			if(!err[wantCH[i]]) {
				minCH += wantCH[i];
				maxCH += wantCH[i];
			}else {
				for(int j=wantCH[i]; j>=0; j--) {
					if(!err[j]) {
						minCH+=j;
						break;
					}
				}
				for(int j=wantCH[i]; j<10; j++) {
					if(!err[j]) {
						maxCH+=j;
						break;
					}
				}
				break;
			}
		}
//		System.out.println(minCH + " " + maxCH);
		if(i!=wantCH.length) {
			if(maxCH.length()!=i+1) {
				maxCH += minCH.charAt(i);
			}
			if(minCH.length()!=i+1) {
				minCH += maxCH.charAt(i);
			}
			for(i=i+1;i<wantCH.length; i++) {
				minCH += max;
				maxCH += min;
			}
		}
//		System.out.println(minCH + " " + maxCH);
		
		int tempA = Integer.parseInt(minCH);
		int tempB = Integer.parseInt(maxCH);
		int tempC = Integer.parseInt(want);
		int a = minCH.length() + Math.abs(tempC-tempA);
		int b = maxCH.length() + Math.abs(tempB-tempC);
//		System.out.println(a + " " + b);
		if(result>a) result = a;
		if(result>b) result = b;
	}

}
