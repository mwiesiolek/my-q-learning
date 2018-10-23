package pl.mw.qlearning;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QTable {
    private QMatrix matrix;

    public int findBestAction(int selected) {
        List<Float> state = matrix.getState(selected);

        Float max = Float.MIN_VALUE;
        for (Float action : state) {
            if (action > max) {
                max = action;
            }
        }

        //todo what if there are multiple equal Q values?
        return state.indexOf(max);
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
