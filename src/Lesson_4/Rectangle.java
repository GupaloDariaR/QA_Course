package Lesson_4;

public class Rectangle extends Figure{
    public Rectangle(int[] figureParameters, String backgroundColor, String borderColor) {
        super(figureParameters, backgroundColor, borderColor);
        super.name = "Прямоугольник";
    }

    @Override
    public double calculateArea() {
        int a = super.getFigureParameters()[0];
        int b = super.getFigureParameters()[1];
        return (a * b);
    }
}
