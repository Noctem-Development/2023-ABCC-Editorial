import java.util.*;
import java.io.*;

public class let_him_cook {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int A = Integer.parseInt(line.nextToken());
        int B = Integer.parseInt(line.nextToken());
        out.println(N / A + " " + N % A / B);

        in.close();
        out.close();
    }
}
