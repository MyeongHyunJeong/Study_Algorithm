import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main3425_2 {
	
	public static Queue<Instruction> ins = new LinkedList<Instruction>();

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3425.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String tempIns="";
		while(true) {
			tempIns = br.readLine();
			if(tempIns.equals("QUIT")) break;	//QUIT일 경우 시스템 끝
			if(tempIns.equals("END")) {		//명령어 입력이 끝날 경우
				int n = Integer.parseInt(br.readLine());
				for(int i=0; i<n; i++) {	//입력 하나하나씩 수행
					getResult(Integer.parseInt(br.readLine()));
				}
				System.out.println();
			}else {		//명령어 입력
				if(tempIns.equals("")) ins.clear();
				else {
					st = new StringTokenizer(tempIns);
					if(st.nextToken().equals("NUM")) {
						ins.add(new Instruction("NUM", Integer.parseInt(st.nextToken())));
					}else {
						ins.add(new Instruction(tempIns, 0));
					}
				}
			}
		}
		
		
	}
	
	public static void getResult(int inputNum) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(inputNum);
		boolean chkError = false;
		
		Iterator<Instruction> it;
		it = ins.iterator();
		while(it.hasNext()) {	//명령어 하나씩 수행
			Instruction nins;
			nins = it.next();
//			System.out.println(nins.ins + " " + nins.num);
			
			if(nins.ins.equals("NUM")) {	//숫자 추가
				stack.add(nins.num);
			}else if(nins.ins.equals("POP")) {	//젤 위의 숫자 제거
				stack.pop();
			}else if(nins.ins.equals("INV")) {	//젤 위의 숫자 부호 수정
				stack.add(-stack.pop());
			}else if(nins.ins.equals("DUP")) {	//젤 위의 숫자 한번더 추가
				stack.add(stack.peek());
			}else if(nins.ins.equals("SWP")) {	//숫자 위치 수정
				if(stack.size()<2) chkError = true;
				else {
					int tempA = stack.pop();
					int tempB = stack.pop();
					stack.add(tempA);
					stack.add(tempB);
				}
			}else if(nins.ins.equals("ADD")) {	//숫자 더하기
				if(stack.size()<2) chkError = true;
				else {
					int tempA = stack.pop();
					int tempB = stack.pop();
					int add = tempA + tempB;
					if(Math.abs(add)>1000000000) chkError = true;
					else stack.add(add);
				}
			}else if(nins.ins.equals("SUB")) {	//숫자 빼기
				if(stack.size()<2) chkError = true;
				else {
					int tempA = stack.pop();
					int tempB = stack.pop();
					int sub = tempB-tempA;
					if(Math.abs(sub)>1000000000) chkError = true;
					else stack.add(sub);
				}
			}else if(nins.ins.equals("MUL")) {	//젤 위의 숫자 한번더 추가
				if(stack.size()<2) chkError = true;
				else {
					int tempA = stack.pop();
					int tempB = stack.pop();
					int mul = tempA * tempB;
					if(Math.abs(mul)>1000000000) chkError = true;
					else stack.add(mul);
				}
			}else if(nins.ins.equals("DIV")) {	//숫자 곱하기
				if(stack.size()<2) chkError = true;
				else {
					int tempA = stack.pop();
					int tempB = stack.pop();
					if(tempA==0) chkError = true;
					else {
						int div = Math.abs(tempB) / Math.abs(tempA);
						if(tempA*tempB<0) div = -div;
						stack.add(div);
					}
				}
			}else if(nins.ins.equals("MOD")) {	//젤 위의 숫자 한번더 추가
				if(stack.size()<2) chkError = true;
				else {
					int tempA = stack.pop();
					int tempB = stack.pop();
					if(tempA==0) chkError = true;
					else {
						int mod = Math.abs(tempB) % Math.abs(tempA);
						if(tempB<0) mod = -mod;
						stack.add(mod);
					}
				}
			}
			
			if(chkError) {
				chkError = false;
				break;
			}
		}
		
		if(chkError || stack.size()!=1) {
			System.out.println("ERROR");
		}else {
			System.out.println(stack.pop());
		}
	}
	
	public static class Instruction {
		String ins;
		int num;
		public Instruction(String ins, int num) {
			this.ins = ins;
			this.num = num;
		}
	}

}
