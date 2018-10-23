package pl.mw.qlearning;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QMatrix {

    private int states;
    private int actions;

    /**
     * Every entry represents state
     */
    private List<List<Float>> values = new ArrayList<>();

    public void init(int states, int actions) {
        this.states = states;
        this.actions = actions;

        for (int i = 0; i < states; i++) {
            List<Float> elements = new ArrayList<>(actions);
            for (int j = 0; j < actions; j++) {
                elements.add(0.0f);
            }
            values.add(elements);
        }
    }

    public void addState(List<Float> state) {
        if (state.size() >= actions) {
            throw new IllegalArgumentException("To many elements in state list.");
        }

        if (values.size() >= states) {
            throw new IllegalStateException("Cannot add more states since it will exceed the limit.");
        }

        values.add(state);
    }

    public List<Float> getState(int i) {
        if (i >= states) {
            throw new IllegalArgumentException("Argument exceeds number of states in matrix.");
        }

        return values.get(i);
    }

    public int numberOfStates() {
        return values.size();
    }

    public Float getValue(int state, int action) {
        return values.get(state)
                .get(action);
    }

    public int pickRandomState() {
        return ThreadLocalRandom.current()
                .nextInt(0, values.size());
    }

    public void print() {
        for (List<Float> state : values) {
            String join = state.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            System.out.println(join);
        }
    }
}
