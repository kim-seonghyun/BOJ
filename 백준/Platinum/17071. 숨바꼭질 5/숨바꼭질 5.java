
import java.io.*;
import java.util.*;

public class Main{

    static int N, K, MAX_INT = 500000;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(findSister());
    }

    public static int findSister() {
        if (N == K) return 0;

        visited = new boolean[2][MAX_INT + 1]; // 짝수, 홀수 시간대 방문 관리
        Deque<Integer> q = new ArrayDeque<>();
        q.add(N);
        visited[0][N] = true; // 처음 시간대는 0 (짝수 시간대)

        int time = 0;

        while (!q.isEmpty()) {
            K += time;  // 동생은 time 시간 후 K + time 위치로 이동
            if (K > MAX_INT) return -1;  // 동생이 범위를 벗어나면 종료
            if (visited[time % 2][K]) return time; // 동생과 같은 위치에 도달했을 때

            int size = q.size();
            for (int i = 0; i < size; i++) {
                int subin = q.poll();

                // 수빈이가 이동할 수 있는 세 가지 경우
                int[] nextPositions = {subin - 1, subin + 1, subin * 2};

                for (int next : nextPositions) {
                    if (next >= 0 && next <= MAX_INT && !visited[(time + 1) % 2][next]) {
                        visited[(time + 1) % 2][next] = true; // 다음 시간대의 방문 여부 체크
                        q.add(next);
                    }
                }
            }

            time++;
        }

        return -1;
    }
}
