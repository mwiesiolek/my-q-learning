package pl.mw.qlearning;

import lombok.Value;

@Value
public class Tuple<K, V> {
    private K key;
    private V value;
}
