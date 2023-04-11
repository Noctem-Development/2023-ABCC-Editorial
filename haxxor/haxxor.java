import java.util.*;
import java.io.*;

public class haxxor {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int N = Integer.parseInt(in.readLine());
        int[] a = new int[N];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }

        // Operations on indices 1 2, 1 2, 2 3, 2 3, ..., (N - 1) N, (N - 1) N turn everything
        // in the array into 0.
        out.println(2 * (N - 1));
        for (int i = 1; i < N; i++) {
            out.println(i + " " + (i + 1));
            out.println(i + " " + (i + 1));
        }

        in.close();
        out.close();
    }
}
