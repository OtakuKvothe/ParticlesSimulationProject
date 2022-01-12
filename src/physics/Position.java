package physics;

import java.awt.*;

public class Position extends Point.Double {
    public Position() {
        this(0, 0);
    }

    public Position(double x, double y) {
        super(x, y);
    }

    public double diffX(Position that) {
        return this.x - that.x;
    }

    public double diffY(Position that) {
        return this.y - that.y;
    }
}
