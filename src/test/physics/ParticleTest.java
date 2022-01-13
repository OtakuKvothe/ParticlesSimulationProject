package test.physics;

import java.awt.Color;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import physics.Particle;
import physics.Position;

public class ParticleTest {

    Particle particle;

    @Test
    public void testMove() {
        particle = new Particle(0.0, 0.0, 1.0, 1.0, 0.01, 0.5, Color.BLACK);
        particle.move(1.0);
        assertEquals(particle.getPosition().diffX(new Position(1.0, 1.0)), 0.0, 0.001);
        assertEquals(particle.getPosition().diffY(new Position(1.0, 1.0)), 0.0, 0.001);
    }

    @Test
    public void testTimeToCollide() {
        particle = new Particle(2.0, 1.0, 0.0, 0.0, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.timeToCollide(particle), Double.POSITIVE_INFINITY, 0.05);

        Particle otherParticle = new Particle(1.0, 1.0, 1.0, 0.0, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.timeToCollide(otherParticle), 1.0, 0.05);

        particle = new Particle(1.0, 2.0, 0.0, 0.0, 0.01, 0.5, Color.BLACK);
        otherParticle = new Particle(1.0, 1.0, 0.0, 1.0, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.timeToCollide(otherParticle), 1.0, 0.05);

        particle = new Particle(1.0, 2.0, 0.0, 1.0, 0.01, 0.5, Color.BLACK);
        otherParticle = new Particle(1.0, 1.0, 0.0, 0.0, 0.01, 0.5, Color.BLACK);
        assertEquals(otherParticle.timeToCollide(particle), Double.POSITIVE_INFINITY, 0.05);
    }

    @Test
    public void testTimeToCollideVertical() {
        particle = new Particle(0.5, 0.5, -0.1, 0.0, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.timeToCollideVerticalWall(), 5, 0.5);

        particle = new Particle(0.5, 0.5, 0.0, 0.1, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.timeToCollideVerticalWall(), Double.POSITIVE_INFINITY, 0.5);
    }

    @Test
    public void testTimeToCollideHorizontalWall() {
        particle = new Particle(0.5, 0.5, 0.0, -0.1, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.timeToCollideHorizontalWall(), 5, 0.5);

        particle = new Particle(0.5, 0.5, 0.1, 0.0, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.timeToCollideHorizontalWall(), Double.POSITIVE_INFINITY, 0.5);
    }

    @Test
    public void testBounceOff() {
        particle = new Particle(2.0, 1.0, 0.0, 0.0, 0.01, 0.5, Color.BLACK);
        Particle otherParticle = new Particle(1.0, 1.0, 1.0, 0.0, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.getCollisionCount(), 0, 0);
        assertEquals(otherParticle.getCollisionCount(), 0, 0);
        particle.bounceOff(otherParticle);
        assertEquals(particle.getCollisionCount(), 1, 0);
        assertEquals(otherParticle.getCollisionCount(), 1, 0);
    }

    @Test
    public void testBounceOffVerticalWall() {
        particle = new Particle(0.5, 0.5, -0.1, 0.0, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.getCollisionCount(), 0, 0);
        particle.bounceOffVerticalWall();
        assertEquals(particle.getCollisionCount(), 1, 0);
    }

    @Test
    public void testBounceOffHorizontalWall() {
        particle = new Particle(0.5, 0.5, 0.0, -0.1, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.getCollisionCount(), 0, 0);
        particle.bounceOffHorizontalWall();
        assertEquals(particle.getCollisionCount(), 1, 0);
    }

    @Test
    public void testGetCollisionCount() {
        particle = new Particle(1.0, 2.0, 0.0, 0.0, 0.01, 0.5, Color.BLACK);
        Particle otherParticle = new Particle(1.0, 1.0, 0.0, 1.0, 0.01, 0.5, Color.BLACK);
        assertEquals(particle.getCollisionCount(), 0, 0);
        assertEquals(otherParticle.getCollisionCount(), 0, 0);
        particle.bounceOff(otherParticle);
        assertEquals(particle.getCollisionCount(), 1, 0);
        assertEquals(otherParticle.getCollisionCount(), 1, 0);
    }

    @Test
    public void testKineticEnergy() {
        particle = new Particle(0.0, 0.0, 1.0, 1.0, 0.01, 1.0, Color.BLACK);
        assertEquals(particle.kineticEnergy(), 1.0, 0.01);
    }
}
