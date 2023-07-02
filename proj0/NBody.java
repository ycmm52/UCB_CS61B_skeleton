public class NBody {
    private static String file = null;
    private static int N;
    private static double radius;
    private static Planet[] planets;

    private static void readPlanetFile(String fileName) {
        In in = new In(fileName);
        NBody.file = fileName;
        NBody.N = in.readInt();
        in.readLine();
        NBody.radius = in.readDouble();
        int n = 0;
        double xP, yP, xV, yV, mass;
        String img;
        planets = new Planet[N];
        while (!in.isEmpty() && n < N) {
            in.readLine();
            xP = in.readDouble();
            yP = in.readDouble();
            xV = in.readDouble();
            yV = in.readDouble();
            mass = in.readDouble();
            img = in.readString();
            planets[n] = new Planet(xP, yP, xV, yV, mass, img);
            n += 1;
        }
    }

    public static double readRadius(String fileName) {
        if (!fileName.equals(NBody.file)) {
            readPlanetFile(fileName);
        }
        return NBody.radius;
    }

    public static Planet[] readPlanets(String fileName) {
        if (!fileName.equals(NBody.file)) {
            readPlanetFile(fileName);
        }
        return NBody.planets;
    }

    private static void drawBackGround() {
        double radiusScaled = NBody.radius;
        StdDraw.setScale(-1 * radiusScaled, radiusScaled);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
    }

    private static void drawPlanets() {
        for (Planet p : NBody.planets) {
            p.draw();
        }
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double t = 0.0;
        String fileName = args[2];
        readPlanetFile(fileName);
        StdDraw.enableDoubleBuffering();
        double[] xForces = new double[NBody.N];
        double[] yForces = new double[NBody.N];
        while (t < T) {
            for (int i = 0; i < NBody.N; i += 1) {
                xForces[i] = NBody.planets[i].calcNetForceExertedByX(NBody.planets);
                yForces[i] = NBody.planets[i].calcNetForceExertedByY(NBody.planets);
            }
            for (int i = 0; i < NBody.N; i += 1) {
                NBody.planets[i].update(dt, xForces[i], yForces[i]);
            }
            drawBackGround();
            drawPlanets();
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}

