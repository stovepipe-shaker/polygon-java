package core.operations;

import core.structures.Pair;

public class PairOperations {

    public static <T extends Number & Comparable<T>> boolean isValueInRange(T value, Pair<T, T> range, boolean inclusiveLeft, boolean inclusiveRight) {
        return range.one.compareTo(value) < (inclusiveLeft ? 1 : 0)
                && value.compareTo(range.two) < (inclusiveRight ? 1 : 0);
    }
}
