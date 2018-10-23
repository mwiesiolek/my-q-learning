package pl.mw.qlearning;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QMemory {
    private QMatrix matrix;

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
