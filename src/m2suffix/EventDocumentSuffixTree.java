package m2suffix;
import java.util.HashMap;
import java.util.Map;

public class EventDocumentSuffixTree {

    static class Node {
        Map<Character, Node> children = new HashMap<>();
    }

    private Node root = new Node();

    public void build(String text) {
        for (int i = 0; i < text.length(); i++) {
            Node current = root;

            for (int j = i; j < text.length(); j++) {
                char ch = text.charAt(j);

                current.children.putIfAbsent(ch, new Node());
                current = current.children.get(ch);
            }
        }
    }

    public boolean search(String pattern) {
        Node current = root;

        for (char ch : pattern.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }

            current = current.children.get(ch);
        }

        return true;
    }

    public static void main(String[] args) {
        String description = "robotics workshop";

        EventDocumentSuffixTree tree =
                new EventDocumentSuffixTree();

        tree.build(description);

        System.out.println("Search robotics: " +
                tree.search("robotics"));

        System.out.println("Search workshop: " +
                tree.search("workshop"));

        System.out.println("Search music: " +
                tree.search("music"));
    }
}


