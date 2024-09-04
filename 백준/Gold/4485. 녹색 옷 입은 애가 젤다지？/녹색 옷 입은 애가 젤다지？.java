import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class MyNode implements Comparable<MyNode> {
    int x;
    int y;
    int distance;

    public MyNode(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public int compareTo(MyNode o) {
        return Integer.compare(this.distance, o.distance);
    }
}

public class Main {
    static int tc = 0;
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[][] board;
    static boolean[][] visited;
    static int[][] minDistance;

    static int[] xDir = {1, -1, 0, 0};
    static int[] yDir = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        while (inputValue()) {
            tc++;
            int result = dijkstra();
            System.out.printf("Problem %d: %d\n", tc, result);

        }




    }

    private static int dijkstra() {
        PriorityQueue<MyNode> pq = new PriorityQueue<>();
        pq.offer(new MyNode(0, 0, 0));
        minDistance[0][0] = board[0][0];
        while (!pq.isEmpty()) {
            MyNode node = pq.poll();
            int x = node.x;
            int y = node.y;

            if (visited[x][y]) {
                continue;
            }

            if(x == n-1 && y== n-1){
                return minDistance[n-1][n-1];
            }
            visited[x][y] = true;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + xDir[dir];
                int ny = y + yDir[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (!visited[nx][ny] && minDistance[nx][ny] > minDistance[x][y] + board[nx][ny]) {
                    minDistance[nx][ny] =  minDistance[x][y] + board[nx][ny];
                    pq.offer(new MyNode(nx, ny, minDistance[nx][ny] ));
                }
            }
        }
        return -1;
    }

    private static boolean inputValue() throws IOException {
        n = Integer.parseInt(br.readLine());
        minDistance = new int[n][n];


        if (n == 0) {
            return false;
        }
        board = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                minDistance[i][j] = Integer.MAX_VALUE;
            }
        }
        return true;
    }
}
/* 입력
3
5 5 4
3 9 1
3 2 7
 */