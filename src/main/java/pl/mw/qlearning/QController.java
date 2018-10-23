package pl.mw.qlearning;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QController {

    private QReward reward;
    private QAgent agent;
    private QParameter parameter;

    public void start(int episodes, int states, int actions, int goalState) {
        agent.init(states, actions);
        agent.setReward(reward);

        for (int i = 0; i < episodes; i++) {
            Integer state = reward.pickRandomState();
            agent.setCurrentState(state);

            while (agent.getCurrentState() != goalState) {
                int action = agent.pickAction();
                int oldState = agent.getCurrentState();
                agent.setCurrentState(action);

                System.out.println(String.format("(state=%s,action=%s)", oldState, action));
                System.out.println(String.format("Reward %s", reward.getReward(oldState, action)));

                Float newQ = computeQ(oldState, action);
                agent.updateQ(oldState, action, newQ);
            }

            float delta = (float) i / episodes;
            agent.updateEpsilon(delta);
            System.out.println(String.format("Episode: %s", (i + 1)));
        }

        agent.printQTable();
    }

    private Float computeQ(int state, int action) {
        return agent.getQ(state, action) + parameter.getLearningRate() * (
                reward.getReward(state, action) + (parameter.getGamma() * maxFromAdjacent(action)) - agent.getQ(state, action));
    }

    private Float maxFromAdjacent(int action) {
        return reward.findAdjacent(action)
                .stream()
                .max(Float::compareTo)
                .orElseThrow(NotAdjacementFoundException::new);
    }
}
