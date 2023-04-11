import java.util.*;
import java.io.*;

public class anuj_goats {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());

        Point[] goats = new Point[N];
        for (int i = 0; i < N; i++) {
            line = new StringTokenizer(in.readLine());
            goats[i] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
        }
        Point[] fences = new Point[M];
        for (int i = 0; i < M; i++) {
            line = new StringTokenizer(in.readLine());
            fences[i] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
            fences[i].ind = i;
        }

        // surround[i][j] stores if fence i -> fence j -> goat is counterclockwise for all goats
        boolean[][] surround = new boolean[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                surround[i][j] = surrounds_goats(fences[i], fences[j], goats);
            }
        }
        // Sort fences by y coordinate, breaking ties by x coordinate
        Arrays.sort(fences, new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                if (a.y == b.y) {
                    return a.x - b.x;
                }
                return a.y - b.y;
            }
        });

        long res = 0;
        for (int i = 0; i < M - 2; i++) {
            Point lowest = fences[i];
            Point[] fences_above = new Point[M - i];
            for (int j = i; j < M; j++) {
                fences_above[j - i] = fences[j];
            }
            // Sorting fences above in counter-clockwise order, breaking ties by distance
            Arrays.sort(fences_above, new Comparator<Point>() {
                @Override
                public int compare(Point a, Point b) {
                    if (a.x == lowest.x && a.y == lowest.y) {
                        return -1;
                    } else if (b.x == lowest.x && b.y == lowest.y) {
                        return 1;
                    }
                    int o = -orientation(lowest, a, b);
                    if (o == 0) {
                        return Long.signum(sq_dist(lowest, a) - sq_dist(lowest, b));
                    }
                    return o;
                }
            });
            res += count_ways(fences_above, goats, surround);
        }
        out.println(res);

        in.close();
        out.close();
    }

    // Counts ways to create convex polygons with a subset of fences as vertices surrounding goats,
    // and fences[0] being the lowest point in the polygon.
    static long count_ways(Point[] fences, Point[] goats, boolean[][] surround) {
        // dp[i][j] stores the # ways to create convex segment arround goats with j the most recent fence and i the second most recent fence.
        long[][] dp = new long[fences.length][fences.length];

        // Base cases
        for (int i = 1; i < fences.length; i++) {
            if (surround[fences[0].ind][fences[i].ind]) {
                dp[0][i] = 1;
            }
        }
        
        for (int i = 0; i < fences.length - 2; i++) {
            for (int j = i + 1; j < fences.length - 1; j++) {
                for (int k = j + 1; k < fences.length; k++) {
                    if (orientation(fences[i], fences[j], fences[k]) >= 0 && surround[fences[j].ind][fences[k].ind]) {
                        dp[j][k] += dp[i][j];
                    } 
                }
            }
        }

        long res = 0;
        for (int i = 1; i < fences.length - 1; i++) {
            for (int j = i + 1; j < fences.length; j++) {
                if (orientation(fences[i], fences[j], fences[0]) >= 0 && surround[fences[j].ind][fences[0].ind]) {
                    res += dp[i][j];
                }
            }
        }
        return res;
    }

    // If a -> b -> goat is counter-clockwise for all goats, return true, otherwise false
    static boolean surrounds_goats(Point a, Point b, Point[] goats) {
        for (Point goat : goats) {
            if (orientation(a, b, goat) == -1) {
                return false;
            }
        }
        return true;
    }

    // Returns 1 if a -> b -> c is counter-clockwise, 0 if collinear, -1 if clockwise
    static int orientation(Point a, Point b, Point c) {
        Point v1 = new Point(b.x - a.x, b.y - a.y);
        Point v2 = new Point(c.x - b.x, c.y - b.y);

        // The determine gives the signed area of the triangle a -> b -> c
        // | v1.x v1.y |
        // | v2.x v2.y |

        long determinant = (long) v1.x * v2.y - (long) v1.y * v2.x;
        return Long.signum(determinant);
    }

    static long sq_dist(Point a, Point b) {
        return (long)(a.x - b.x) * (a.x - b.x) + (long)(a.y - b.y) * (a.y - b.y);
    }

    static class Point {
        int x, y, ind;
        Point(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }
}
