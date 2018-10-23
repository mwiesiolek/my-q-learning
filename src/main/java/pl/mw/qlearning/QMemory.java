package pl.mw.qlearning;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QMemory {
    private QMatrix matrix;

    /**
     * Randomly
     */
    public int pickAction(int selected) {
        List<Float> state = matrix.getState(selected);
        int size = state.size();
        return ThreadLocalRandom.current()
                .nextInt(0, size);
    }

    public Float getValue(int state, int action) {
        return matrix.getValue(state, action);
    }

    public void updateValue(int state, int action, Float value) {
        matrix.getState(state)
                .set(action, value);
    }

    public void print() {
        matrix.print();
    }
}
