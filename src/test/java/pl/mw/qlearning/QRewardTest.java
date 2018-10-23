package pl.mw.qlearning;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class QRewardTest {
    @Test
    public void shouldCreateStatesCollectionProperly() {

        //given
        QMatrix matrix = new QMatrix();
        matrix.init(4, 4);

        //when
        QReward reward = new QReward(matrix);

        //then
        assertThat(reward.states).hasSize(4);

        List<Integer> expected = Arrays.asList(0, 1, 2, 3);
        assertThat(reward.states).hasSameElementsAs(expected);
    }
}