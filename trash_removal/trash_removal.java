import java.util.*;
import java.io.*;

public class trash_removal {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
            
        int N = Integer.parseInt(in.readLine());
        StringTokenizer line = new StringTokenizer(in.readLine());
        
        int prev = 0;
        int res = Integer.MAX_VALUE;

        // Looping through the "gaps" between cells with trash
        for (int i = 1; i <= N + 1; i++) {
            if (i == N + 1 || Integer.parseInt(line.nextToken()) == 1) {
                // Gusts of wind needed to push trash off the left and then right
                res = Math.min(res, prev * 2 + (N + 1 - i));
                // Gusts of wind needed to push trash off the right and then left
                res = Math.min(res, prev + (N + 1 - i) * 2);
                prev = i;
            }
        }

        if (res == Integer.MAX_VALUE) {
            res = 0;
        }
        out.println(res);

        in.close();
        out.close();
    }
}
