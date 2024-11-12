
import java.io.*;
import java.util.*;

public class Main {

	static int N, count[];
	static List<Integer>[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		count = new int[N+1];
		for(int i=1 ; i <= N ; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0 ; i < N-1 ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
			
			count[a]++;
			count[b]++;
			
		}
		
		Deque<Integer> q = new ArrayDeque<>();
		boolean visit[] = new boolean[N+1];
		boolean early[] = new boolean[N+1];
		
		for(int i=1 ; i <= N ; i++) {
			if(count[i] == 1) {
				q.offer(i);
			}
		}
		
		int answer = 0;
		while(!q.isEmpty()) {
			int current = q.poll();
			
			if(visit[current]) continue;
			visit[current] = true;
			
			for(int next : list[current]) {
				count[next]--;
				if(!early[current] && !early[next]) {
					early[next] = true;
					answer++;
				}
				
				if(count[next] == 1) {
					q.offer(next);
				}
			}
			
		}//end while
		
		System.out.println(answer);
		
		
	}

}
