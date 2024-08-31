import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김성현
 * 1. 문제 설명
 * N * N 배열에 전선, 프로세서가 있음.
 * 전선은 직선으로 설치, 겹치면 안됨.
 * 모든 프로세서의 전원을 연결하는 최소 전선 길이.
 * 가장자리 프로세서는 전원 연결된것으로
 *
 * 2. input
 * T
 * N
 * N * N 프로세서 정보 ( 0: 빈 cell, 1: core )
 *
 * 3. output
 * 최소 전선 길이 합.
 *
 * 4. 해결 방법
 *
 * 노드 위치 기록하기.
 * DFS로 탐색해보자.
 *
 * 방향에 따라서 선 하나씩 그어보기.
 * 모든 노드 순회 하면서 가능한 경우에만 쭉 긋기. ( 각 방향으로 1
 * 순회가 끝나면 맵을 초기화 해야되는뎀.. 흠.. 긋고나면 지우기.
 *
 *
 *
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[][] board;
    static ArrayList<int[]> processor;
    static int count = 0;
    static int lineCount = Integer.MAX_VALUE;
    static int core;
    static int result;

    static int[] xDir = {0, 0 , -1, 1};
    static int[] yDir = {1,-1, 0, 0};
     public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int tc =1; tc<= t; tc++){
            core = 0;
            result =Integer.MAX_VALUE;

            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            processor = new ArrayList<>();

            for(int i =0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if(board[i][j] == 1 && (i > 0 && j >0 && i < n-1 && j < n-1)){
                        int[] tmp = {i,j};
                        processor.add(tmp);
                    }
                }
            }

            dfs( 0,0,0);
            // 현재 문제 :

            System.out.printf("#%d %d\n", tc, result);
        }
    }

    private static boolean isPossible(int x, int y, int direction){
        int nx = x + xDir[direction];
        int ny = y + yDir[direction];
        while(nx >= 0 && nx <n && ny >=0 && ny < n){
            if(board[nx][ny] == 1 || board[nx][ny] == 2){
                return false;
            }
            nx += xDir[direction];
            ny += yDir[direction];
        }
        return true;
    }

    private static int drawLine(int x, int y, int direction, int number){
         int count =0;
        int nx = x + xDir[direction];
        int ny = y + yDir[direction];
        while(nx >= 0 && nx <n && ny >=0 && ny < n){
            if(number ==2){
                count++;
            }
            board[nx][ny] = number;
            nx += xDir[direction];
            ny += yDir[direction];


        }
        return count;
//        for(int i=0; i<n; i++){
//            for(int j=0; j<n; j++){
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    private static void dfs( int depth, int c, int line) {


         if(depth == processor.size()){
             if(core < c){
                 core = c;
                result = line;
             }else if(core == c){
                 result = Math.min(result, line);
             }

             return;
         }

        int[] p = processor.get(depth);
        int x = p[0];
        int y = p[1];

        for(int dir = 0; dir<4; dir++){
            if(isPossible(x,y,dir)){
                int count1 = drawLine(x,y,dir, 2);
                dfs(depth+1, c+1, line + count1);
                drawLine(x,y,dir, 0);

            }

        }
        dfs(depth+1, c, line);


    }
}
