

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input4DigitNumber=  Integer.parseInt( String.join("", br.readLine().split(" "))) ;
		boolean[] clockNumbers = new boolean[10000];
		for(int i=1111; i<=9999; i++) {
			clockNumbers[getClockNumber(i)] = true ;
		}
		int count = 0;
		for(int i=1111; i<=9999; i++) {
			if(!clockNumbers[i]) {
				continue;
			}
			count++;
			if(i == getClockNumber(input4DigitNumber)) {
				System.out.println(count);
				return;
			}
		}
	}

	private static int getClockNumber(int number) {
		String tmp = String.valueOf(number) + String.valueOf(number);
		int min = Integer.MAX_VALUE;
		for(int i=0; i<4; i++) {
			min = Math.min(Integer.parseInt(tmp.substring(i, i+ 4)), min) ;
		}
		return min;
	}



}
