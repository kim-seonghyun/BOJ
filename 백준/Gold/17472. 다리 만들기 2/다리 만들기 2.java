import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    int islandNumber;

    public Point(int x, int y, int islandNumber) {
        this.x = x;
        this.y = y;
        this.islandNumber = islandNumber;
    }

    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + ", islandNumber=" + islandNumber + "]";
    }

}

class Node implements Comparable<Node> {
    int from;
    int to;
    int cost;

    public Node(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Node [from=" + from + ", to=" + to + ", cost=" + cost + "]\n";
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

/**
 * @author 김성현
 */
public class Main {
    static int N;
    static int M;
    static BufferedReader br;

    static StringTokenizer st;
    static boolean[][] visited;
    static int[][] board;
    static int[] xDir = { 1, -1, 0, 0 };
    static int[] yDir = { 0, 0, 1, -1 };
    static int islandCount;

    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static ArrayList<Point> points = new ArrayList<>();

    static int[] parents;

    public static void main(String[] args) throws IOException {
        inputValue();

        numbering();

        getLandPosition();
        getEdge();
        makeSet();
        int result = getResult();
        System.out.println(result);
    }

    private static void makeSet() {
        parents = new int[islandCount + 1];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    private static int find(int value) {
        if (value == parents[value])
            return value;
        return parents[value] = find(parents[value]);
    }

    private static boolean union(int v1, int v2) {
        int aroot = find(v1);
        int broot = find(v2);

        if (aroot == broot)
            return false;

        parents[aroot] = broot;
        return true;
    }

    private static int getResult() {
        int res = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (union(node.from, node.to)) {
                count++;
                res += node.cost;
                if (count == islandCount - 1) {
                    break;
                }
            }
        }
        if (count == islandCount - 1) {
            return res;
        } else {
            return -1;
        }
    }

    private static void getEdge() {
        for (Point point : points) {
            int x = point.x;
            int y = point.y;

            for (int dir = 0; dir < 4; dir++) {
                int distance = 0;
                while (true) {
                    int nx = x + xDir[dir] * (distance + 1);
                    int ny = y + yDir[dir] * (distance + 1);

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == point.islandNumber) {
                        break;
                    }

                    if (board[nx][ny] == 0) {
                        distance++;
                        continue;
                    }
                    if (board[nx][ny] != point.islandNumber) {
                        if (distance >= 2) {
                            pq.offer(new Node(board[x][y], board[nx][ny], distance));
                        }
                        break;
                    }
                }

            }
        }
    }

    private static void getLandPosition() {
        points.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    points.add(new Point(i, j, board[i][j]));
                }
            }
        }
    }

    private static void numbering() {
        visited = new boolean[N][M];
        int number = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0 && !visited[i][j]) {
                    dfs(i, j, number++);
                }
            }
        }
        islandCount = number - 1;
    }

    private static void dfs(int x, int y, int number) {
        visited[x][y] = true;
        board[x][y] = number;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + xDir[dir];
            int ny = y + yDir[dir];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || board[nx][ny] == 0) {
                continue;
            }

            dfs(nx, ny, number);
        }

    }

    private static void inputValue() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
