package physics;

/**
 * For velocity of a particle in terms of x and y components.
 * Provides functionality for calculation of relative velocity
 */
public class Velocity {
    double x;
    double y;

    public Velocity() {
        this(0, 0);
    }

    public Velocity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void reverseX() {
        x *= -1;
    }

    public void reverseY() {
        y *= -1;
    }

    public double delX(Velocity that) {
        return this.x - that.x;
    }

    public double delY(Velocity that) {
        return this.y - that.y;
    }

    public void increaseInEnergy(double jx, double jy, double mass) {
        System.out.println("Velocity Change: "+this);
        System.err.println(x+" "+y);
        x += jx / mass;
        y += jy / mass;
        System.err.println(x+" "+y);
    }

    public void decreaseInEnergy(double jx, double jy, double mass){
        increaseInEnergy(-jx, -jy, mass);
    }
}
