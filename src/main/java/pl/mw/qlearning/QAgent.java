package pl.mw.qlearning;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class QAgent {
    private QMemory memory;

    @Setter
    private QReward reward;

    @Getter
    @Setter
    private int currentState;

    private float epsilon = 0.5f;

    public void init(int states, int actions) {
        QMatrix matrix = new QMatrix();
        matrix.init(states, actions);

        memory = new QMemory(matrix);
    }

    public Float getQ(int state, int action) {
        return memory.getValue(state, action);
    }

    public int pickAction() {
        if (ThreadLocalRandom.current()
                .nextFloat() > epsilon) {
            return exploit();
        } else {
            return explore();
        }
    }

    public void updateQ(int state, int action, Float q) {
        memory.updateValue(state, action, q);
    }

    public void printQTable() {
        memory.print();
    }

    public void updateEpsilon(float delta) {
        epsilon -= delta;
    }

    private int exploit() {
        return reward.findBestAction(currentState);
    }

    private int explore() {
        List<Float> state = reward.getRewardsForState(currentState);

        List<Tuple<Integer, Float>> rewards = new ArrayList<>();
        for (int i = 0; i < state.size(); i++) {
            if (state.get(i) >= 0 && i != currentState) {
                rewards.add(new Tuple<>(i, state.get(i)));
            }
        }

        return rewards.stream()
                .max(Comparator.comparing(Tuple::getValue))
                .map(Tuple::getKey)
                .orElseThrow(IllegalStateException::new);
    }
}
