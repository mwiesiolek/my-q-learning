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
    private QMatrix matrix;
    private Queue<Integer> states;

    public QReward(QMatrix matrix) {
        this.matrix = matrix;
        states = new LinkedList<>();

        init();
    }

    private void init() {
        int number = this.matrix.numberOfStates();
        List<Integer> temp = new ArrayList<>();
        IntStream.of(number)
                .forEach(temp::add);
        Collections.shuffle(temp);
        states.addAll(temp);
    }

    public Float getReward(int state, int action) {
        return matrix.getValue(state, action);
    }

    public Integer pickRandomState() {
        return Optional.ofNullable(states.poll())
                .orElseThrow(NotStateFoundException::new);
    }

    public List<Float> findAdjacent(int state) {
        return matrix.getState(state)
                .stream()
                .filter(v -> v >= 0)
                .collect(Collectors.toList());
    }
}
