package physics;

import helper.StdCmdDraw;
import helper.StdDraw;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CollisionSystem {
    private PriorityQueue<Event> prioritizedEvents;
    private double simulationTime = 0.0d;
    private Particle[] particles;
    private static final double REDRAW_INTERVAL = 1.0d; // time between each redraw

    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
    }

    private void predictCollision(Particle seer, double limit) {
        if (seer == null) return;
        for (Particle particle : particles) {
            double dt = seer.timeToCollide(particle);
            if (simulationTime + dt <= limit) {
                Event collision = new Event(simulationTime + dt, seer, particle);
                prioritizedEvents.offer(collision);
            }
        }
        double dtx = seer.timeToCollideVerticalWall();
        if (simulationTime + dtx <= limit)
            prioritizedEvents.offer(new Event(simulationTime + dtx, seer, null));

        double dty = seer.timeToCollideHorizontalWall();
        if (simulationTime + dty <= limit)
            prioritizedEvents.offer(new Event(simulationTime + dty, null, seer));
    }

    private void redraw(double limit) {
        StdDraw.clear();
        StdCmdDraw.clear();
        Arrays.stream(particles).forEach(Particle::draw);
        StdDraw.show(20);
        StdCmdDraw.draw();
        if (simulationTime < limit) prioritizedEvents.offer(new Event(simulationTime + REDRAW_INTERVAL, null, null));
    }

    public void simulate(double limit) {
        prioritizedEvents = new PriorityQueue<>();
        Arrays.stream(particles).forEach(particle -> predictCollision(particle, limit));
        prioritizedEvents.offer(new Event(0.0, null, null));
        while (!prioritizedEvents.isEmpty()) {
            Event foremostEvent = prioritizedEvents.poll();
            if (!foremostEvent.isValid()) continue;
            Arrays.stream(particles).forEach(particle -> particle.move(foremostEvent.getTime() - simulationTime));
            simulationTime = foremostEvent.getTime();
            Particle a = foremostEvent.getA();
            Particle b = foremostEvent.getB();
            if (a != null && b != null) a.bounceOff(b);
            else if (a != null) a.bounceOffVerticalWall();
            else if (b != null) b.bounceOffHorizontalWall();
            else redraw(limit);
            predictCollision(a, limit);
            predictCollision(b, limit);
        }
    }
}
