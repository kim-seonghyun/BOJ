import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static long maxSum = Integer.MIN_VALUE;
    static int[] board;
    static ArrayList<String> inputs;
    static int[][] alphabetSequence;

    public static void permutation(int depth, boolean[] isSelected) {
        if (depth == board.length) {
            long currentSum = 0;
            for (String input : inputs) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < input.length(); i++) {
                    // map이 느려서 그렇다고?
                    // 알파벳 순서를 어떻게 정렬할까?
                    // 2차원 배열을 써볼까..
                    // 행에 알파벳에서 숫자만 얻어내고
                    // 0번째 열에 순서를 입히면 될듯?

                    // board는 특정 순서의 알파벳의 값이니까

                    // 2차원 배열의 열에는 순서를 적어야 겠다.
                    sb.append(board[alphabetSequence[input.charAt(i) - 'A'][0]]);
                }
                currentSum += Long.parseLong(sb.toString());
            }
            maxSum = Math.max(currentSum, maxSum);
            // 전체 값에 대입하고 max갑 갱신
            return;
        }

        for (int i = 9; i >= 10 - isSelected.length; i--) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                board[depth] = i;
                permutation(depth + 1, isSelected);
                isSelected[i] = false;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new
        // FileReader("C:\\Users\\SSAFY\\Downloads\\2번_Input.txt"));

        int n = Integer.parseInt(br.readLine());
        HashSet<Character> set = new HashSet<>();
        inputs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            inputs.add(br.readLine());
            for (int j = 0; j < inputs.get(i).length(); j++) {
                set.add(inputs.get(i).charAt(j));
            }
        }

        alphabetSequence = new int[26][1];
        ArrayList<Character> test1 = new ArrayList<>(set);

        for (int i = 0; i < test1.size(); i++) {
            alphabetSequence[test1.get(i) - 'A'][0] = i;
        }

        // test1의 길이의 인덱스 배열에 0~9까지 값 넣어서 순열 구하기.
        boolean[] selected = new boolean[10];
        board = new int[test1.size()];
        permutation(0, selected);
        System.out.println(maxSum);

    }

}
