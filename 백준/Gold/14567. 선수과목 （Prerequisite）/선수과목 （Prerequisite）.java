import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static ArrayList<Integer>[] board;
    static int[] count;
    static int[] result;

    static int N;
    static int M;

    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        inputValue();
        bfs();
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<result.length; i++){
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            if(count[i] == 0){
                queue.add(i);
            }
        }
        int semester = 1;
        while(!queue.isEmpty()){

            int number = queue.size();

            for(int i = 0; i < number; i++){
                int popedIndex = queue.poll();
                result[popedIndex] = semester;
                for(int child : board[popedIndex]){
                    count[child]--;
                    if(count[child] == 0){
                        queue.add(child);
                    }
                }
            }
            semester++;
        }

    }

    private static void inputValue() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1];
        result = new int[N + 1];
        count = new int[N + 1];
        for(int i=1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            board[A].add(B);
            count[B]++;
        }
    }
}