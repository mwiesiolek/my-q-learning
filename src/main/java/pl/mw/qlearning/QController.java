package pl.mw.qlearning;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QController {

    private QReward reward;
    private QAgent agent;
    private QParameter parameter;

    public void start(int episodes, int states, int actions, int goalState) {
        agent.init(states, actions);

        for (int i = 0; i < episodes; i++) {
            Integer state = reward.pickRandomState();
            agent.setCurrentState(state);

            while (agent.getCurrentState() != goalState) {
                int action = agent.pickAction();

                // todo determine whether or not that action should be taken
                agent.setCurrentState(action);

                System.out.println(String.format("Reward %s for (state=%s,action=%s)", reward.getReward(agent.getCurrentState(), action),
                        agent.getCurrentState(), action));

                Float newQ = computeQ(state, action);
                agent.updateQ(action, newQ);
            }

            float delta = (float) i / episodes;
            agent.updateEpsilon(delta);
        }

        agent.printQTable();
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
