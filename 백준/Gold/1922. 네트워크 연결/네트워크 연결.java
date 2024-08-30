import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


class Edge implements Comparable<Edge>{
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}

/**
 * @author 김성현
 * 1. 문제 설명
 *
 * N개의 컴퓨터를 최소 비용으로 연결하기.
 *
 * 2. input
 * N ( 컴퓨터 수)
 * M ( 선의 수 )
 * M줄의 컴퓨터 연결 비용 ( a b c)
 */
public class Main {


    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int m;
    static ArrayList<Edge> board;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        inputValue();
        make();
        long minimumCost = getMinimumCost();
        System.out.println(minimumCost);
    }

    private static long getMinimumCost() {
        Collections.sort(board);
        int cnt =0;
        long cost = 0;
        for(Edge edge: board){
            if(union(edge.from, edge.to)){
                cost += edge.weight;
                if(++cnt == n-1) return cost;
            }
        }
        return -1;
    }

    private static void make() {
        parents = new int[n + 1];

        for(int i=0; i<n; i++){
            parents[i] = i;
        }
    }

    private static int find(int value){
        if(parents[value] == value) return value;
        return parents[value] = find(parents[value]);
    }

    private static boolean union(int v1, int v2){
        int aroot = find(v1);
        int broot = find(v2);

        if(aroot == broot) return false;
        parents[aroot] = broot;
        return true;
    }

    private static void inputValue() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        board=  new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            board.add(new Edge(from, to, weight));
        }


    }
}
