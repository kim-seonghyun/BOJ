import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String n = br.readLine();
            if(Objects.equals(n, "0")){
                break;
            }
            int len = n.length();
            String answer = "yes";
            for(int i=0; i< len /2; i++){
                if (n.charAt(i) != n.charAt(len - 1 -i)){
                    answer = "no";
                    break;
                }
            }
            System.out.println(answer);
        }
    }
}
