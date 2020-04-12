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
			if(tempIns.equals("QUIT")) break;	//QUIT�� ��� �ý��� ��
			if(tempIns.equals("END")) {		//��ɾ� �Է��� ���� ���
				int n = Integer.parseInt(br.readLine());
				for(int i=0; i<n; i++) {	//�Է� �ϳ��ϳ��� ����
					getResult(Long.parseLong(br.readLine()));
				}
				System.out.println();
			}else {		//��ɾ� �Է�
				if(tempIns.equals("")) ins.clear();
				else {
					st = new StringTokenizer(tempIns);
					if(st.nextToken().equals("NUM")) {
						ins.add(new Instruction("NUM", Long.parseLong(st.nextToken())));
					}else {
						ins.add(new Instruction(tempIns));
					}
				}
			}
		}
		
		
	}
	
	public static void getResult(Long inputNum) {
		Stack<Long> stack = new Stack<Long>();
		stack.add(inputNum);
		boolean chkError = false;
		
		Iterator<Instruction> it;
		it = ins.iterator();
		while(it.hasNext()) {	//��ɾ� �ϳ��� ����
			Instruction nins;
			nins = it.next();
//			System.out.println(nins.ins + " " + nins.num);
			
			if(nins.ins.equals("NUM")) {	//���� �߰�
				stack.add(nins.num);
			}else if(nins.ins.equals("POP")) {	//�� ���� ���� ����
				stack.pop();
			}else if(nins.ins.equals("INV")) {	//�� ���� ���� ��ȣ ����
				stack.add(-stack.pop());
			}else if(nins.ins.equals("DUP")) {	//�� ���� ���� �ѹ��� �߰�
				stack.add(stack.peek());
			}else if(nins.ins.equals("SWP")) {	//���� ��ġ ����
				if(stack.size()<2) chkError = true;
				else {
					Long tempA = stack.pop();
					Long tempB = stack.pop();
					stack.add(tempA);
					stack.add(tempB);
				}
			}else if(nins.ins.equals("ADD")) {	//���� ���ϱ�
				if(stack.size()<2) chkError = true;
				else {
					Long tempA = stack.pop();
					Long tempB = stack.pop();
					Long add = tempA + tempB;
					if(Math.abs(add)>1000000000) chkError = true;
					else stack.add(add);
				}
			}else if(nins.ins.equals("SUB")) {	//���� ����
				if(stack.size()<2) chkError = true;
				else {
					Long tempA = stack.pop();
					Long tempB = stack.pop();
					Long sub = tempB-tempA;
					if(Math.abs(sub)>1000000000) chkError = true;
					else stack.add(sub);
				}
			}else if(nins.ins.equals("MUL")) {	//�� ���� ���� �ѹ��� �߰�
				if(stack.size()<2) chkError = true;
				else {
					Long tempA = stack.pop();
					Long tempB = stack.pop();
					Long mul = tempA * tempB;
					if(Math.abs(mul)>1000000000) chkError = true;
					else stack.add(mul);
				}
			}else if(nins.ins.equals("DIV")) {	//���� ���ϱ�
				if(stack.size()<2) chkError = true;
				else {
					Long tempA = stack.pop();
					Long tempB = stack.pop();
					if(tempA==0) chkError = true;
					else {
						Long div = Math.abs(tempB) / Math.abs(tempA);
						if(tempA*tempB<0) div = -div;
						stack.add(div);
					}
				}
			}else if(nins.ins.equals("MOD")) {	//�� ���� ���� �ѹ��� �߰�
				if(stack.size()<2) chkError = true;
				else {
					Long tempA = stack.pop();
					Long tempB = stack.pop();
					if(tempA==0) chkError = true;
					else {
						Long mod = Math.abs(tempB) % Math.abs(tempA);
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
		Long num;
		public Instruction(String ins) {
			this.ins = ins;
		}
		public Instruction(String ins, Long num) {
			this.ins = ins;
			this.num = num;
		}
	}

}
