import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static int M;
    static int[][] arr;
    static int[][] housePositions;
    static int[][] chickenPositions;
    private static int[] number;
    private static ArrayList<int[]> comb;
    private static boolean[] visited;


    private static void combinationOfChicken(int depth, int currentValue)  {
        if(depth == M){
            comb.add(Arrays.copyOf(number, M));
            return;
        }

        for(int i = currentValue; i< chickenPositions.length; i++){
            if(!visited[i]){
                visited[i] = true;
                number[depth] = i;
                combinationOfChicken(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        comb = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[M];

        arr = new int[N][N];
        housePositions = new int[N * N][2];
        chickenPositions = new int[N*N][2];
        int houseCount = 0;
        int chickenCount = 0;

        for(int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1){
                    housePositions[houseCount][0] = i;
                    housePositions[houseCount][1] = j;
                    houseCount++;
                }

                if(arr[i][j] == 2){
                    chickenPositions[chickenCount][0] = i;
                    chickenPositions[chickenCount][1] = j;
                    chickenCount++;
                 }
            }
        }
        visited = new boolean[chickenCount];

        // 집 위치 저장
        housePositions = Arrays.copyOf(housePositions, houseCount);
        chickenPositions = Arrays.copyOf(chickenPositions, chickenCount);

        // 치킨집 위치에서 치킨집이 M개일때의 모든 조합 구하기.
        // int[치킨집 개수][2 * M]
        combinationOfChicken(0, 0);


        // 각 치킨집 경우의 수 마다 최소 거리 구하기.

        int resultShortestDistance = Integer.MAX_VALUE;

        for(int[] positionIndex: comb){
            int totalDistance = getTotalDistance(positionIndex);
            resultShortestDistance = Math.min(resultShortestDistance, totalDistance);
        }

        System.out.println(resultShortestDistance);

    }


    private static int getTotalDistance(int[] position) {
        int totalDistance = 0;

        for(int[] housePosition: housePositions){
            int houseX = housePosition[0];
            int houseY = housePosition[1];
            int distance = Integer.MAX_VALUE;
            for (int i : position) {
                int chickenX = chickenPositions[i][0];
                int chickenY = chickenPositions[i][1];

                distance = Math.min(distance, Math.abs(chickenX - houseX) + Math.abs(chickenY - houseY));
            }
            totalDistance += distance;
        }
        return totalDistance;
    }


}
