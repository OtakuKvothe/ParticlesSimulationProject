package test.physics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import physics.Position;

public class PositionTest {
    
    Position position1;
    Position position2;

    @Test
    public void testDiffX(){
        position1 = new Position(0.5, 0.21);
        position2 = new Position(0.46, 0.44);
        assertEquals(position1.diffX(position2), 0.04, 0.001);
    }

    @Test
    public void testDiffY(){
        position1 = new Position(0.5, 0.21);
        position2 = new Position(0.46, 0.44);
        assertEquals(position1.diffY(position2), -.23, 0.001);
    }
}
