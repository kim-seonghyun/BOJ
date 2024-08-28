import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김성현
 *
 *         1. 문제설명 R * C의 배열에서 S에서 D까지 이동 *(물이 차있음), X(돌), . ( 비어있는 곳)
 *
 *
 *         2. input R C R * C 지도
 *
 *
 *         3. output S -> D로 가는 빠른 시간, 없으면 KAKTUS 출력
 *
 *
 *         4. 해결 방법 인접한 지역에 물을 채운다. 고슴도치를 이동시킨다. (bfs로)
 *
 *         고슴도치가 D에 도착하면 시간 return 고슴도치가 갈곳이 없으면 KAKTUS 출력
 * 
 *         use 2 queue (water, gosumdochi) a. fill water b. go gosumdochi ! if
 *         gosumdochi meets beaver, then return distance else shout "KAKTUS"
 * 
 * 
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int R;
	static int C;
	static boolean[][] visited;

	static int[] xDir = { 1, -1, 0, 0 };
	static int[] yDir = { 0, 0, -1, 1 };

	static char[][] board;

	private static boolean checkRange(int x, int y) {
		if (x >= 0 && x < R && y >= 0 && y < C) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new FileReader("input.txt"));

		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		Queue<int[]> gosumdochiQueue = new ArrayDeque<>();
		Queue<int[]> waterQueue = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = tmp.charAt(j);
				if (board[i][j] == '*') {
					int[] tmpWaterPosition = { i, j };
					waterQueue.add(tmpWaterPosition);
				} else if (board[i][j] == 'S') {
					int[] gosumdochiPosition = { i, j };
					
					gosumdochiQueue.add(gosumdochiPosition);
				}

			}
		}
		visited = new boolean[R][C];

		for(int time = 1; !gosumdochiQueue.isEmpty() || !waterQueue.isEmpty(); time++) {
			
			int wS = waterQueue.size();
			
			for(int i=0; i<wS; i++) {
				int[] position = waterQueue.poll();
				int x = position[0];
				int y = position[1];
				visited[x][y] = true;
				
				

				for (int dir = 0; dir < 4; dir++) {
					int nx = x + xDir[dir];
					int ny = y + yDir[dir];

					if (checkRange(nx, ny) && board[nx][ny] == '.') {
						board[nx][ny] = '*';
						int[] nextWaterPosition = { nx, ny };
						waterQueue.add(nextWaterPosition);
					}
				}
			}
			
			
			
		
			
			int gS = gosumdochiQueue.size();
			for(int i = 0; i< gS; i++) {
				int[] gosumdochiPosition = gosumdochiQueue.poll();
				int gx = gosumdochiPosition[0];
				int gy = gosumdochiPosition[1];
				if (board[gx][gy] != 'S') {
					continue;
				}
				
				if(visited[gx][gy]) {
					continue;
				}
				
				visited[gx][gy] = true;
				

				for (int dir = 0; dir < 4; dir++) {
					int nx = gx + xDir[dir];
					int ny = gy + yDir[dir];

					if (checkRange(nx, ny) && !visited[nx][ny] ) {
						if(board[nx][ny] == 'D') {
							System.out.println(time);
							return;
						}
						
						if(board[nx][ny] == '.') {
							board[gx][gy] = '.';
							board[nx][ny] = 'S';
							int[] nextgosumdochiPosition = { nx, ny };
							gosumdochiQueue.add(nextgosumdochiPosition);
						}
						
					}
				}
				
		
				if(gosumdochiQueue.size() == 0) {
					System.out.println("KAKTUS");
					
				}
			}
			
			
			

		}

	}
}