package pl.mw.qlearning;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QController {

    private QReward reward;
    private QAgent agent;
    private QParameter parameter;

    public void start(int episodes, int states, int actions, int goalState) throws NotStateFoundException {
        agent.init(states, actions);

        for (int i = 0; i < episodes; i++) {
            while (agent.getCurrentState() != goalState) {

                Integer state = reward.pickRandomState();
                agent.setCurrentState(state);

                // todo this needs more robust logic
                int action = agent.pickAction(state);

                Float newQ = computeQ(state, action);
                agent.updateQ(action, newQ);
            }
        }

        agent.printMemory();
    }

    private Float computeQ(int state, int action) {
        return agent.getQ(state, action) + parameter.getLearningRate() * (
                reward.getReward(state, action) + (parameter.getGamma() * maxFromAdjacent()) - agent.getQ(state, action));
    }

    private Float maxFromAdjacent() {
        return reward.findAdjacent(agent.getCurrentState())
                .stream()
                .max(Float::compareTo)
                .orElseThrow(NotAdjacementFoundException::new);
    }
}
