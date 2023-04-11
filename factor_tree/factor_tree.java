import java.util.*;
import java.io.*;

public class factor_tree {
    static final int MAXN = 100005;
    public static void main(String[] args) throws IOException {
        // spf[i] stores the smallest prime factor of i. Used to quickly prime factorize a number.
        int[] spf = new int[MAXN];
        for (int i = 2; i < MAXN; i++) {
            for (int j = i; j < MAXN; j += i) {
                if (spf[j] == 0) {
                    spf[j] = i;
                }
            }
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        // Prime factorize all numbers from floor(N / 2) + 1 to N, and sum the number of prime factors.
        int N = Integer.parseInt(in.readLine());
        int half = N / 2 + 1;
        int count = 0;
        for (int i = half; i <= N; i++) {
            int num = i;
            while (num > 1) {
                count++;
                num /= spf[num];
            }
        }
        out.println(count);

        in.close();
        out.close();
    }
}
