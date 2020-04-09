import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1107_4 {
	
	public static int wantCH;
	public static int errSize;
	public static boolean[] err;
	public static int click;
	public static int n;
	public static int r;
	public static int[] temp;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1107.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		wantCH = Integer.parseInt(br.readLine());
		errSize = Integer.parseInt(br.readLine());
		
		click = Math.abs(wantCH-100);
		if(errSize==10) {	//모든 버튼이 Error일때
			System.out.println(click);
		}else if(errSize==0){	//모든 버튼이 고장나지 않았을때
			if(click>(""+wantCH).length()) click = (""+wantCH).length();
			System.out.println(click);
		}else {	//고장난 버튼이 존재할때
			err = new boolean[10];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<errSize; i++) {
				err[Integer.parseInt(st.nextToken())] = true;
			}
			int size = (""+wantCH).length();
			n = 10-errSize;		//누를수 있는 전체 버튼의 개수
			if(size>1) {	//원하는 채널의 자리수가 1개 이상일 때(ex> 원하는 채널:123 일때 클릭하는 채널은 2자리~4자리)
				for(int i=size-1; i<=size+1; i++) {
					r = i;	//클릭하는 숫자의 개수
					temp = new int[r];
					getResult(0);
				}
			}else {	//원하는 채널의 자리수가 1개일 때(ex> 원하는 채널: 1 일때 클릭하는 채널은 1자리~2자리)
				for(int i=size; i<=size+1; i++) {
					r = i;	//클릭하는 숫자의 개수
					temp = new int[r];
					getResult(0);
				}
			}
			
			System.out.println(click);
		}
		
		br.close();
	}
	
	public static void getResult(int idx) {
		if(idx==r) {
			String ch = "";
			for(int i=0; i<r; i++) {
				ch += temp[i];
			}
			int chClick = ch.length() + Math.abs(wantCH-Integer.parseInt(ch));
			if(chClick<click) click = chClick;
			
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(!err[i]) {
				temp[idx] = i;
				getResult(idx+1);
			}
		}
	}

}
