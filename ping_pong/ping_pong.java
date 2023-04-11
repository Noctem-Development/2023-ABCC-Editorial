import java.util.*;
import java.io.*;

public class ping_pong {

    static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        // Answer is (2N - 2) choose (N - 1)
        int N = Integer.parseInt(in.readLine());
        long[] fact = new long[2 * N];
        fact[0] = 1;
        for (int i = 1; i < 2 * N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        out.println(fact[2 * N - 2] * modinv(fact[N - 1]) % MOD * modinv(fact[N - 1]) % MOD);

        in.close();
        out.close();
    }

    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }

    static long binpow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if (b % 2 == 0) {
            return small * small % MOD;
        } else {
            return small * small % MOD * a % MOD;
        }
    }
}
