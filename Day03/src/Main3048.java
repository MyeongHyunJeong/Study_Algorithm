import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main3048 {
	
	public static int n1, n2;
	public static String[] m1, m2;
	public static String tempN1, tempN2;
	public static int time;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3048.txt"));
		Scanner s = new Scanner(System.in);
		n1 = s.nextInt();
		n2 = s.nextInt();
		m1 = new String[n1+n2];
		m2 = new String[n1+n2];
		String[] currentM2 = new String[n1+n2];
		tempN1 = s.next();
		tempN2 = s.next();
		time = s.nextInt();
		char[] temp1 = new char[n1];
		char[] temp2 = new char[n2];
		temp1 = tempN1.toCharArray();
		temp2 = tempN2.toCharArray();
		for(int i=0; i<n1; i++) {
			m1[i] = ""+temp1[n1-1-i];
		}
		for(int i=n1; i<m2.length; i++) {
			m2[i] = ""+temp2[i-n1];
			currentM2[i] = ""+temp2[i-n1];
		}
//		System.out.println(Arrays.toString(m1));
//		System.out.println(Arrays.toString(m2));
		for(int i=0; i<time; i++) {
			for(int j=m1.length-1; j>=0; j--) {
				if(m1[j]!=null) {
//					System.out.println("hihi");
					if(j+1<m2.length && currentM2[j+1]!=null) {
						m1[j+1] = m1[j];
						m1[j] = null;
						m2[j] = m2[j+1];
						m2[j+1] = null;
					}
				}

			}
//			System.out.println("------------------------------");
//			System.out.println(Arrays.toString(m1));
//			System.out.println(Arrays.toString(m2));
//			System.out.println("------------------------------");
			for(int j=0; j<m2.length; j++) {
				currentM2[j] = m2[j];
			}
		}
		String result = "";
		for(int i=0; i<m1.length; i++) {
			if(m1[i] != null) {
				result += m1[i];
			}else if(m2[i] != null) {
				result += m2[i];
			}
		}
		System.out.println(result);
		
	}

}
