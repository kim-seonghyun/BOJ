import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.print.DocFlavor.STRING;


public class Main {
	static int N;
	static int M;
	static int V;
	static boolean[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()) + 1;
		M=  Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		arr = new boolean[N][N];

		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x][y] = true;
			
			arr[y][x] = true;
		}
		boolean[] visitedDFS = new boolean[N];
		dfs(V, visitedDFS);
		System.out.println();
		boolean[] visitedBFS = new boolean[N];
		bfs(V, visitedBFS);
	}

	private static void bfs(int V, boolean[] visited) {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(V);
		
		while(!queue.isEmpty()) {
			int poped = queue.poll();
			if(visited[poped]) {
				continue;
			}
			visited[poped] = true;
			System.out.print(poped + " ");
			for(int i =1; i<N; i++) {
				if(arr[poped][i] && !visited[i]) {
					queue.add(i);
				}
			}
		}
	}

	private static void dfs(int start, boolean[] visited) {
		// TODO Auto-generated method stub
		visited[start] = true;
		System.out.print(start + " ");
		for(int i=1; i< N; i++) {
			
			if(arr[start][i] && !visited[i]) {
				dfs(i, visited);
			}
		}
	}
	
	
}
