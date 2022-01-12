package physics;

import standardLib.StdCmdDraw;
import standardLib.StdDraw;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CollisionSimulation {
    private PriorityQueue<Event> prioritisedEvents;
    private double simulationTime = 0.0d;
    private Particle particles[];
    private static final double REDRAW_INTERVAL = 1.0d;

    public CollisionSimulation(Particle particles[]) {
        this.particles = particles;
    }

    private void predictCollision(Particle focus, double limit) {
        if (focus == null)
            return;
        for (Particle particle : particles) {
            double dt = focus.timeToCollision(particle);
            if (simulationTime + dt <= limit) {
                Event collision = new Event(simulationTime + dt, focus, particle);
                prioritisedEvents.offer(collision);
            }
        }
        double dtx = focus.timeToCollisionVerticalWall();
        if (simulationTime + dtx <= limit)
            prioritisedEvents.offer(new Event(simulationTime + dtx, focus, null));

        double dty = focus.timeToCollisionHorizontalWall();
        if (simulationTime + dty <= limit)
            prioritisedEvents.offer(new Event(simulationTime + dty, null, focus));
    }

    private void redrawFrame(double limit) {
        StdDraw.clear();
        StdCmdDraw.clear();
        Arrays.stream(particles).forEach(Particle::drawParticle);
        StdDraw.show(20);
        StdCmdDraw.draw();
        if (simulationTime < limit)
            prioritisedEvents.offer(new Event(simulationTime + REDRAW_INTERVAL, null, null));
    }

    public void simulateParticles(double limit) {
        prioritisedEvents = new PriorityQueue<>();
        Arrays.stream(particles).forEach(particle -> predictCollision(particle, limit));
        prioritisedEvents.offer(new Event(0.0, null, null));
        while (!prioritisedEvents.isEmpty()) {
            Event foremostEvent = prioritisedEvents.poll();
            if (!foremostEvent.isValid())
                continue;
            Arrays.stream(particles)
                    .forEach(particle -> particle.moveParticle(foremostEvent.getTime() - simulationTime));
            simulationTime = foremostEvent.getTime();
            Particle a = foremostEvent.getA();
            Particle b = foremostEvent.getB();
            if (a != null && b != null)
                a.collisionWithParticle(b);
            else if (a != null)
                a.bounceOffVerticalWall();
            else if (b != null)
                b.bounceOffHorizontalWall();
            else
                redrawFrame(limit);
            predictCollision(a, limit);
            predictCollision(b, limit);
        }
    }
}
