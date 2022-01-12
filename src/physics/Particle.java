package physics;

import standardLib.StdCmdDraw;
import standardLib.StdDraw;
import standardLib.StdRandom;

import java.awt.*;

public class Particle {
    private final double radius;
    private final double mass;
    private Position position;
    private Velocity velocity;
    private Color color;
    private int numberOfCollisions;
    private int order = 0;

    public Particle() {
        position = new Position(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0.0, 1.0));
        velocity = new Velocity(StdRandom.uniform(-.005, 0.005), StdRandom.uniform(-.005, 0.005));
        radius = 0.01;
        mass = 0.5;
        color = Color.BLACK;
    }

    public Particle(double rx, double ry, double vx, double vy, double radius, double mass, Color color) {
        position = new Position(rx, ry);
        velocity = new Velocity(vx, vy);
        this.radius = radius;
        this.mass = mass;
        this.color = color;
    }

    public void moveParticle(double dt) {
        double dx = dt * this.velocity.x;
        double dy = dt * this.velocity.y;
        this.position.x += dx;
        this.position.y += dy;
    }

    void drawParticle() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(this.position.x, this.position.y, this.radius);
        StdCmdDraw.putCharacter((char) ('#' + order), this.position.x, this.position.y);
    }

    public double timeToCollision(Particle that) {
        System.out.println("Time: " + this + " " + that);
        if (this == that)
            return Double.POSITIVE_INFINITY;
        double dx = that.position.delX(this.position);
        double dy = that.position.delY(this.position);
        double dvx = that.velocity.delX(this.velocity);
        double dvy = that.velocity.delY(this.velocity);
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0)
            return Double.POSITIVE_INFINITY;
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0)
            return Double.POSITIVE_INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToCollisionHorizontalWall() {
        System.out.println("Horizontal Wall Collision" + this);
        double vy = this.velocity.y;
        double ry = this.position.y;
        if (vy > 0)
            return (1.0 - ry - radius) / vy;
        else if (vy < 0)
            return (radius - ry) / vy;
        return Double.POSITIVE_INFINITY;
    }

    public double timeToCollisionVerticalWall() {
        System.out.println("Vertical Wall Collision" + this);
        double vx = this.velocity.x;
        double rx = this.position.x;
        if (vx > 0)
            return (1.0 - rx - radius) / vx;
        else if (vx < 0)
            return (radius - rx) / vx;
        return Double.POSITIVE_INFINITY;
    }

    public int getNumberOfCollisions() {
        return numberOfCollisions;
    }

    public void collisionWithParticle(Particle that) {
        System.out.println("Collision: " + this + " " + that);
        double dx = that.position.delX(this.position);
        double dy = that.position.delY(this.position);
        double dvx = that.velocity.delX(this.velocity);
        double dvy = that.velocity.delY(this.velocity);
        double dvdr = dx * dvx + dy * dvy;
        double sigma = this.radius + that.radius;
        double j = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * sigma);
        double jx = j * dx / sigma;
        double jy = j * dy / sigma;
        this.velocity.increaseInEnergy(jx, jy, this.mass);
        that.velocity.increaseInEnergy(jx, jy, that.mass);
        this.numberOfCollisions++;
        that.numberOfCollisions++;
    }

    public void bounceOffVerticalWall() {
        velocity.reverseX();
        numberOfCollisions++;
    }

    public void bounceOffHorizontalWall() {
        velocity.reverseY();
        numberOfCollisions++;
    }

    public double kineticEnergy() {
        return 0.5 * mass * (velocity.x * velocity.x + velocity.y * velocity.y);
    }
}
