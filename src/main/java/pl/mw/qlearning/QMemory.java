package pl.mw.qlearning;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QMemory {
    private static final int NOT_EXIST_IDS = -1;

    private QMatrix matrix;

    public int findBestAction(int selected) {
        List<Float> state = matrix.getState(selected);

        Tuple<Integer, Float> max = new Tuple<>(NOT_EXIST_IDS, -1_000.0F);
        for (int i = 0; i < state.size(); i++) {
            if (state.get(i) > max.getValue()) {
                max = new Tuple<>(i, state.get(i));
            }
        }

        //todo what if there are multiple equal Q values?
        if (max.getKey() == NOT_EXIST_IDS) {
            throw new IllegalStateException("Max not found.");
        }
        return max.getKey();
    }

    public Float getValue(int state, int action) {
        return matrix.getValue(state, action);
    }

    public void updateValue(int state, int action, Float value) {
        matrix.getState(state)
                .set(action, value);
    }

    public List<Float> getState(int state) {
        return matrix.getState(state);
    }

    public void print() {
        matrix.print();
    }
}
