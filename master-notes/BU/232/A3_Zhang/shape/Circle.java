package shape;

public class Circle extends Shape {

    private double radius;

    public Circle(double initialRadius) {
        this.radius = initialRadius;
    };

    @Override
    public double findArea() {
        return Math.PI * this.radius * this.radius;

    };
}