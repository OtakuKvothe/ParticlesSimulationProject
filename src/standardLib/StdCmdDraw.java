package standardLib;

import java.util.Arrays;

/**
 * Created by off999555 on 28/9/2558 at 2:43.
 * Project Name: Particle Collision Simulator
 */
public class StdCmdDraw {
    private static StdCmdDraw instance = new StdCmdDraw();
    private static final char fence = '#';
    private char[][] canvas;
    private int width, height;

    private StdCmdDraw() {
    }

    public static void setCanvasSize(int w, int h) {
        instance.width = w;
        instance.height = h;
        instance.canvas = new char[w][h];
        clear();
    }

    /**
     * @param c the character to put into the virtual drawing canvas
     * @param x should be in range 0 to 1
     * @param y should be in range 0 to 1
     * @throws ArrayIndexOutOfBoundsException if x or y is not in appropriate range
     */
    public static void putCharacter(char c, double x, double y) {
        int w = (int) Math.round(x * (instance.width - 1));
        int h = (instance.height - 1) - (int) Math.round(y * (instance.height - 1));
        instance.canvas[w][h] = c;
    }

    public static void draw() {
        for (int i = 0; i < instance.width+2; i++) System.out.print(fence);
        System.out.println();
        for (int h = 0; h < instance.height; h++) {
            System.out.print(fence);
            for (int w = 0; w < instance.width; w++) System.out.print(instance.canvas[w][h]);
            System.out.println(fence);
        }
        for (int i = 0; i < instance.width+2; i++) System.out.print(fence);
        System.out.println();
    }

    public static void clear() {
        Arrays.stream(instance.canvas).forEach(chars -> Arrays.fill(chars, ' '));
    }
}
