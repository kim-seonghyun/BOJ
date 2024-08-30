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
 * 1. 문제설명 그래프의 최소 스패닝 트리 구하는 프로그램
 *
 * 2. input
 *
 * V E
 * E줄 (A B C)
 *
 * 3. output
 * 최소 스패닝 트리의 가중치
 *
 * 4. 해결방법
 * weight에는 음수가 포함되지만 크루스칼 알고리즘의 경우에 잘 동작할듯
 */
public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int e;
    static int v;
    static ArrayList<Edge> board;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        inputValue();
        make();
        long costs = getMinimumCost();
        System.out.println(costs);
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

    private static long getMinimumCost() {
        Collections.sort(board);
        int cnt= 0;
        long cost = 0;
        for(Edge edge: board){
            if(union(edge.from,edge.to)){
                cost += edge.weight;
                if(++cnt == v-1) return cost;
            }
        }
        return cost;
    }

    private static void make() {
        parents = new int[v+1];
        for(int i=0; i< parents.length; i++){
            parents[i] = i;
        }
    }

    private static void inputValue() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        board = new ArrayList<>();
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());


        for(int i =0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            board.add(new Edge(from, to, weight));
        }


    }
}
