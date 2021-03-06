import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1107 {

	public static String wantCH;
	public static int m;
	public static String wrong;
	public static int[] upper;
	public static int[] lower;
	public static int[] canClick;
	public static int currentCH;
	public static int onlyClickUD;
	public static int clickCount;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1107.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		wantCH = br.readLine();
		m = Integer.parseInt(br.readLine());
		wrong = br.readLine().replace(" ", "");
		currentCH = 100;
//		System.out.println("wantCH : " + wantCH);
//		System.out.println("wrong num : " + wrong);
//		System.out.println("currentCH : " + currentCH);
		
		//+/-버튼만 누를 경우
		if(Integer.parseInt(wantCH)==currentCH) {
			clickCount = 0;
			System.out.println(clickCount);
			return;
		}else if(Integer.parseInt(wantCH)>currentCH) {
			clickCount = Integer.parseInt(wantCH) - currentCH;
		}else {
			clickCount = currentCH - Integer.parseInt(wantCH);
		}
		
		String click = "";
		boolean chk = false;
		int num = 0;
		int select;
		int temp;
		if(m!=10) {
			int i = 0;
			for(i=0; i<wantCH.length(); i++) {
				num = Integer.parseInt(wantCH.charAt(i)+"");
				select = Integer.MAX_VALUE;
				if(wrong.contains(""+num)) {
					for(int j=0; j<10; j++) {
						if(!wrong.contains(""+j)) {
							if(select==Integer.MAX_VALUE) {
								select = j;
							}else {
								temp = Math.abs(select-num);
								if(temp>select+10-num) temp = select+10-num;
								if(temp>Math.abs(j-num) || temp>j+10-num) {
									select = j;
								}
							}
						}
					}
//					System.out.println("s : " +select);
					if(select>num) chk = true;
					else chk = false;
					click += select;
					i++;
					break;
				}else {
					click += num;
				}
			}
			if(chk) {
				select = 0;
				for(int j=0; j<10; j++) {
					if(!wrong.contains(""+j)) {
						select = j;
						break;
					}
				}
				for(;i<wantCH.length(); i++) {
					click += select;
				}
			}else {
				select = 0;
				for(int j=9; j>=0; j--) {
					if(!wrong.contains(""+j)) {
						select = j;
						break;
					}
				}
				for(;i<wantCH.length(); i++) {
					click += select;
				}
			}
//			System.out.println("click : " +click);
			int t = click.length() + Math.abs(Integer.parseInt(wantCH)-Integer.parseInt(click));
//			System.out.println(t);
			if(t<clickCount) clickCount = t;
			
		}else {
			System.out.println(clickCount);
			return;
		}
		
		System.out.println(clickCount);
		
		br.close();
	}
	

}
