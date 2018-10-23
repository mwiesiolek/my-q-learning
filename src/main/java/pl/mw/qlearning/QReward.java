package pl.mw.qlearning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QReward {
    private static final int NOT_EXIST_IDS = -1;

    private QMatrix matrix;
    Queue<Integer> states;

    public QReward(QMatrix matrix) {
        this.matrix = matrix;
        states = new LinkedList<>();

        init();
    }

    private void init() {
        int number = this.matrix.numberOfStates();
        List<Integer> temp = new ArrayList<>();
        IntStream.range(0, number)
                .forEach(temp::add);
        Collections.shuffle(temp);
        states.addAll(temp);
    }

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

    public Float getReward(int state, int action) {
        return matrix.getValue(state, action);
    }

    public List<Float> getRewardsForState(int state) {
        return matrix.getState(state);
    }

    public Integer pickRandomState() {

        Optional<Integer> result = Optional.ofNullable(states.poll());
        if (result.isPresent()) {
            return result.get();
        } else {
            init();
            return states.poll();
        }
    }

    public List<Float> findAdjacent(int state) {
        return matrix.getState(state)
                .stream()
                .filter(v -> v >= 0)
                .collect(Collectors.toList());
    }
}
