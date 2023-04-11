import java.util.*;
import java.io.*;

public class feeling_thirsty {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int K = Integer.parseInt(line.nextToken());
        int[] T = new int[N];
        line = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(T);

        int bottles = 0;
        int time = 0;
        for (int i = 0; i < N; i++) {
            time += T[i];
            if (time > K) {
                break;
            }
            bottles++;
        }
        out.println(bottles);

        in.close();
        out.close();
    }
}
