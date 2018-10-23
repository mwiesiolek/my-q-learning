package pl.mw.qlearning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class QParameter {
    private float learningRate;
    private float gamma;
}
