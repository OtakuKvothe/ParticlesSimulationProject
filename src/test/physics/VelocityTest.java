package test.physics;

import physics.Velocity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VelocityTest {
    
    Velocity velocity;

    @Test
    public void testFlipX(){
        velocity = new Velocity(1.0, 0.0);
        velocity.flipX();
        assertEquals(velocity.getX(), -1.0, 0.0);
    }

    @Test
    public void testFlipY(){
        velocity = new Velocity(0.0, 1.0);
        velocity.flipY();
        assertEquals(velocity.getY(), -1.0, 0.0);
    }

    @Test
    public void testDiffX(){
        velocity = new Velocity(1.0, 1.0);
        Velocity otherVelocity = new Velocity(2.25, 3.45);
        assertEquals(velocity.getX() - otherVelocity.getX(), -1.25, 0.0); 
    }

    @Test
    public void testDiffY(){
        velocity = new Velocity(1.0, 1.0);
        Velocity otherVelocity = new Velocity(2.25, 3.45);
        assertEquals(velocity.getY() - otherVelocity.getY(), -2.45, 0.0); 
    }

    @Test
    public void testChangeInEnergy(){
        velocity = new Velocity(0.0, 0.0);
        velocity.increaseByEnergy(1.0, 1.0, 1.0);
        assertEquals(velocity.getX(), 1.0, 0.0);
        assertEquals(velocity.getY(), 1.0, 0.0);
        velocity.decreaseByEnergy(1.0, 1.0, 1.0);
        assertEquals(velocity.getX(), 0.0, 0.0);
        assertEquals(velocity.getY(), 0.0, 0.0);
    }
}
