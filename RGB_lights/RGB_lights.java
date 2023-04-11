import java.util.*;
import java.io.*;

public class RGB_lights {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(in.readLine());
            String S = in.readLine();
            int R = 0;
            int G = 0;
            int B = 0;
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == 'R') {
                    R++;
                } else if (S.charAt(i) == 'G') {
                    G++;
                } else {
                    B++;
                }
            }

            // If the sum of all light values is equal to all red, all green, or all blue lights under mod 3,
            // then it is possible. Otherwise, it is not.
            if ((G + 2 * B) % 3 == 0 || (G + 2 * B) % 3 == N % 3 || (G + 2 * B) % 3 == 2 * N % 3) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        in.close();
        out.close();
    }
}
