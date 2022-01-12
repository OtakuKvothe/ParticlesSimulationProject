package physics;

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

    public void flipX() {
        x *= -1;
    }

    public void flipY() {
        y *= -1;
    }

    public double diffX(Velocity that) {
        return this.x - that.x;
    }

    public double diffY(Velocity that) {
        return this.y - that.y;
    }

    public void increaseByEnergy(double jx, double jy, double mass) {
        x += jx / mass;
        y += jy / mass;
    }

    public void decreaseByEnergy(double jx, double jy, double mass) {
        increaseByEnergy(-jx, -jy, mass);
    }
}
