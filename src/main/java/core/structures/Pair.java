package core.structures;

public class Pair<T1, T2> {

    public T1 one;
    public T2 two;

    public Pair(T1 x, T2 y) {
        this.one = x;
        this.two = y;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", one.toString(), two.toString());
    }
}
