package pl.mw.qlearning;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class QAgent {
    private QTable table;

    @Getter
    @Setter
    private int currentState;

    private float epsilon = 1;

    public void init(int states, int actions) {
        QMatrix matrix = new QMatrix();
        matrix.init(states, actions);

        table = new QTable(matrix);
    }

    public Float getQ(int state, int action) {
        return table.getValue(state, action);
    }

    public int pickAction() {
        if (ThreadLocalRandom.current()
                .nextFloat() > epsilon) {
            return exploit(currentState);
        } else {
            return explore(currentState);
        }
    }

    public void updateQ(int action, Float q) {
        table.updateValue(currentState, action, q);
    }

    public void printQTable() {
        table.print();
    }

    public void updateEpsilon(float delta) {
        epsilon -= delta;
        System.out.println(String.format("New epsilon: %s", epsilon));
    }

    private int exploit(int selected) {
        return table.findBestAction(selected);
    }

    private int explore(int selected) {
        List<Float> state = table.getState(selected);

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
}
