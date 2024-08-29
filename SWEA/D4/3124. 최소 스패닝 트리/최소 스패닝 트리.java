import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @author 김성현
 * 1. 문제 설명 : Minimum Spanning Tree 구하기
 *
 * 2. input
 * T
 * V E
 * E개의 A B C
 *
 * 3. output
 * minimum spanning tree 가중치
 *
 * 4. 해결방법
 * 간선 개수가 더 많기 때문에 Kruskal 알고리즘으로 해결한다.
 * 1. 간선을 가중치 기준으로 정렬
 * 2. 결과 트리 만들기 // union find를 잘 모른다.
 * 3. 모든 간선에서 union find에 걸리지 않는간선을 v-1개 선택할때까지 반복.
 *
 */
class Edge implements Comparable<Edge>{
    int A;
    int B;
    int C;

    public Edge(int A, int B, int C){
        this.A = A;
        this.B = B;
        this.C = C;
    }


    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.C, o.C);
    }
}

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static ArrayList<Edge> board;
    static int[] parents;

    public static int find(int value){
        if(parents[value] == value) return value;
        return parents[value] = find(parents[value]);
    }

    public static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;


        parents[rootA] = rootB;
        return true;
    }
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in ));
        int T = Integer.parseInt(br.readLine());

        for(int tc= 1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            parents = new int[V + 1];
            board = new ArrayList<>();
            for(int i=1; i<=V; i++){
                parents[i] = i;
            }

            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                board.add(new Edge(A, B, C));
            }


            Collections.sort(board);

            long cnt = 1 , cost = 0;
            for(Edge e: board){
                if(union(e.A, e.B)){
                    cost += e.C;
                    if(++cnt == V) break;
                }
            }
            System.out.printf("#%d %d\n", tc,cost);

        }
    }
}
