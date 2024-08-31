import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


class Edge implements Comparable<Edge> {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}

/**
 * @author 김성현
 * 1. 문제설명
 * 모든 노드를 연결하는 최단 거리 만들기. 근데 노드는 다른 문자의 노드만 연결 할 수 있음.
 * <p>
 * 2. input
 * N M
 * N개의 대학교 종류 표시 ( M, W )
 * M개 간선 표시 u v d
 * <p>
 * 3. output
 * 경로 길이
 * <p>
 * 4. 해결 방법
 * 크루스칼 알고리즘을 사용하여 최소 간선 순으로 union-find를 진행하면서 수행.
 * 조건은 다음 노드가 현재 노드와 다른 종류일때.
 */
public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int m;
    static ArrayList<Edge> board;
    static String[] gender;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        inputValue();
        make();
        long result = connect();
        System.out.println(result);
    }

    private static void make() {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    private static int find(int value) {
        if (parents[value] == value) {
            return value;
        }
        return parents[value] = find(parents[value]);
    }

    private static boolean union(int v1, int v2) {
        int aroot = find(v1);
        int broot = find(v2);

        if (aroot == broot) {
            return false;
        }
        parents[aroot] = broot;
        return true;
    }


    private static long connect() {
        Collections.sort(board);
        long cost = 0;
        int cnt = 0;
        for (Edge edge : board) {
            if (!gender[edge.from].equals(gender[edge.to]) &&
                    union(edge.from, edge.to)) {
                cost += edge.cost;
                if (++cnt == n - 1) {
                    return cost;
                }
            }
        }
        return -1;
    }


    private static void inputValue() throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        gender = new String[n + 1];
        for (int i = 1; i <= n; i++) {
            gender[i] = st.nextToken();
        }
        board = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            board.add(new Edge(from, to, cost));
        }
    }
}
