package Lesson_4;

public class Triangle extends Figure{
    public Triangle(int[] figureParameters, String backgroundColor, String borderColor) {
        super(figureParameters, backgroundColor, borderColor);
        super.name = "Треугольник";
    }

    @Override
    public double calculateArea() {
        int a = super.getFigureParameters()[0];
        int b = super.getFigureParameters()[1];
        int c = super.getFigureParameters()[2];
        double p = calculatePerimeter(super.getFigureParameters()) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
