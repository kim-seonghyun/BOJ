import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	static BufferedReader br;
	static StringTokenizer st; 
	static int M;
	static int N;
	static int K;
	
	static int[][] board;
	
	static int[] xDir = {0,0,1,-1};
	static int[] yDir = {1,-1,0,0};
	
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		inputValue();
		
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(board[i][j] == 0) {
					result.add(bfs(i,j));
				}
				
			}
		}
		result.sort(null);
		System.out.println(result.size());
		for(int i=0; i<result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
		
		
	}
	
	private static int bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {x,y});
		int count =0;
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int cx = current[0];
			int cy = current[1];
			if(visited[cx][cy]) {
				continue;
			}
			board[cx][cy] = 1;
			count++;
			visited[cx][cy] = true;
			
			for(int dir=0; dir<4; dir++) {
				int nx = cx + xDir[dir];
				int ny = cy + yDir[dir];
				
				if(nx >=0 && nx < M && ny >=0 && ny< N && board[nx][ny] == 0) {
					queue.add(new int[] {nx,ny});
				}
			}
		}
		return count;
	}

	private static void inputValue() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		board = new int[M + 1][N + 1];
		visited = new boolean[M + 1][N + 1];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			for(int row = r1; row < r2; row++) {
				for(int col = c1; col < c2; col++) {
					board[col][row] = 1;
				}
			}
		}
		
		
	}

	
	
	
}
