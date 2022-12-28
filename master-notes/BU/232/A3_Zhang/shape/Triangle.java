package shape;

public class Triangle extends Shape {

    private double height;

    private double base;

    public Triangle(double initialHeight, double initialBase) {
        this.height = initialHeight;
        this.base = initialBase;
    };

    @Override
    public double findArea() {
        return 0.5 * this.height * this.base;
    };
}