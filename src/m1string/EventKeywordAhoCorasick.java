package m1string;
import java.util.*;
public class EventKeywordAhoCorasick {
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node failure;
        List<String> words = new ArrayList<>();
    }

    private Node root = new Node();

    public void addWord(String word) {
        Node current = root;

        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new Node());
            current = current.children.get(ch);
        }

        current.words.add(word);
    }

    public void buildFailureLinks() {
        Queue<Node> queue = new LinkedList<>();

        root.failure = root;

        for (Node child : root.children.values()) {
            child.failure = root;
            queue.add(child);
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Map.Entry<Character, Node> entry :
                    current.children.entrySet()) {

                char ch = entry.getKey();
                Node child = entry.getValue();
                Node failure = current.failure;

                while (failure != root &&
                        !failure.children.containsKey(ch)) {
                    failure = failure.failure;
                }

                if (failure.children.containsKey(ch)) {
                    child.failure = failure.children.get(ch);
                } else {
                    child.failure = root;
                }

                child.words.addAll(child.failure.words);
                queue.add(child);
            }
        }
    }

    public void search(String text) {
        Node current = root;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            while (current != root &&
                    !current.children.containsKey(ch)) {
                current = current.failure;
            }

            if (current.children.containsKey(ch)) {
                current = current.children.get(ch);
            }

            for (String word : current.words) {
                System.out.println(word + " found at index "
                        + (i - word.length() + 1));
            }
        }
    }

    public static void main(String[] args) {
        EventKeywordAhoCorasick ac =
                new EventKeywordAhoCorasick();

        ac.addWord("AI");
        ac.addWord("workshop");
        ac.addWord("robotics");

        ac.buildFailureLinks();

        String description =
                "AI workshop and robotics event";

        ac.search(description);
    }
}


