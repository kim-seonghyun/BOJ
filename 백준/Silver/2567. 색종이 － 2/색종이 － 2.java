import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static boolean[][] arr;
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		arr = new boolean[102][102];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			drawSquare(x,y);
		}
//		// 0이었다가 1만나면 길이 하나 추가 
//		for(int i=0; i<100; i++) {
//			for(int j=0; j<100; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//		 x, y축으로 레이저 발사
		int sum = 0;
		sum += getXLength();
		
		sum += getYLength();
		System.out.println(sum);
	}
	private static int getYLength() {
		int sum =0;
		// TODO Auto-generated method stub
		for(int i=0; i<arr.length -1; i++) {
			for(int j=0; j<arr.length -1; j++) {
				if(arr[j][i] != arr[j +1][i]) {
					sum++;
				}
			}
		}
		return sum;
	}
	private static int getXLength() {
		// TODO Auto-generated method stub
		int sum =0;
		// TODO Auto-generated method stub
		for(int i=0; i<arr.length -1; i++) {
			for(int j=0; j<arr.length -1; j++) {
				if(arr[i][j] != arr[i][j + 1]) {
					sum++;
				}
			}
		}
		return sum;
	}
	private static void drawSquare(int x, int y) {
		// TODO Auto-generated method stub
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				arr[x + i][y + j] = true;
			}
		}
	}
}
