package test.physics;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import physics.Event;
import physics.Particle;

public class EventTest {

    Event event1;
    Event event2;

    @Test
    public void testCompareTo() {
        event1 = new Event(1.0, null, null);
        event2 = new Event(1.2, null, null);
        assertTrue("Does event 1 occur before event 2" ,event1.compareTo(event2) < 0);
    }

    @Test
    public void testIsValid() {
        Particle a = new Particle(2.0, 1.0, 0.0, 0.0, 0.01, 0.5, Color.BLACK);
        Particle b = new Particle(1.0, 1.0, 1.0, 0.0, 0.01, 0.5, Color.BLACK);

        event1 = new Event(0.1, a, b);
        assertTrue("Is collision valid", event1.isValid());
    }
}
