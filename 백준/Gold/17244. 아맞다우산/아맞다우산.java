
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, startR, startC, need = 0;
    static char[][] map;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'S') {
                    startR = i;
                    startC = j;
                } else if (map[i][j] == 'X') {
                    need++;
                    map[i][j] = (char) (need + '0'); 
                }
            }
        }

        System.out.println(BFS());
    }

    public static int BFS() {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startR, startC, 0});
        boolean[][][] visit = new boolean[N][M][(1 << need)];
        visit[startR][startC][0] = true;

        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                int[] cur = q.poll();
                int cr = cur[0], cc = cur[1], item = cur[2];

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if (!isIn(nr, nc) || map[nr][nc] == '#' || visit[nr][nc][item]) continue;

                    char location = map[nr][nc];
                    if (location == '.' || location == 'S') {
                        visit[nr][nc][item] = true;
                        q.offer(new int[]{nr, nc, item});
                    } else if (location >= '1' && location <= '5') {
                        int newItem = item | (1 << (location - '1'));
                        if (visit[nr][nc][newItem]) continue;                      
                            visit[nr][nc][newItem] = true;
                            q.offer(new int[]{nr, nc, newItem});
                        
                    } else if (location == 'E') {
                        if (item == (1 << need) - 1) {
                            return time + 1; 
                        }
                    }
                }
            }
            time++;
        }
        return -1;
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
