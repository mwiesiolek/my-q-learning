package pl.mw.qlearning;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QReward {
    private QMatrix matrix;

    public Float getReward(int state, int action) {
        return matrix.getValue(state, action);
    }

    public int pickRandomState() {
        return matrix.pickRandomState();
    }

    public List<Float> findAdjacent(int state) {
        return matrix.getState(state)
                .stream()
                .filter(v -> v >= 0)
                .collect(Collectors.toList());
    }
}
