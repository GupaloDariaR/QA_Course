package Lesson_4;

public class Circle extends Figure{

    public Circle(int[] figureParameters, String backgroundColor, String borderColor) {
        super(figureParameters, backgroundColor, borderColor);
        super.name = "Круг";
    }

    @Override
    public double calculatePerimeter(int[] figureParameters) {
        return 2 * Math.PI * super.getFigureParameters()[0];
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(super.getFigureParameters()[0], 2);
    }
}
