import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution1218 {

	public static int n;
	public static String r;
	public static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1218.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			n = Integer.parseInt(br.readLine());
			r = br.readLine();
			Stack<Character> stack = new Stack<Character>();
			result = 0;
			
			char nr;
			char pop;
			for(int i=0; i<n; i++) {
				nr = r.charAt(i);
				if(nr=='(' || nr=='[' || nr=='{' || nr=='<') {
					stack.add(nr);
				}else {
					if(!stack.isEmpty()) {
						pop = stack.pop();
						if(pop=='(') {
							if(nr==')') {
								continue;
							}else {
								result = 0;
								break;
							}
						}else if(pop=='[') {
							if(nr==']') {
								continue;
							}else {
								result = 0;
								break;
							}
						}else if(pop=='{') {
							if(nr=='}') {
								continue;
							}else {
								result = 0;
								break;
							}
						}else {
							if(nr=='>') {
								continue;
							}else {
								result = 0;
								break;
							}
						}
					}else {
						break;
					}
				}
				result = 1;
			}
			
			System.out.println("#" + t + " " + result);
		}
	}

}
