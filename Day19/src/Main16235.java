import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main16235 {
	
	public static int n;	//map크기
	public static int m;	//나무 개수
	public static int k;	//나무 키우는 기간
	public static int[][] map;
	public static int[][] yang;
	public static Deque<Tree> trees;
	public static Deque<Tree> deads;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input16235.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		yang = new int[n][n];
		//양분 추가 맵
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				yang[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		deads = new ArrayDeque<Tree>();
		//나이 순 대로 출력하기 위한 Queue
		trees = new ArrayDeque<Tree>();
		int x,y,age;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			age = Integer.parseInt(st.nextToken());
			trees.offer(new Tree(x, y, age));
		}
		//현재 땅의 양분맵
		for(int i=0; i<n; i++) {
			Arrays.fill(map[i], 5);
		}
		
		
		grow();
		
		System.out.println(trees.size());
		trees.clear();
		deads.clear();
		
		br.close();
	}
	
	public static void grow() {
		int x,y,age;
		int qSize;
		Tree poll;
		for(int year=0; year<k; year++) {
			
			//봄
//			System.out.println("===================" + year + "년===============");
//			System.out.println("(봄)");
			qSize = trees.size();
			for(int i=0; i<qSize; i++) {
				poll = trees.poll();
				x = poll.x;
				y = poll.y;
				age = poll.age;
//				System.out.println("x: " + x + " y: " + y + " age: " + age);
				if(map[x][y] >= age) {
					map[x][y] -= age;	//양분먹고
					age++;		//나이 한살 먹기
					trees.offer(new Tree(x, y, age));
				}else {
					deads.offer(new Tree(x,y,age));
					continue;	//양분 안먹고 죽음
				}
			}
			
			//여름
//			System.out.println("(여름) - 죽은나무");
			int dyang = 0;
			while(!deads.isEmpty()) {
				poll = deads.poll();
				x = poll.x;
				y = poll.y;
				age = poll.age;
				dyang = age / 2;		//죽은 나무의 양분
				map[x][y] += dyang;		//양분 추가
			}
			
			//가을
//			System.out.println("(가을) - 배양");
			Deque<Tree> temp = new ArrayDeque<Tree>();
			int nx, ny;
			while(!trees.isEmpty()) {
				poll = trees.poll();
				x = poll.x;
				y = poll.y;
				age = poll.age;
				if(age%5==0) {		//나이가 5의 배수일때
					for(int k=0; k<8; k++) {
						nx = x + dir[k][0];
						ny = y + dir[k][1];
						if(nx>-1 && ny>-1 && nx<n && ny<n) {
							temp.offerFirst(new Tree(nx, ny, 1));
						}
					}
				}
				temp.offer(new Tree(x, y, age));
			}
			
			trees.addAll(temp);
			temp.clear();
			
			//겨울
//			System.out.println("(겨울) - 양분 추가");
//			printMap();
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					map[i][j] += yang[i][j];
				}
			}
//			printMap();
		}
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}
	
	public static class Tree {
		int x;
		int y;
		int age;
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}

}
