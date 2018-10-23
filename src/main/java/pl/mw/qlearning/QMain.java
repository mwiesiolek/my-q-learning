package pl.mw.qlearning;

import com.google.common.collect.Lists;

public class QMain {

    private static final int EPISODES = 50;
    private static final int STATES = 5;
    private static final int ACTIONS = 5;

    public static void main(String[] args) {
        QController controller = new QController(createReward(), new QAgent(), new QParameter(0.2f, 0.7f));
        controller.start(EPISODES, STATES, ACTIONS, 5);
    }

    private static QReward createReward() {
        QMatrix matrix = new QMatrix();
        matrix.setStates(STATES);
        matrix.setActions(ACTIONS);
        matrix.addState(Lists.newArrayList(-1.0f, -1.0f, -1.0f, -1.0f, 0.0f, -1.0f));
        matrix.addState(Lists.newArrayList(-1.0f, -1.0f, -1.0f, 0.0f, -1.0f, 100.0f));
        matrix.addState(Lists.newArrayList(-1.0f, -1.0f, -1.0f, 0.0f, -1.0f, -1.0f));
        matrix.addState(Lists.newArrayList(-1.0f, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f));
        matrix.addState(Lists.newArrayList(0.0f, -1.0f, -1.0f, 0.0f, -1.0f, 100.0f));
        matrix.addState(Lists.newArrayList(-1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 100.0f));

        return new QReward(matrix);
    }
}
