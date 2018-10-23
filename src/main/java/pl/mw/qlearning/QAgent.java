package pl.mw.qlearning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class QAgent {
    private QMemory memory;

    @Getter
    @Setter
    private int currentState;

    public void init(int states, int actions) {
        QMatrix matrix = new QMatrix();
        matrix.init(states, actions);

        memory = new QMemory(matrix);
    }

    public Float getQ(int state, int action) {
        return memory.getValue(state, action);
    }

    public int pickAction(int state) {
        return this.memory.pickAction(state);
    }

    public void updateQ(int action, Float q) {
        memory.updateValue(currentState, action, q);
    }

    public void printMemory() {
        memory.print();
    }
}
