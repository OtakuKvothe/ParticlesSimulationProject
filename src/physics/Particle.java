package physics;

import helper.StdCmdDraw;
import helper.StdDraw;
import helper.StdRandom;

import java.awt.*;

/**
 * Created by off999555 on 27/9/2558 at 22:20.
 * Project Name: Particle Collision Simulator
 * Particle ในที่นี้คืออนุภาคที่มีมวล มีที่อยู่ มีความเร็ว เป็นอะไรก็ได้ที่มีลักษณะวงกลม ไม่ว่าจะเป็นบอล ลูกบาส โลก ดวงอาทิตย์หรืออะตอม
 */
public class Particle {
    private final double radius;
    private final double mass;
    private Position position;
    private Velocity velocity;
    private Color color;
    private int collisionCount;
    private int order;

    {
//        order = StdRandom.uniform(10);
    }

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

    public void move(double timeDelta) {
        double dx = timeDelta * this.velocity.x;
        double dy = timeDelta * this.velocity.y;
        this.position.x += dx;
        this.position.y += dy;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(this.position.x, this.position.y, this.radius);
        StdCmdDraw.putCharacter((char) ('#' + order), this.position.x, this.position.y);
    }

    public double timeToCollide(Particle that) {
        if (this == that) return Double.POSITIVE_INFINITY; // do not collide with itself!
        double dx = that.position.diffX(this.position);
        double dy = that.position.diffY(this.position);
        double dvx = that.velocity.diffX(this.velocity);
        double dvy = that.velocity.diffY(this.velocity);
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0) return Double.POSITIVE_INFINITY; // no collision
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0) return Double.POSITIVE_INFINITY; // no collision
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToCollideVerticalWall() {
        double vx = velocity.x;
        double rx = position.x;
        if (vx > 0) return (1.0 - rx - radius) / vx;
        else if (vx < 0) return (radius - rx) / vx;
        return Double.POSITIVE_INFINITY;
    }

    public double timeToCollideHorizontalWall() {
        double vy = velocity.y;
        double ry = position.y;
        if (vy > 0) return (1.0 - ry - radius) / vy;
        else if (vy < 0) return (radius - ry) / vy;
        return Double.POSITIVE_INFINITY;
    }

    public void bounceOff(Particle that) {
        double dx = that.position.diffX(this.position);
        double dy = that.position.diffY(this.position);
        double dvx = that.velocity.diffX(this.velocity);
        double dvy = that.velocity.diffY(this.velocity);
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;
        double j = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double jx = j * dx / dist;
        double jy = j * dy / dist;
        this.velocity.increaseByEnergy(jx, jy, this.mass);
        that.velocity.decreaseByEnergy(jx, jy, that.mass);
        this.collisionCount++;
        that.collisionCount++;
    }

    public void bounceOffVerticalWall() {
        velocity.flipX();
        collisionCount++;
    }

    public void bounceOffHorizontalWall() {
        velocity.flipY();
        collisionCount++;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public double kineticEnergy() {
        return 0.5 * mass * (velocity.x * velocity.x + velocity.y * velocity.y);
    }
}
