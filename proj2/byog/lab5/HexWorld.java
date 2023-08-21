package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static int getOffset(int s, int y) {
        int val = 0;
        if (y >= s) {
            val = y - 2*s + 1;
        } else {
            val = -1 * y;
        }
        return val;
    }

    private static int getColumn(int s, int y) {
        int val = 0;
        if (y >= s) {
            val = 2 * (2*s - y - 1) + s;
        } else {
            val = s + 2 * y;
        }
        return val;
    }

    public static void addHexgon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int y = 0; y < 2 * s; y += 1) {
            int offset = getOffset(s, y);
            int column = getColumn(s, y);
            int newX = p.x + offset;
            int newY = p.y + y;
            Position rowStart = new Position(newX, newY);
            drawColumn(world, rowStart, t, column);
        }
    }

    private static void drawColumn(TETile[][] world, Position p, TETile t, int column) {
        for (int i = 0; i < column; i += 1) {
            // world[p.x + i][p.y] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
            world[p.x + i][p.y] = t;
        }
    }

    @Test
    public void testHexRowWidth() {
        assertEquals(3, getColumn(3, 5));
        assertEquals(5, getColumn(3, 4));
        assertEquals(7, getColumn(3, 3));
        assertEquals(7, getColumn(3, 2));
        assertEquals(5, getColumn(3, 1));
        assertEquals(3, getColumn(3, 0));
        assertEquals(2, getColumn(2, 0));
        assertEquals(4, getColumn(2, 1));
        assertEquals(4, getColumn(2, 2));
        assertEquals(2, getColumn(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0,  getOffset(3, 5));
        assertEquals(-1, getOffset(3, 4));
        assertEquals(-2, getOffset(3, 3));
        assertEquals(-2, getOffset(3, 2));
        assertEquals(-1, getOffset(3, 1));
        assertEquals(0,  getOffset(3, 0));
        assertEquals(0,  getOffset(2, 0));
        assertEquals(-1, getOffset(2, 1));
        assertEquals(-1, getOffset(2, 2));
        assertEquals(0,  getOffset(2, 3));
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        int WIDTH = 30;
        int HEIGHT = 30;
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position newP = new Position(15, 0);
        addHexgon(world, newP, 7, Tileset.WALL);
        // draws the world to the screen
        ter.renderFrame(world);

    }

    private static class Position {
        private int x;
        private int y;
        private Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // Getter methods
        private int getX() {
            return x;
        }
        private int getY() {
            return y;
        }
        // Setter methods
        private void setX(int x) {
            this.x = x;
        }
        private void setY(int y) {
            this.y = y;
        }
    }
}
