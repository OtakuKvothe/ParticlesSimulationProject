package physics;

import java.awt.*;

/**
 * Created by off999555 on 27/9/2558 at 22:30.
 * Project Name: Particle Collision Simulator
 * Extra field isn't needed now, so simply inheriting Point.Double is fine in this case.
 */
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
