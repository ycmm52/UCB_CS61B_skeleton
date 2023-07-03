public class Planet {

    // private double xxPos;
    // private double yyPos;
    // private double xxVel;
    // private double yyVel;
    // private double mass;
    // private String imgFileName;
    // private static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass  = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass  = p.mass;
        this.imgFileName = p.imgFileName;
    }

    private double calcDistanceSquare(Planet other) {
        double xDelta = this.xxPos - other.xxPos;
        double xDisSquare = xDelta * xDelta;
        double yDelta = this.yyPos - other.yyPos;
        double yDisSquare = yDelta * yDelta;
        return xDisSquare + yDisSquare;
    }

    public double calcDistance(Planet other) {
        return Math.sqrt(calcDistanceSquare(other));
    }

    public double calcForceExertedBy(Planet other) {
        return G * this.mass * other.mass / calcDistanceSquare(other);
    }

    public double calcForceExertedByX(Planet other) {
        double totalFource = calcForceExertedBy(other);
        double distance = calcDistance(other);
        double deltaDistance = other.xxPos - this.xxPos;
        return totalFource * (deltaDistance / distance);
    }

    public double calcForceExertedByY(Planet other) {
        double totalFource = calcForceExertedBy(other);
        double distance = calcDistance(other);
        double deltaDistance = other.yyPos - this.yyPos;
        return totalFource * deltaDistance / distance;
    }

    public double calcNetForceExertedByX(Planet[] others) {
        double netForce = 0.0;
        for (int i = 0; i < others.length; i += 1) {
            if (others[i] != this) {
                netForce += calcForceExertedByX(others[i]);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] others) {
        double netForce = 0.0;
        for (int i = 0; i < others.length; i += 1) {
            if (others[i] != this) {
                netForce += calcForceExertedByY(others[i]);
            }
        }
        return netForce;
    }

    public void update(double dt, double fx, double fy) {
        double axDelta = fx / this.mass;
        double vxDelta = axDelta * dt;
        xxVel += vxDelta;
        double xDelta = xxVel * dt;
        xxPos += xDelta;
        double ayDelta = fy / this.mass;
        double vyDelta = ayDelta * dt;
        yyVel += vyDelta;
        double yDelta = yyVel * dt;
        yyPos += yDelta;
    }

    public void draw() {
        String image = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, image);
    }

    private void print() {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            this.xxPos, this.yyPos, this.xxVel,
            this.yyVel, this.mass, this.imgFileName);
    }
}
