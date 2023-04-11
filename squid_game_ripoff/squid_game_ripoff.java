import java.util.*;
import java.io.*;

public class squid_game_ripoff {

    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int N = Integer.parseInt(in.readLine());
        int[][] ranges = new int[N][2];
        Event[] events = new Event[N * 2];
        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            ranges[i][0] = Integer.parseInt(line.nextToken());
            ranges[i][1] = Integer.parseInt(line.nextToken());
            events[i * 2] = new Event(ranges[i][0], ranges[i][1] - ranges[i][0] + 1, 0);
            events[i * 2 + 1] = new Event(ranges[i][1] + 1, ranges[i][1] - ranges[i][0] + 1, 1);
        }
        Arrays.sort(events);

        int prev = -1;
        long ans = 0;

        // Example: If lengths of segments currently running are L1, L2, L3, cur stores (L1 - 1)(L2 - 1) + (L2 - 1)(L3 - 1) + (L3 - 1)(L1 - 1).
        // In general, cur stores the same calculation but generalized to any amount of currently running segments.
        long cur = 0;

        // Product of all currently running segment lengths. L1 * L2 * L3 * ...
        long prod = 1;

        // Product of all currently running segment lengths - 1. (L1 - 1) * (L2 - 1) * (L3 - 1) * ...
        long sub = 1;
        for (int i = 0; i < N * 2; i++) {
            if (events[i].x != prev) {
                if (prev != -1) {
                    ans += cur * modinv(prod) % MOD * (events[i].x - prev) % MOD;
                    ans %= MOD;
                }
                prev = events[i].x;
            }
            if (events[i].t == 0) {
                cur = (cur * (events[i].l - 1) % MOD + sub) % MOD;
                prod = prod * events[i].l % MOD;
                sub = sub * (events[i].l - 1) % MOD;
            } else {
                prod = prod * modinv(events[i].l) % MOD;
                sub = sub * modinv(events[i].l - 1) % MOD;
                cur = (cur - sub + MOD) * modinv(events[i].l - 1) % MOD;
            }
        }
        out.println(ans);

        in.close();
        out.close();
    }

    // Each Event is either the start or an end of a participants range
    static class Event implements Comparable<Event> {
        int x, l, t;

        Event(int xx, int ll, int tt) {
            x = xx;
            l = ll;
            t = tt;
        }

        @Override
        public int compareTo(Event o) {
            return x - o.x;
        }
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
