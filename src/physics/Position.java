package physics;

import java.awt.*;
/**
 * Inherits java.awt.point, for storing the position of a particle. Also provides functionality for calculating distance between particles.
 */
public class Position extends Point.Double {
    /**
     * Default constructor
     */
    public Position(){
        this(0, 0);
    }

    /**
     * Parameterised constructor
     * @param x x-position of particle
     * @param y y-position of particle
     */
    public Position(double x, double y){
        super(x, y);
    }

    /**
     * Returns relative difference in x-position
     * @param that particle that is compared with this
     * @return double value, difference in x-position relative to self
     */
    public double delX(Position that){
        return this.x - that.x;
    }

    /**
     * 
     * @param that particle that is compared with this
     * @return double value, difference in y-position relative to self
     */
    public double delY(Position that){
        return this.y - that.y;
    }
}
