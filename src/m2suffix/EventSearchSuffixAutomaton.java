package m2suffix;
import java.util.HashMap;
import java.util.Map;

public class EventSearchSuffixAutomaton {

    static class State {
        int length;
        int link = -1;
        Map<Character, Integer> next = new HashMap<>();
    }

    private State[] states;
    private int size;
    private int last;

    public EventSearchSuffixAutomaton(int maxLength) {
        states = new State[2 * maxLength];
        states[0] = new State();
        size = 1;
        last = 0;
    }

    public void build(String text) {
        for (char ch : text.toCharArray()) {
            extend(ch);
        }
    }

    private void extend(char ch) {
        int current = size++;
        states[current] = new State();
        states[current].length = states[last].length + 1;

        int p = last;

        while (p != -1 && !states[p].next.containsKey(ch)) {
            states[p].next.put(ch, current);
            p = states[p].link;
        }

        if (p == -1) {
            states[current].link = 0;
        } else {
            int q = states[p].next.get(ch);

            if (states[p].length + 1 == states[q].length) {
                states[current].link = q;
            } else {
                int clone = size++;
                states[clone] = new State();
                states[clone].length = states[p].length + 1;
                states[clone].link = states[q].link;
                states[clone].next.putAll(states[q].next);

                while (p != -1 &&
                       states[p].next.getOrDefault(ch, -1) == q) {
                    states[p].next.put(ch, clone);
                    p = states[p].link;
                }

                states[q].link = clone;
                states[current].link = clone;
            }
        }

        last = current;
    }

    public boolean search(String pattern) {
        int current = 0;

        for (char ch : pattern.toCharArray()) {
            if (!states[current].next.containsKey(ch)) {
                return false;
            }

            current = states[current].next.get(ch);
        }

        return true;
    }

    public static void main(String[] args) {
        String description = "data science event";

        EventSearchSuffixAutomaton automaton =
                new EventSearchSuffixAutomaton(description.length());

        automaton.build(description);

        System.out.println("Search science: " +
                automaton.search("science"));

        System.out.println("Search event: " +
                automaton.search("event"));

        System.out.println("Search robotics: " +
                automaton.search("robotics"));
    }
}


