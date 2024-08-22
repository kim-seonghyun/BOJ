import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static String dir;
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] board;

    public static void main(String[] args) throws NumberFormatException, IOException {

        // br = new BufferedReader(new FileReader("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            inputValue();
            System.out.printf("#%d\n", tc);
            switch (dir) {
                case "up":
                    up();
                    break;
                case "down":
                    down();
                    break;

                case "right":
                    right();
                    break;

                case "left":
                    left();
                    break;

            }

        }

    }

    private static void up() {
        LinkedList<LinkedList<Integer>> a = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            a.add(new LinkedList<>());
            for (int j = 0; j < N; j++) {
                if (board[j][i] != 0) {
                    a.get(i).add(board[j][i]);
                }
            }
        }
        LinkedList<LinkedList<Integer>> b = new LinkedList<>();
        // 블록 합치는 코드
        for (int linkIndex = 0; linkIndex < N; linkIndex++) {
            LinkedList<Integer> tmp = new LinkedList<>();

            for (int i = 0; i < a.get(linkIndex).size(); i++) {
                if (i == a.get(linkIndex).size() - 1) {
                    tmp.add(a.get(linkIndex).get(i));
                    break;
                }
                if (a.get(linkIndex).get(i).intValue() == a.get(linkIndex).get(i + 1).intValue()) {
                    tmp.add(a.get(linkIndex).get(i) * 2);
                    i++;
                } else {
                    tmp.add(a.get(linkIndex).get(i).intValue());
                    if (i == (a.get(linkIndex).size() - 2)) {
                        tmp.add(a.get(linkIndex).get(i + 1));
                        break;
                    }
                }

            }
            b.add(tmp);

        }
        int[][] resultArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < b.get(i).size(); j++) {
                resultArr[j][i] = b.get(i).get(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(resultArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void down() {
        LinkedList<LinkedList<Integer>> a = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            a.add(new LinkedList<>());
            for (int j = N - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    a.get(i).add(board[j][i]);
                }
            }

        }
        LinkedList<LinkedList<Integer>> b = new LinkedList<>();
        for (int linkIndex = 0; linkIndex < N; linkIndex++) {
            LinkedList<Integer> tmp = new LinkedList<>();

            for (int i = 0; i < a.get(linkIndex).size(); i++) {
                if (i == a.get(linkIndex).size() - 1) {
                    tmp.add(a.get(linkIndex).get(i).intValue());
                    break;
                }
                if (a.get(linkIndex).get(i).intValue() == a.get(linkIndex).get(i + 1).intValue()) {
                    tmp.add(a.get(linkIndex).get(i) * 2);
                    i++;
                } else {
                    tmp.add(a.get(linkIndex).get(i));
                    if (i == (a.get(linkIndex).size() - 2)) {
                        tmp.add(a.get(linkIndex).get(i + 1));
                        break;
                    }
                }
            }
            b.add(tmp);
        }
        int[][] resultArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < b.get(i).size(); j++) {
                resultArr[N - 1 - j][i] = b.get(i).get(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(resultArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void left() {
        LinkedList<LinkedList<Integer>> a = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            a.add(new LinkedList<>());
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    a.get(i).add(board[i][j]);
                }
            }
        }
        LinkedList<LinkedList<Integer>> b = new LinkedList<>();
        for (int linkIndex = 0; linkIndex < N; linkIndex++) {
            LinkedList<Integer> tmp = new LinkedList<>();
            // 같으면 합치고
            // 다르면 그냥 두기

            for (int i = 0; i < a.get(linkIndex).size(); i++) {

                if (i == a.get(linkIndex).size() - 1) {
                    tmp.add(a.get(linkIndex).get(i));
                    break;
                }
                if (a.get(linkIndex).get(i).intValue() == a.get(linkIndex).get(i + 1).intValue()) {
                    tmp.add(a.get(linkIndex).get(i) * 2);
                    i++;
                } else {
                    tmp.add(a.get(linkIndex).get(i));
                    if (i == (a.get(linkIndex).size() - 2)) {
                        tmp.add(a.get(linkIndex).get(i + 1));
                        break;
                    }
                }

            }
            b.add(tmp);

        }
        int[][] resultArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < b.get(i).size(); j++) {
                resultArr[i][j] = b.get(i).get(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(resultArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void right() {
        LinkedList<LinkedList<Integer>> a = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            a.add(new LinkedList<>());
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    a.get(i).add(board[i][j]);

                }
            }

        }
        LinkedList<LinkedList<Integer>> b = new LinkedList<>();
        for (int linkIndex = 0; linkIndex < N; linkIndex++) {
            LinkedList<Integer> tmp = new LinkedList<>();

            for (int i = 0; i < a.get(linkIndex).size(); i++) {
                if (i == a.get(linkIndex).size() - 1) {
                    tmp.add(a.get(linkIndex).get(i));
                    break;
                }
                if (a.get(linkIndex).get(i).intValue() == a.get(linkIndex).get(i + 1).intValue()) {
                    tmp.add(a.get(linkIndex).get(i) * 2);
                    i++;
                } else {
                    tmp.add(a.get(linkIndex).get(i));
                    if (i == (a.get(linkIndex).size() - 2)) {
                        tmp.add(a.get(linkIndex).get(i + 1));
                        break;
                    }
                }

            }
            b.add(tmp);

        }
        int[][] resultArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < b.get(i).size(); j++) {
                resultArr[i][N - 1 - j] = b.get(i).get(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(resultArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void inputValue() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dir = st.nextToken();
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

}