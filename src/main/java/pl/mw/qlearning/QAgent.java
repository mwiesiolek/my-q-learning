package pl.mw.qlearning;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class QAgent {
    private QMemory memory;

    @Getter
    @Setter
    private int currentState;

    private float epsilon = 1;

    public void init(int states, int actions) {
        QMatrix matrix = new QMatrix();
        matrix.init(states, actions);

        memory = new QMemory(matrix);
    }

    public Float getQ(int state, int action) {
        return memory.getValue(state, action);
    }

    public int pickAction(int selected) {

        if (ThreadLocalRandom.current()
                .nextFloat() > epsilon) {
            return exploit(selected);
        } else {
            return explore(selected);
        }
    }

    private int exploit(int selected) {
        return memory.findBestAction(selected);
    }

    private int explore(int selected) {
        List<Float> state = memory.getState(selected);

        List<Integer> availableMoves = new ArrayList<>();
        for (int i = 0; i < state.size(); i++) {

            if (state.get(i) >= 0) {
                availableMoves.add(i);
            }
        }

        int randomAction = ThreadLocalRandom.current()
                .nextInt(0, availableMoves.size());

        return availableMoves.get(randomAction);
    }

    public void updateQ(int action, Float q) {
        memory.updateValue(currentState, action, q);
    }

    public void printMemory() {
        memory.print();
    }
}
