/*
 * 1868. 파핑파핑지뢰찾기
 * 
 * 1. 문제 설명
 * 
 * 지뢰가 표시된 지뢰찾기 게임에서
 * 최소한 클릭으로 숫자 찾아내기.
 * 
 * 2. input
 * 
 * T
 * N
 * N*N 지뢰밭 (.(빈칸), *(지뢰))
 * 
 * 3. OUTPUT : #1 2 
 * 최소 클릭 횟수
 * 
 * 4. 해결방법
 * 
 * 모든 배열에 주변 8방향의 지뢰 개수를 count하는 배열 만들기
 * 주변 지뢰가 0인 경우 클릭해서 지뢰 터뜨리기 ( count++)
 * 0인 지뢰가 없을때까지 반복
 * 
 * 남은 지뢰 개수 더해서 return
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static char[][] board;
    static ArrayList<int[]> zeroBomb = new ArrayList<>();
    static int[][] countBoard;
    static int count = 0;
    static int[] xDir = { -1, -1, -1, 0, 1, 1, 1, 0 };
    static int[] yDir = { -1, 0, 1, 1, 1, 0, -1, -1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            inputValue();
            getResult();
            System.out.printf("#%d %d\n", tc, count);
            count = 0;
        }
    }

    private static void getResult() {
        // 주변 지뢰 개수 저장하는 배열 만들기.
        countBoard = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    countBoard[i][j] = getCount(i, j);
                    if (countBoard[i][j] == 0) {
                        int[] tmp = { i, j };
                        zeroBomb.add(tmp);
                    }
                } else {
                    countBoard[i][j] = -1;
                }
            }
        }

        for (int[] position : zeroBomb) {
            int x = position[0];
            int y = position[1];

            if (board[x][y] == '.' && countBoard[x][y] == 0) {
                count++;
                bfs(x, y);

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    count++;
                }
            }
        }

        // 0 전부 터뜨리기

        // 남은개수 더하기
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        int[] tmp = { x, y };

        queue.add(tmp);

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int cx = position[0];
            int cy = position[1];
            if (countBoard[cx][cy] != 0) {
                continue;
            }
            if (visited[cx][cy]) {
                continue;
            }

            board[cx][cy] = Character.forDigit(countBoard[cx][cy], 10);
            visited[cx][cy] = true;

            for (int dir = 0; dir < xDir.length; dir++) {
                int nx = cx + xDir[dir];
                int ny = cy + yDir[dir];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (countBoard[nx][ny] == 0) {
                        int[] nextTmp = { nx, ny };
                        queue.add(nextTmp);
                        continue;
                    } else if (countBoard[nx][ny] > 0) {
                        board[nx][ny] = Character.forDigit(countBoard[nx][ny], 10);
                    }
                }
            }
        }

    }

    private static int getCount(int x, int y) {
        int count = 0;
        for (int i = 0; i < xDir.length; i++) {
            int nx = x + xDir[i];
            int ny = y + yDir[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (board[nx][ny] == '*') {
                    count++;
                }
            }
        }
        return count;

    }

    private static void inputValue() throws NumberFormatException, IOException {
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = tmp.charAt(j);
            }
        }

    }
}