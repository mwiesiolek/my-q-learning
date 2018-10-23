package pl.mw.qlearning;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * this means we use what we already know to select the best action at each step
     */
    private int exploit() {
        return memory.findBestAction(currentState);
    }

    /**
     * This means we need to do a lot of exploration, by randomly choosing our actions.
     */
    private int explore() {
        List<Float> rewards = reward.getRewardsForState(currentState);

        List<Tuple<Integer, Float>> availableRewards = new ArrayList<>();
        for (int i = 0; i < rewards.size(); i++) {
            if (rewards.get(i) >= 0) {
                availableRewards.add(new Tuple<>(i, rewards.get(i)));
            }
        }

        if (availableRewards.isEmpty()) {
            throw new IllegalStateException("Cannot be empty.");
        }

        Collections.shuffle(availableRewards);

        return availableRewards.get(0)
                .getKey();
    }
}
