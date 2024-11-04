import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R;
	static int map[][];
	static boolean visit[][];
	static int[] dr = {0,1,0,-1};//우하좌상
	static int[] dc = {1,0,-1,0};//우하좌상
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		//visit = new boolean[N][M];
		
		for(int i=0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0 ; i < R ; i++) {
			rotate();
		}
		//rotate();
		for(int i=0 ; i < N ; i++) {
			for(int j=0 ; j < M ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}

	public static void rotate() {
		visit = new boolean[N][M];
		
		int r=0, c=0, d=0;
		int nr,nc, cr, cc;
		
		while(r < N/2 && c < M/2) {
			//초기위치 r c +1 씩
			//초기 위치로 돌아올 때 까지
			d=0;

			cr = r + dr[d];
			cc = c + dc[d];
			int temp = map[cr][cc];
			//초기 위치로 돌아올 때까지
			while(!(cr == r && cc == c)) {
				visit[cr][cc] = true;
	
				//이동
				nr = cr + dr[d];
				nc = cc + dc[d];
				
				
				//방향전환
				if(!isIn(nr, nc) || visit[nr][nc]) {
					d = (d+1) % 4;
					nr = cr + dr[d];
					nc = cc + dc[d];					
				}
				
				map[cr][cc] = map[nr][nc];
				cr += dr[d];
				cc += dc[d];
			}
			map[r][c] = temp;
			
			r++;
			c++;//초기위치 이동
		}
		
		
			
	}
	public static boolean isIn(int r, int c) {
		if(0 <= r && r < N && 0 <= c && c < M) return true;
		return false;
	}
}
