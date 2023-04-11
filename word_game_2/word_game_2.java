import java.util.*;
import java.io.*;

public class word_game_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
          
        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(in.readLine());
            ArrayList<String> words = new ArrayList<String>();
            for (int i = 0; i < N; i++) {
                words.add(in.readLine());
            }
            Collections.sort(words);
            Node root = construct_trie(words, ' ', 0);
            out.println(wins(root) ? 2 : 1);
        }

        in.close();
        out.close();
    }
    
    // Returns whether the player picking the current letter wins or not. Calculated recursively.
    static boolean wins(Node cur) {
        if (cur.children.size() == 0) {
            return false;
        }
        boolean all_false = true;
        for (Node child : cur.children) {
            if (wins(child)) {
                all_false = false;
                break;
            }
        }
        // The player that picks the current letter only wins if for all of its children the player
        // that picks them loses.
        return all_false;
    }

    // Constructs a trie recursively from a list of words
    static Node construct_trie(ArrayList<String> words, char c, int ind) {
        Node cur = new Node(c, new ArrayList<Node>());
        if (words.size() == 0) {
            return cur;
        }
        ArrayList<String> group = new ArrayList<String>();
        boolean end = false;
        for (int i = 0; i <= words.size(); i++) {
            if (i == words.size() || i > 0 && words.get(i).charAt(ind) != words.get(i - 1).charAt(ind)) {
                if (end) {
                    group.clear();
                }
                cur.children.add(construct_trie(group, words.get(i - 1).charAt(ind), ind + 1));
                group.clear();
                end = false;
            }
            if (i < words.size()) {
                if (ind < words.get(i).length() - 1) {
                    group.add(words.get(i));
                } else {
                    end = true;
                }
            }
        }
        return cur;
    }

    // The nodes of the trie
    static class Node {
        char c;
        ArrayList<Node> children;
        Node(char c, ArrayList<Node> children) {
            this.c = c;
            this.children = children;
        }
    }
}
