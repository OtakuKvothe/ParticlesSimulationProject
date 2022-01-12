import helper.StdCmdDraw;
import helper.StdDraw;
import helper.StdIn;
import physics.CollisionSystem;
import physics.Particle;

import java.awt.*;

public class Main {

    public static final int TIME_LIMIT = 10000;

    public static void main(String[] args) {
        StdDraw.setCanvasSize(600, 600);
        StdCmdDraw.setCanvasSize(50, 20);

        StdDraw.show(0);

        Particle[] particles;


        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            particles = new Particle[n];
            for (int i = 0; i < n; i++)
                particles[i] = new Particle();
        }


        else {
            System.out.println("Paste the information of all particles below!");
            int N = StdIn.readInt();
            particles = new Particle[N];
            for (int i = 0; i < N; i++) {
                double rx = StdIn.readDouble();
                double ry = StdIn.readDouble();
                double vx = StdIn.readDouble();
                double vy = StdIn.readDouble();
                double radius = StdIn.readDouble();
                double mass = StdIn.readDouble();
                int r = StdIn.readInt();
                int g = StdIn.readInt();
                int b = StdIn.readInt();
                Color color = new Color(r, g, b);
                particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
            }
        }
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(TIME_LIMIT);
    }
}

