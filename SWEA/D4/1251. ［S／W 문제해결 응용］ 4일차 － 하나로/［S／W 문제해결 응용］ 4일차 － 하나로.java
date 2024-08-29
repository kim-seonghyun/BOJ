import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 1. 문제 설명
 * 모든 섬을 연결하면서 가중치 합이 최소인 MST 구하기.
 *
 * 2.input
 *
 * T
 * N
 * n개의 x좌표
 * n개의 Y좌표
 * 실수 E
 *
 * 3. output
 * 가중치 최소 합
 *
 *
 * 4. 해결 방법
 *  각 좌표마다 가장 가까운 노드와 거리 저장하여 거리순으로 정렬
 *
 */

class Node{
    int x;
    int y;

    public Node(int x, int y){
        this.x =x;
        this.y =y;
    }
}

class Node2 implements Comparable<Node2>{
    int start;
    int end;
    double cost;

    public Node2(int start, int end, double cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "start=" + start +
                ", end=" + end +
                ", cost=" + cost +
                '}';
    }

    @Override
    public int compareTo(Node2 o) {
        return Double.compare(this.cost, o.cost);
    }
}

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static ArrayList<Node> board;
    static ArrayList<Node2> board2;
    static double e;
    static int[] parents;
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        int t= Integer.parseInt(br.readLine());

        for(int tc =1; tc<=t; tc++){
            inputValue();
            // 각 노드마다 최소 거리 구한뒤 , 정렬시키기
            makeVertex();

            Collections.sort(board2);

            make();
            // 크루스칼 수행
            int cnt = 0;
            double cost = 0;
            for(Node2 node:board2){
                if(union(node.start, node.end)){
                    cost += node.cost;
                    if(++cnt == n -1) break;

                }

            }
            System.out.printf("#%d %.0f\n", tc, cost);

        }

    }

    private static void make() {
        parents = new int[1001];
        for(int i=1; i<parents.length; i++){
            parents[i] = i;
        }
    }

    private static int find(int value){
        if(parents[value] == value) return value;
        return parents[value] = find(parents[value]);
    }

    private static boolean union(int val1, int val2){
        int aroot = find(val1);
        int broot = find(val2);

        if(aroot == broot) return false;
        parents[aroot] = broot;
        return true;
    }

    private static void makeVertex() {
        board2 = new ArrayList<>();
        for(int i=0; i<board.size(); i++){
            for(int j= i + 1; j<board.size(); j++){
                Node tmp1 = board.get(i);
                Node tmp2 = board.get(j);
                board2.add(new Node2(i,j, e * (Math.pow(tmp1.x - tmp2.x, 2) + Math.pow(tmp1.y - tmp2.y, 2))));
            }
        }
    }

    private static void inputValue() throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        board = new ArrayList<>();
        for(int i=0 ;i<n; i++){
            board.add(new Node(Integer.parseInt(st.nextToken()), 0));
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0 ;i<n; i++){
            board.get(i).y = Integer.parseInt(st.nextToken());
        }
        e = Double.parseDouble(br.readLine());
    }
}
