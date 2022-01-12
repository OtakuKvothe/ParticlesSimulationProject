package physics;

public class Event implements Comparable<Event> {
    private double time;
    private Particle a, b;
    private int countA, countB;

    public Event(double time, Particle a, Particle b) {
        this.time = time;
        this.a = a;
        this.b = b;
        this.countA = a == null ? -1 : a.getCollisionCount();
        this.countB = b == null ? -1 : b.getCollisionCount();
    }

    public double getTime() {
        return time;
    }

    public Particle getA() {
        return a;
    }

    public Particle getB() {
        return b;
    }

    @Override
    public int compareTo(Event that) {
        return Double.compare(this.time, that.time);
    }

    public boolean isValid() {
        if (a != null && a.getCollisionCount() != countA) return false;
        if (b != null && b.getCollisionCount() != countB) return false;
        return true;
    }
}
