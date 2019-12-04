package core.enumerations;

import java.util.Arrays;
import java.util.List;

public enum EnDirections {

    D000(1, 0),
    D045(1, 1),
    D090(0, 1),
    D135(-1, 1),
    D180(-1, 0),
    D225(-1, -1),
    D270(0, -1),
    D315(1, -1);

    private int dx, dy;

    EnDirections(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    static public List<EnDirections> getDirections4() {
        return Arrays.asList(D000, D090, D180, D270);
    }

    static public List<EnDirections> getDirections8() {
        return Arrays.asList(D000, D045, D090, D135, D180, D225, D270, D315);
    }
}
